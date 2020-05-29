package io.mercury.indicator.impl.ma;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.indicator.base.FixedPeriodIndicator;
import io.mercury.indicator.event.EmaEvent;

public final class Ema extends FixedPeriodIndicator<EmaPoint, EmaEvent> {

	public Ema(Instrument instrument, TimePeriod period, int cycle) {
		super(instrument, period);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

}
