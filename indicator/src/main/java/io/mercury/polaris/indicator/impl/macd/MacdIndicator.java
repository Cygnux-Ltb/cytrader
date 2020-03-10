package io.mercury.polaris.indicator.impl.macd;

import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.mercury.polaris.financial.vector.TimePeriod;
import io.mercury.polaris.indicator.base.BaseTimePeriodIndicator;
import io.mercury.polaris.indicator.events.MacdEvent;

public final class MacdIndicator extends BaseTimePeriodIndicator<MacdPoint, MacdEvent> {

	public MacdIndicator(Instrument instrument, TimePeriod timePeriod) {
		super(instrument, timePeriod);
	}


	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub
	}

}
