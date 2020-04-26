package io.mercury.indicator.event;

import io.mercury.indicator.api.IndicatorEvent;
import io.mercury.indicator.impl.bar.TimeBar;

public interface TimeBarsEvent extends IndicatorEvent {

	void onCurrentTimeBarChanged(TimeBar bar);

	void onStartTimeBar(TimeBar bar);

	void onEndTimeBar(TimeBar bar);

}
