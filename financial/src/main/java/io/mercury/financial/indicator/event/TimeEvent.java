package io.mercury.financial.indicator.event;

import java.time.temporal.TemporalAdjuster;

import io.mercury.financial.indicator.api.IndicatorEvent;

public interface TimeEvent<T extends TemporalAdjuster> extends IndicatorEvent {

	@Override
	default String eventName() {
		return "TimeEvent";
	}

	void onTime(T time);

}
