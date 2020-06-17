package io.mercury.financial.indicator.base;

import io.mercury.financial.indicator.api.IndicatorEvent;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;

public abstract class FloatPeriodIndicator<P extends FloatPeriodPoint<M>, M extends MarketData, E extends IndicatorEvent>
		extends BaseIndicator<P, M, E> {

	public FloatPeriodIndicator(Instrument instrument) {
		super(instrument);
	}

}
