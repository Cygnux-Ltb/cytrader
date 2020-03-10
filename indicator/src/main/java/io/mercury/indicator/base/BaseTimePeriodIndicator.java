package io.mercury.polaris.indicator.base;

import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.vector.TimePeriod;
import io.mercury.polaris.financial.vector.TimePeriodSerial;
import io.mercury.polaris.indicator.api.CalculationCycle;
import io.mercury.polaris.indicator.api.IndicatorEvent;
import io.mercury.polaris.indicator.api.Point;

public abstract class BaseTimePeriodIndicator<P extends Point<TimePeriodSerial>, E extends IndicatorEvent>
		extends BaseIndicator<P, E> {

	protected TimePeriod period;
	protected CalculationCycle cycle;

	public BaseTimePeriodIndicator(Instrument instrument, TimePeriod period) {
		this(instrument, period, CalculationCycle.OnlyOne);
	}

	public BaseTimePeriodIndicator(Instrument instrument, TimePeriod period, CalculationCycle cycle) {
		super(instrument);
		this.period = period;
		this.cycle = cycle;
		
	}

}
