package io.mercury.indicator.impl.macd;

import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriodSerial;
import io.mercury.indicator.impl.FixedPeriodPoint;

public final class MacdPoint extends FixedPeriodPoint<BasicMarketData> {

	private MacdPoint(int index, TimePeriodSerial timePeriod) {
		super(index, timePeriod);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

}
