package io.mercury.indicator.base;

import java.time.ZonedDateTime;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.vector.RandomTimeSerial;

public abstract class FloatPeriodPoint extends BasePoint<RandomTimeSerial> {

	protected RandomTimeSerial timeSerial;

	protected FloatPeriodPoint(int index, Instrument instrument, RandomTimeSerial timeSerial) {
		super(index, instrument);
		this.timeSerial = timeSerial;
	}

	@Override
	public RandomTimeSerial serial() {
		return timeSerial;
	}

	public ZonedDateTime time() {
		return timeSerial.time();
	}

}