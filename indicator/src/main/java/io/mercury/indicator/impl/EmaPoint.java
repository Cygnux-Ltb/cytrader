package io.mercury.indicator.impl;

import java.time.Duration;

import io.gemini.definition.market.data.impl.BasicMarketData;
import io.gemini.definition.market.instrument.Instrument;
import io.gemini.definition.market.vector.TimePeriodSerial;
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
