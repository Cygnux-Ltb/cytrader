package io.apollo.indicator.impl;

import java.time.Duration;

import io.apollo.indicator.api.IndicatorEvent;
import io.apollo.indicator.impl.BollingerBandsIndicator.BollingerBandsEvent;
import io.apollo.indicator.impl.base.FixedPeriodIndicator;
import io.gemini.definition.market.data.impl.BasicMarketData;
import io.gemini.definition.market.instrument.Instrument;

public final class BollingerBandsIndicator extends FixedPeriodIndicator<BollingerBandsPoint, BollingerBandsEvent, BasicMarketData> {

	public BollingerBandsIndicator(Instrument instrument, Duration duration, int cycle) {
		super(instrument, duration, cycle);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {

	}
	
	public static interface BollingerBandsEvent extends IndicatorEvent {

		@Override
		default String eventName() {
			return "BollingerBandsEvent";
		}

	}


}
