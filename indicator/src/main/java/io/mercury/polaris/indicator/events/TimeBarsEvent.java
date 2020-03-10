package io.mercury.polaris.indicator.events;

import io.mercury.polaris.indicator.api.IndicatorEvent;
import io.mercury.polaris.indicator.impl.bar.TimeBar;

public interface TimeBarsEvent extends IndicatorEvent {

	void onCurrentTimeBarChanged(TimeBar bar);

	void onStartTimeBar(TimeBar bar);

	void onEndTimeBar(TimeBar bar);

}
