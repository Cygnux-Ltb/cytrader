/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package io.cygnus.exchange.core.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import io.cygnus.exchange.core.processors.TwoStepSlaveProcessor;
import io.mercury.common.log.CommonLoggerFactory;
import lombok.RequiredArgsConstructor;
import net.openhft.affinity.AffinityLock;

@RequiredArgsConstructor
public final class AffinityThreadFactory implements ThreadFactory {

	private static final Logger log = CommonLoggerFactory.getLogger(AffinityThreadFactory.class);

	// There is a bug it LMAX Disruptor, when configuring dependency graph as
	// processors, not handlers.
	// We have to track all threads requested from the factory to avoid duplicate
	// reservations.
	private final Set<Object> affinityReservations = new HashSet<>();

	private final ThreadAffinityMode threadAffinityMode;

	private static AtomicInteger threadsCounter = new AtomicInteger();

	@Override
	public synchronized Thread newThread(@NotNull Runnable runnable) {

		// log.info("---- Requesting thread for {}", runnable);

		if (threadAffinityMode == ThreadAffinityMode.THREAD_AFFINITY_DISABLE) {
			return Executors.defaultThreadFactory().newThread(runnable);
		}

		if (runnable instanceof TwoStepSlaveProcessor) {
			log.debug("Skip pinning slave processor: {}", runnable);
			return Executors.defaultThreadFactory().newThread(runnable);
		}

		if (affinityReservations.contains(runnable)) {
			log.warn("Task {} was already pinned", runnable);
//            return Executors.defaultThreadFactory().newThread(runnable);
		}

		affinityReservations.add(runnable);

		return new Thread(() -> executePinned(runnable));

	}

	private void executePinned(@NotNull Runnable runnable) {

		try (final AffinityLock lock = getAffinityLockSync()) {

			final int threadId = threadsCounter.incrementAndGet();
			Thread.currentThread().setName(String.format("Thread-AF-%d-cpu%d", threadId, lock.cpuId()));

			log.debug("{} will be running on thread={} pinned to cpu {}", runnable, Thread.currentThread().getName(),
					lock.cpuId());

			runnable.run();

		} finally {
			log.debug("Removing cpu lock/reservation from {}", runnable);
			synchronized (this) {
				affinityReservations.remove(runnable);
			}
		}
	}

	private synchronized AffinityLock getAffinityLockSync() {
		return threadAffinityMode == ThreadAffinityMode.THREAD_AFFINITY_ENABLE_PER_PHYSICAL_CORE
				? AffinityLock.acquireCore()
				: AffinityLock.acquireLock();
	}

	public enum ThreadAffinityMode {

		THREAD_AFFINITY_ENABLE_PER_PHYSICAL_CORE,

		THREAD_AFFINITY_ENABLE_PER_LOGICAL_CORE,

		THREAD_AFFINITY_DISABLE,

		;
	}

}