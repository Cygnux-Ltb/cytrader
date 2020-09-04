package io.mercury.financial.indicator;

import io.mercury.financial.indicator.api.IndicatorEvent;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;
import io.mercury.financial.vector.TimePeriod;

public abstract class FixedPeriodIndicator<P extends FixedPeriodPoint<M>, E extends IndicatorEvent, M extends MarketData>
		extends BaseIndicator<P, E, M> {

	protected TimePeriod period;
	protected int cycle;

	/**
	 * 
	 * @param instrument
	 * @param period
	 */
	public FixedPeriodIndicator(Instrument instrument, TimePeriod period) {
		this(instrument, period, 1);
	}

	/**
	 * 
	 * @param instrument
	 * @param period
	 * @param cycle
	 */
	public FixedPeriodIndicator(Instrument instrument, TimePeriod period, int cycle) {
		super(instrument);
		this.period = period;
		this.cycle = cycle;

	}

}
