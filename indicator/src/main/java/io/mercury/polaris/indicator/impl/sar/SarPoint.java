package io.mercury.polaris.indicator.impl.sar;

import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.mercury.polaris.financial.vector.TimePeriod;
import io.mercury.polaris.financial.vector.TimePeriodSerial;
import io.mercury.polaris.indicator.base.TimePeriodPoint;

public final class SarPoint extends TimePeriodPoint<SarPoint> {

	private double pointValue;

	public SarPoint(int index, Instrument instrument, TimePeriod period, TimePeriodSerial timePeriod) {
		super(index, instrument, period, timePeriod);
		// TODO Auto-generated constructor stub
	}

	public double getPointValue() {
		return pointValue;
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

}
