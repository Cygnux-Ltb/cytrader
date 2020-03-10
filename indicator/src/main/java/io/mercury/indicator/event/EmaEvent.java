package io.mercury.indicator.event;

import io.mercury.indicator.api.IndicatorEvent;
import io.mercury.indicator.impl.ma.EmaPoint;

public interface EmaEvent extends IndicatorEvent {

	void onCurrentEmaPointAvgPriceChanged(EmaPoint point);

	void onStartEmaPoint(EmaPoint point);

	void onEndEmaPoint(EmaPoint point);

}
