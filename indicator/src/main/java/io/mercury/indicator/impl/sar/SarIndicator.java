package io.mercury.polaris.indicator.impl.sar;

import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.mercury.polaris.financial.vector.TimePeriod;
import io.mercury.polaris.indicator.base.BaseTimePeriodIndicator;
import io.mercury.polaris.indicator.events.SarEvent;

public final class SarIndicator extends BaseTimePeriodIndicator<SarPoint, SarEvent> {

	public SarIndicator(Instrument instrument, TimePeriod period) {
		super(instrument, period);
		this.period = period;
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}


}
