package io.mercury.financial.indicator.specific.ma;

import io.mercury.financial.indicator.base.FixedPeriodIndicator;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriod;

public final class Ema extends FixedPeriodIndicator<EmaPoint, EmaEvent, BasicMarketData> {

	public Ema(Instrument instrument, TimePeriod period, int cycle) {
		super(instrument, period);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

}
