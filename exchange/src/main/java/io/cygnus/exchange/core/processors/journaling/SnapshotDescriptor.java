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
package io.cygnus.exchange.core.processors.journaling;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.NavigableMap;
import java.util.TreeMap;

@Data
public class SnapshotDescriptor implements Comparable<SnapshotDescriptor> {

	// 0 means empty snapshot (clean start)
	private final long snapshotId;

	// sequence when snapshot was made
	private final long seq;
	private final long timestampNs;

	// next and previous snapshots
	private final SnapshotDescriptor prev;
	private SnapshotDescriptor next = null; // TODO can be a list

	private final int numMatchingEngines;
	private final int numRiskEngines;

	// all journals based on this snapshot
	// mapping: startingSeq -> JournalDescriptor
	private final NavigableMap<Long, JournalDescriptor> journals = new TreeMap<>();

	/**
	 * Create initial empty snapshot descriptor
	 *
	 * @param initialNumME - number of matching engine instances
	 * @param initialNumRE - number of risk engine instances
	 * @return new instance
	 */
	public static SnapshotDescriptor createEmpty(int initialNumME, int initialNumRE) {
		return new SnapshotDescriptor(0, 0, 0, null, initialNumME, initialNumRE);
	}

	public SnapshotDescriptor createNext(long snapshotId, long seq, long timestampNs) {
		return new SnapshotDescriptor(snapshotId, seq, timestampNs, this, numMatchingEngines, numRiskEngines);
	}

	@Override
	public int compareTo(@NotNull SnapshotDescriptor o) {
		return Long.compare(this.seq, o.seq);
	}
}
