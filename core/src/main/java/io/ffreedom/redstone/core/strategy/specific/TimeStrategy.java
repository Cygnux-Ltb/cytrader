package io.ffreedom.redstone.core.strategy.specific;

import java.time.temporal.TemporalAdjuster;

import io.ffreedom.redstone.core.strategy.Strategy;

public interface TimeStrategy<T extends TemporalAdjuster> extends Strategy {

	void onTime(T time);

}
