package io.mercury.financial.indicator;

import io.mercury.financial.indicator.api.IndicatorEvent;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;

public abstract class FloatPeriodIndicator<P extends FloatPeriodPoint<M>, E extends IndicatorEvent, M extends MarketData>
		extends BaseIndicator<P, E, M> {

	protected FloatPeriodIndicator(Instrument instrument) {
		super(instrument);
	}

}
