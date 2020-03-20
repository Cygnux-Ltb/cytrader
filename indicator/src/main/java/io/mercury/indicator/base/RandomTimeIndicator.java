package io.mercury.indicator.base;

import io.mercury.indicator.api.CalculationCycle;
import io.mercury.indicator.api.IndicatorEvent;
import io.mercury.indicator.api.Point;
import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.vector.RandomTimeSerial;

public abstract class BaseRandomTimeIndicator<P extends Point<RandomTimeSerial>, E extends IndicatorEvent>
		extends BaseIndicator<P, E> {

	protected CalculationCycle cycle;

	public BaseRandomTimeIndicator(Instrument instrument) {
		this(instrument, CalculationCycle.OnlyOne);
	}

	public BaseRandomTimeIndicator(Instrument instrument, CalculationCycle cycle) {
		super(instrument);
		this.cycle = cycle;
	}

}
