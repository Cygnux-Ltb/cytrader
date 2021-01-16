package io.cygnus.exchange.core.common.config;

import io.cygnus.exchange.core.common.enums.CoreWaitStrategy;
import io.cygnus.exchange.core.orderbook.IOrderBook;
import io.cygnus.exchange.core.orderbook.OrderBookDirectImpl;
import io.cygnus.exchange.core.orderbook.OrderBookNaiveImpl;
import io.cygnus.exchange.core.utils.AffinityThreadFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;

import java.util.concurrent.ThreadFactory;
import java.util.function.Supplier;

/**
 * Exchange performance configuration
 */
@AllArgsConstructor
@Builder
public final class PerformanceConfiguration {

	public static final PerformanceConfiguration DEFAULT = PerformanceConfiguration.baseBuilder().build();

	/*
	 * Disruptor ring buffer size (number of commands). Must be power of 2.
	 */
	@Getter
	private final int ringBufferSize;

	/*
	 * Number of matching engines. Each instance requires extra CPU core.
	 */
	@Getter
	private final int matchingEnginesNum;

	/*
	 * Number of risk engines. Each instance requires extra CPU core.
	 */
	@Getter
	private final int riskEnginesNum;

	/*
	 * max number of messages not processed by R2 stage. Must be less than quarter
	 * of ringBufferSize. Lower values, like 100, provide better mean latency.
	 * Higher values, like 2000 provide better throughput and tail latency.
	 */
	@Getter
	private final int msgsInGroupLimit;

	/*
	 * max interval when messages not processed by R2 stage. Interfere with
	 * msgsInGroupLimit parameter. Lower values, like 1000 (1us), provide better
	 * mean latency. Higher values, like 2000 provide better throughput and tail
	 * latency.
	 */
	@Getter
	private final int maxGroupDurationNs;

	/*
	 * Disruptor threads factory
	 */
	@Getter
	private final ThreadFactory threadFactory;

	/*
	 * Disruptor wait strategy
	 */
	@Getter
	private final CoreWaitStrategy waitStrategy;

	/*
	 * Order books factory
	 */@Getter
	private final IOrderBook.OrderBookFactory orderBookFactory;

	/*
	 * LZ4 compressor factory for binary commands and reports
	 */
	@Getter
	private final Supplier<LZ4Compressor> binaryCommandsLz4CompressorFactory;

	@Override
	public String toString() {
		return "PerformanceConfiguration{" + "ringBufferSize=" + ringBufferSize + ", matchingEnginesNum="
				+ matchingEnginesNum + ", riskEnginesNum=" + riskEnginesNum + ", msgsInGroupLimit=" + msgsInGroupLimit
				+ ", maxGroupDurationNs=" + maxGroupDurationNs + ", threadFactory="
				+ (threadFactory == null ? null : threadFactory.getClass().getSimpleName()) + ", waitStrategy="
				+ waitStrategy + ", orderBookFactory="
				+ (orderBookFactory == null ? null : orderBookFactory.getClass().getSimpleName())
				+ ", binaryCommandsLz4CompressorFactory=" + (binaryCommandsLz4CompressorFactory == null ? null
						: binaryCommandsLz4CompressorFactory.getClass().getSimpleName())
				+ '}';
	}

	// TODO add expected number of users and symbols

	public static PerformanceConfiguration.PerformanceConfigurationBuilder baseBuilder() {
		return builder().ringBufferSize(16 * 1024).matchingEnginesNum(1).riskEnginesNum(1).msgsInGroupLimit(256)
				.maxGroupDurationNs(10_000).threadFactory(Thread::new).waitStrategy(CoreWaitStrategy.BLOCKING)
				.binaryCommandsLz4CompressorFactory(() -> LZ4Factory.fastestInstance().highCompressor())
				.orderBookFactory(OrderBookNaiveImpl::new);
	}

	public static PerformanceConfiguration.PerformanceConfigurationBuilder latencyPerformanceBuilder() {
		return builder().ringBufferSize(2 * 1024).matchingEnginesNum(1).riskEnginesNum(1).msgsInGroupLimit(256)
				.maxGroupDurationNs(10_000)
				.threadFactory(new AffinityThreadFactory(
						AffinityThreadFactory.ThreadAffinityMode.THREAD_AFFINITY_ENABLE_PER_LOGICAL_CORE))
				.waitStrategy(CoreWaitStrategy.BUSY_SPIN)
				.binaryCommandsLz4CompressorFactory(() -> LZ4Factory.fastestInstance().highCompressor())
				.orderBookFactory(OrderBookDirectImpl::new);
	}

	public static PerformanceConfiguration.PerformanceConfigurationBuilder throughputPerformanceBuilder() {
		return builder().ringBufferSize(64 * 1024).matchingEnginesNum(4).riskEnginesNum(2).msgsInGroupLimit(4_096)
				.maxGroupDurationNs(4_000_000)
				.threadFactory(new AffinityThreadFactory(
						AffinityThreadFactory.ThreadAffinityMode.THREAD_AFFINITY_ENABLE_PER_LOGICAL_CORE))
				.waitStrategy(CoreWaitStrategy.BUSY_SPIN)
				.binaryCommandsLz4CompressorFactory(() -> LZ4Factory.fastestInstance().highCompressor())
				.orderBookFactory(OrderBookDirectImpl::new);
	}
}
