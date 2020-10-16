package io.mercury.indicator.api;

import java.time.temporal.TemporalAdjuster;

public interface TimeEvent<T extends TemporalAdjuster> extends IndicatorEvent {

	@Override
	default String eventName() {
		return "TimeEvent";
	}

	void onTime(T time);

}
