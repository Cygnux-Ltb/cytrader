package io.mercury.polaris.indicator.base;

import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.vector.RandomTimeSerial;
import io.mercury.polaris.indicator.api.CalculationCycle;
import io.mercury.polaris.indicator.api.IndicatorEvent;
import io.mercury.polaris.indicator.api.Point;

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
