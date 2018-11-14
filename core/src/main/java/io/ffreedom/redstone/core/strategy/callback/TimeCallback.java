package io.ffreedom.redstone.core.strategy.callback;

import java.time.temporal.TemporalAdjuster;

public interface TimeCallback<T extends TemporalAdjuster> {

	void onTime(T time);

}
