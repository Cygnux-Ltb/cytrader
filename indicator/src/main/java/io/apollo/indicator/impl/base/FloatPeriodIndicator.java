package io.apollo.indicator.impl.base;

import io.apollo.indicator.IndicatorEvent;
import io.gemini.definition.market.data.api.MarketData;
import io.gemini.definition.market.instrument.Instrument;

public abstract class FloatPeriodIndicator<P extends FloatPeriodPoint<M>, E extends IndicatorEvent, M extends MarketData>
		extends BaseIndicator<P, E, M> {

	protected FloatPeriodIndicator(Instrument instrument) {
		super(instrument);
	}

}
