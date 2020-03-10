package io.mercury.polaris.indicator.impl.bollinger;

import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.mercury.polaris.financial.vector.TimePeriod;
import io.mercury.polaris.indicator.api.CalculationCycle;
import io.mercury.polaris.indicator.base.BaseTimePeriodIndicator;
import io.mercury.polaris.indicator.events.BollingerBandsEvent;

public final class BollingerBandsIndicator extends BaseTimePeriodIndicator<BollingerBandsPoint, BollingerBandsEvent> {

	public BollingerBandsIndicator(Instrument instrument, TimePeriod period, CalculationCycle cycle) {
		super(instrument, period, cycle);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {

	}

}
