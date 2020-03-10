package io.mercury.polaris.indicator.impl.bollinger;

import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.mercury.polaris.financial.vector.TimePeriod;
import io.mercury.polaris.financial.vector.TimePeriodSerial;
import io.mercury.polaris.indicator.base.TimePeriodPoint;

public final class BollingerBandsPoint extends TimePeriodPoint<BollingerBandsPoint> {

	private BollingerBandsPoint(int index, Instrument instrument, TimePeriod period, TimePeriodSerial timePeriod) {
		super(index, instrument, period, timePeriod);
	}



	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

}
