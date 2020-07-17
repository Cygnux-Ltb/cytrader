package io.mercury.financial.indicator.specific.bollinger;

import io.mercury.financial.indicator.api.IndicatorEvent;

public interface BollingerBandsEvent extends IndicatorEvent {

	@Override
	default String eventName() {
		return "BollingerBandsEvent";
	}

}
