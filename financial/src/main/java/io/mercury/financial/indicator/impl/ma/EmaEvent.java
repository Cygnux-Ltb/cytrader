package io.mercury.financial.indicator.impl.ma;

import io.mercury.financial.indicator.api.IndicatorEvent;

public interface EmaEvent extends IndicatorEvent {

	@Override
	default String eventName() {
		return "EmaEvent";
	}

	void onCurrentEmaPointAvgPriceChanged(EmaPoint point);

	void onStartEmaPoint(EmaPoint point);

	void onEndEmaPoint(EmaPoint point);

}
