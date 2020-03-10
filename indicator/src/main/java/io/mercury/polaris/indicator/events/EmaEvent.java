package io.mercury.polaris.indicator.events;

import io.mercury.polaris.indicator.api.IndicatorEvent;
import io.mercury.polaris.indicator.impl.ma.EmaPoint;

public interface EmaEvent extends IndicatorEvent {

	void onCurrentEmaPointAvgPriceChanged(EmaPoint point);

	void onStartEmaPoint(EmaPoint point);

	void onEndEmaPoint(EmaPoint point);

}
