package io.mercury.indicator.base;

import io.mercury.financial.instrument.Instrument;
import io.mercury.indicator.api.IndicatorEvent;

public abstract class FloatPeriodIndicator<P extends FloatPeriodPoint, E extends IndicatorEvent>
		extends BaseIndicator<P, E> {

	public FloatPeriodIndicator(Instrument instrument) {
		super(instrument);
	}

}
