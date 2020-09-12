package io.mercury.indicator.impl.base;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;
import io.mercury.indicator.api.IndicatorEvent;

public abstract class FloatPeriodIndicator<P extends FloatPeriodPoint<M>, E extends IndicatorEvent, M extends MarketData>
		extends BaseIndicator<P, E, M> {

	protected FloatPeriodIndicator(Instrument instrument) {
		super(instrument);
	}

}
