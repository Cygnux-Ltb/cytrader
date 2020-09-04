package io.mercury.indicator.impl.ma;

import io.mercury.indicator.api.IndicatorEvent;

public interface EmaEvent extends IndicatorEvent {

	@Override
	default String eventName() {
		return "EmaEvent";
	}

	void onCurrentEmaPointAvgPriceChanged(EmaPoint point);

	void onStartEmaPoint(EmaPoint point);

	void onEndEmaPoint(EmaPoint point);

}
