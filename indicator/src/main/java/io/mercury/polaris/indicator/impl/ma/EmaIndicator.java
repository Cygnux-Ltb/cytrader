package io.mercury.polaris.indicator.impl.ma;

import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.mercury.polaris.financial.vector.TimePeriod;
import io.mercury.polaris.indicator.api.CalculationCycle;
import io.mercury.polaris.indicator.base.BaseTimePeriodIndicator;
import io.mercury.polaris.indicator.events.EmaEvent;

public final class EmaIndicator extends BaseTimePeriodIndicator<EmaPoint, EmaEvent> {

	public EmaIndicator(Instrument instrument, TimePeriod period, CalculationCycle cycle) {
		super(instrument, period);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}


}
