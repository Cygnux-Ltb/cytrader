package io.mercury.indicator.impl;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.indicator.api.IndicatorEvent;
import io.mercury.indicator.impl.BollingerBandsIndicator.BollingerBandsEvent;
import io.mercury.indicator.impl.base.FixedPeriodIndicator;

public final class BollingerBandsIndicator extends FixedPeriodIndicator<BollingerBandsPoint, BollingerBandsEvent, BasicMarketData> {

	public BollingerBandsIndicator(Instrument instrument, TimePeriod period, int cycle) {
		super(instrument, period, cycle);
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
