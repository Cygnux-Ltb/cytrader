package io.mercury.financial.indicator.specific.ma;

import io.mercury.financial.indicator.api.IndicatorEvent;

public interface SmaEvent extends IndicatorEvent {

	default String eventName() {
		return "SmaEvent";
	}

	void onCurrentPointAvgPriceChanged(SmaPoint point);

	void onStartSmaPoint(SmaPoint point);

	void onEndSmaPoint(SmaPoint point);

}
