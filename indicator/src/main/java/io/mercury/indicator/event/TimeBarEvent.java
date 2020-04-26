package io.mercury.indicator.event;

import io.mercury.indicator.api.IndicatorEvent;
import io.mercury.indicator.impl.bar.TimeBar;

public interface TimeBarEvent extends IndicatorEvent {

	@Override
	default String eventName() {
		return "TimeBarEvent";
	}

	void onCurrentTimeBarChanged(TimeBar bar);

	void onStartTimeBar(TimeBar bar);

	void onEndTimeBar(TimeBar bar);

}
