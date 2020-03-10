package io.mercury.indicator.event;

import io.mercury.indicator.api.IndicatorEvent;
import io.mercury.indicator.impl.ma.SmaPoint;

public interface SmaEvent extends IndicatorEvent {

	default String eventName() {
		return "SmaEvent";
	}

	void onCurrentPointAvgPriceChanged(SmaPoint point);

	void onStartSmaPoint(SmaPoint point);

	void onEndSmaPoint(SmaPoint point);

}
