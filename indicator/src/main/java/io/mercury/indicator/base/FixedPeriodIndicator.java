package io.mercury.indicator.base;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.financial.vector.TimePeriodSerial;
import io.mercury.indicator.api.IndicatorEvent;
import io.mercury.indicator.api.Point;

public abstract class FixedPeriodIndicator<P extends Point<TimePeriodSerial>, E extends IndicatorEvent>
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
