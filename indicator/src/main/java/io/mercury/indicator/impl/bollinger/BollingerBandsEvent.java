package io.mercury.indicator.impl.bollinger;

import io.mercury.indicator.api.IndicatorEvent;

public interface BollingerBandsEvent extends IndicatorEvent {

	@Override
	default String eventName() {
		return "BollingerBandsEvent";
	}

}
