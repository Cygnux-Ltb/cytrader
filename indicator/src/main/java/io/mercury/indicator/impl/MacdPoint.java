package io.mercury.indicator.impl;

import io.gemini.definition.market.data.impl.BasicMarketData;
import io.gemini.definition.market.vector.TimePeriodSerial;
import io.mercury.indicator.impl.base.FixedPeriodPoint;

public final class MacdPoint extends FixedPeriodPoint<BasicMarketData> {

	private MacdPoint(int index, TimePeriodSerial timePeriod) {
		super(index, timePeriod);
	}

	@Override
	protected void handleMarketData0(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

}
