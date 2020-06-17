package io.mercury.financial.indicator.specific.ma;

import io.mercury.common.collections.list.FixedLengthRecorder;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.financial.vector.TimePeriodSerial;

public final class EmaPoint extends MaPoint  {

	protected EmaPoint(int index, Instrument instrument, TimePeriod period, TimePeriodSerial timePeriod,
			FixedLengthRecorder historyPriceRecorder) {
		super(index, instrument, period, timePeriod, historyPriceRecorder);
		// TODO Auto-generated constructor stub
	}



	@Override
	protected void handleMarketData(BasicMarketData preMarketData) {
		// TODO Auto-generated method stub

	}

}
