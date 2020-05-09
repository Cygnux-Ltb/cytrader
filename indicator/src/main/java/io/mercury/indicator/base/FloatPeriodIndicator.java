package io.mercury.indicator.base;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.vector.RandomTimeSerial;
import io.mercury.indicator.api.IndicatorEvent;
import io.mercury.indicator.api.Point;

public abstract class FloatTimeIndicator<P extends Point<RandomTimeSerial>, E extends IndicatorEvent>
		extends BaseIndicator<P, E> {

	public FloatTimeIndicator(Instrument instrument) {
		super(instrument);
	}

}
