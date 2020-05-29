package io.mercury.financial.indicator.base;

import java.time.ZonedDateTime;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.financial.vector.TimePeriodSerial;

public abstract class FixedPeriodPoint extends BasePoint<TimePeriodSerial> {

	protected TimePeriod period;
	protected TimePeriodSerial serial;

	protected FixedPeriodPoint(int index, Instrument instrument, TimePeriod period, TimePeriodSerial serial) {
		super(index, instrument);
		this.period = period;
		this.serial = serial;
	}

	public TimePeriod getPeriod() {
		return period;
	}

	@Override
	public TimePeriodSerial serial() {
		return serial;
	}

	public ZonedDateTime startTime() {
		return serial.startTime();
	}

	public ZonedDateTime endTime() {
		return serial.endTime();
	}

}