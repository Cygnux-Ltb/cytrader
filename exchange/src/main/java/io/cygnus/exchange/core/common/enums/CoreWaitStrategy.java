package io.cygnus.exchange.core.common.enums;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.YieldingWaitStrategy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
public enum CoreWaitStrategy {

	BUSY_SPIN(BusySpinWaitStrategy::new, false, false),

	YIELDING(YieldingWaitStrategy::new, true, false),

	BLOCKING(BlockingWaitStrategy::new, false, true),

	// special case
	SECOND_STEP_NO_WAIT(() -> null, false, false),

	;

	@Getter
	private final Supplier<WaitStrategy> waitStrategySupplier;

	@Getter
	private final boolean yield;

	@Getter
	private final boolean block;

}
