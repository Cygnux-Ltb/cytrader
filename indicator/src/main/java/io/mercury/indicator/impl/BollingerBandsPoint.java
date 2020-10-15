package io.mercury.indicator.impl;

import io.gemini.definition.market.data.impl.BasicMarketData;
import io.gemini.definition.market.vector.TimePeriodSerial;
import io.mercury.indicator.impl.base.FixedPeriodPoint;

public final class BollingerBandsPoint extends FixedPeriodPoint<BasicMarketData> {

	private BollingerBandsPoint(int index, TimePeriodSerial timePeriod) {
		super(index, timePeriod);
	}

	@Override
	protected void handleMarketData0(BasicMarketData marketData) {

	}

}
