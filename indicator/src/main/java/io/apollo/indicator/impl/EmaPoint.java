package io.apollo.indicator.impl;

import java.time.Duration;

import io.horizon.definition.market.data.impl.BasicMarketData;
import io.horizon.definition.market.instrument.Instrument;
import io.horizon.definition.market.vector.TimePeriodSerial;
import io.mercury.common.collections.list.FixedLengthRecorder;

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
