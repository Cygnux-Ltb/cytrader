package io.mercury.financial.indicator.base;

import io.mercury.financial.indicator.api.IndicatorEvent;
import io.mercury.financial.instrument.Instrument;

public abstract class FloatPeriodIndicator<P extends FloatPeriodPoint, E extends IndicatorEvent>
		extends BaseIndicator<P, E> {

	public FloatPeriodIndicator(Instrument instrument) {
		super(instrument);
	}

}
