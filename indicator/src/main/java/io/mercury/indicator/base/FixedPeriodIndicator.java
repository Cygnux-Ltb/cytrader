package io.mercury.indicator.base;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.indicator.api.IndicatorEvent;

public abstract class FixedPeriodIndicator<P extends FixedPeriodPoint, E extends IndicatorEvent>
		extends BaseIndicator<P, E> {

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
