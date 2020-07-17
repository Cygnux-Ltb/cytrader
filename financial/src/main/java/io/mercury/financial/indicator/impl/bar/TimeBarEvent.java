package io.mercury.financial.indicator.impl.bar;

import io.mercury.financial.indicator.api.IndicatorEvent;

public interface TimeBarEvent extends IndicatorEvent {

	@Override
	default String eventName() {
		return "TimeBarEvent";
	}

	void onCurrentTimeBarChanged(TimeBar bar);

	void onStartTimeBar(TimeBar bar);

	void onEndTimeBar(TimeBar bar);

}
