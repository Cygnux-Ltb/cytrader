package io.mercury.indicator.impl.ma;

import io.mercury.indicator.api.IndicatorEvent;

public interface SmaEvent extends IndicatorEvent {

	default String eventName() {
		return "SmaEvent";
	}

	void onCurrentPointAvgPriceChanged(SmaPoint point);

	void onStartSmaPoint(SmaPoint point);

	void onEndSmaPoint(SmaPoint point);

}
