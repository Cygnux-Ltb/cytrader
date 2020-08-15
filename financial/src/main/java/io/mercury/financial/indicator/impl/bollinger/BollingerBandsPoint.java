package io.mercury.financial.indicator.impl.bollinger;

import io.mercury.financial.indicator.FixedPeriodPoint;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriodSerial;

public final class BollingerBandsPoint extends FixedPeriodPoint<BasicMarketData> {

	private BollingerBandsPoint(int index, TimePeriodSerial timePeriod) {
		super(index, timePeriod);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {

	}

}
