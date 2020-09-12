package io.mercury.indicator.impl.bollinger;

import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriodSerial;
import io.mercury.indicator.impl.FixedPeriodPoint;

public final class BollingerBandsPoint extends FixedPeriodPoint<BasicMarketData> {

	private BollingerBandsPoint(int index, TimePeriodSerial timePeriod) {
		super(index, timePeriod);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {

	}

}
