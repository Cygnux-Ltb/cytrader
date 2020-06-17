package io.mercury.financial.indicator.base;

import io.mercury.financial.indicator.api.IndicatorEvent;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;
import io.mercury.financial.vector.TimePeriod;

public abstract class FixedPeriodIndicator<P extends FixedPeriodPoint<M>, M extends MarketData, E extends IndicatorEvent>
		extends BaseIndicator<P, M, E> {

	protected TimePeriod period;
	protected int cycle;

	public FixedPeriodIndicator(Instrument instrument, TimePeriod period) {
		this(instrument, period, 1);
	}

	public FixedPeriodIndicator(Instrument instrument, TimePeriod period, int cycle) {
		super(instrument);
		this.period = period;
		this.cycle = cycle;

	}

}
