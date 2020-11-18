package io.cygnus.indicator.impl;

import io.cygnus.indicator.impl.base.FixedPeriodPoint;
import io.horizon.definition.market.data.impl.BasicMarketData;
import io.horizon.definition.market.vector.TimePeriodSerial;

public final class MacdPoint extends FixedPeriodPoint<BasicMarketData> {

	private MacdPoint(int index, TimePeriodSerial timePeriod) {
		super(index, timePeriod);
	}

	@Override
	protected void handleMarketData0(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

}
