package io.mercury.indicator.impl.macd;

import io.mercury.indicator.api.IndicatorEvent;

public interface MacdEvent extends IndicatorEvent {

	@Override
	default String eventName() {
		return "MacdEvent";
	}

}
