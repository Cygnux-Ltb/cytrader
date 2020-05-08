package io.mercury.indicator.base;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.vector.RandomTimeSerial;
import io.mercury.indicator.api.IndicatorEvent;
import io.mercury.indicator.api.Point;

public abstract class RandomTimeIndicator<P extends Point<RandomTimeSerial>, E extends IndicatorEvent>
		extends BaseIndicator<P, E> {

	public RandomTimeIndicator(Instrument instrument) {
		super(instrument);
	}

}
