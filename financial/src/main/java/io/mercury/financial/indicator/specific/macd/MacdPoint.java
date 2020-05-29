package io.mercury.financial.indicator.specific.macd;

import io.mercury.financial.indicator.base.FixedPeriodPoint;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.financial.vector.TimePeriodSerial;

public final class MacdPoint extends FixedPeriodPoint {

	private MacdPoint(int index, Instrument instrument, TimePeriod period, TimePeriodSerial timePeriod) {
		super(index, instrument, period, timePeriod);
	}



	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

}
