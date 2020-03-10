package io.mercury.polaris.indicator.events;

import java.time.temporal.TemporalAdjuster;

import io.mercury.polaris.indicator.api.IndicatorEvent;

public interface TimeEvent<T extends TemporalAdjuster> extends IndicatorEvent{

	void onTime(T time);

}
