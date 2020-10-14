package io.mercury.indicator.impl;

import java.time.Duration;

import io.mercury.common.collections.list.FixedLengthRecorder;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriodSerial;

public final class EmaPoint extends MaPoint  {

	protected EmaPoint(int index, Instrument instrument, Duration duration, TimePeriodSerial timePeriod,
			FixedLengthRecorder historyPriceRecorder) {
		super(index, instrument, duration, timePeriod, historyPriceRecorder);
		// TODO Auto-generated constructor stub
	}



	@Override
	protected void handleMarketData0(BasicMarketData preMarketData) {
		// TODO Auto-generated method stub

	}

}
