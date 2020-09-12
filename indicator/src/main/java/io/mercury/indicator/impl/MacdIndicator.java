package io.mercury.indicator.impl.macd;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.indicator.impl.FixedPeriodIndicator;

public final class MacdIndicator extends FixedPeriodIndicator<MacdPoint, MacdEvent, BasicMarketData> {

	public MacdIndicator(Instrument instrument, TimePeriod timePeriod) {
		super(instrument, timePeriod);
	}


	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub
	}

}
