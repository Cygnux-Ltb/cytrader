package io.mercury.indicator.impl.bollinger;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.indicator.impl.FixedPeriodIndicator;

public final class BollingerBandsIndicator extends FixedPeriodIndicator<BollingerBandsPoint, BollingerBandsEvent, BasicMarketData> {

	public BollingerBandsIndicator(Instrument instrument, TimePeriod period, int cycle) {
		super(instrument, period, cycle);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {

	}

}
