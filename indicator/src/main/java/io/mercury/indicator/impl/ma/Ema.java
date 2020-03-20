package io.mercury.indicator.impl.ma;

import io.mercury.indicator.api.CalculationCycle;
import io.mercury.indicator.base.BaseTimePeriodIndicator;
import io.mercury.indicator.event.EmaEvent;
import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.mercury.polaris.financial.vector.TimePeriod;

public final class EmaIndicator extends BaseTimePeriodIndicator<EmaPoint, EmaEvent> {

	public EmaIndicator(Instrument instrument, TimePeriod period, CalculationCycle cycle) {
		super(instrument, period);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}


}
