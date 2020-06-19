package io.mercury.financial.indicator.specific.bollinger;

import io.mercury.financial.indicator.base.FixedPeriodIndicator;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriod;

public final class BollingerBandsIndicator extends FixedPeriodIndicator<BollingerBandsPoint, BollingerBandsEvent, BasicMarketData> {

	public BollingerBandsIndicator(Instrument instrument, TimePeriod period, int cycle) {
		super(instrument, period, cycle);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {

	}

}
