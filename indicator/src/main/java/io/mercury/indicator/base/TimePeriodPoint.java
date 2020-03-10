package io.mercury.polaris.indicator.base;

import java.time.ZonedDateTime;

import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.vector.TimePeriod;
import io.mercury.polaris.financial.vector.TimePeriodSerial;

public abstract class TimePeriodPoint<Y extends TimePeriodPoint<Y>> extends BasePoint<TimePeriodSerial> {

	protected TimePeriod period;
	protected TimePeriodSerial serial;

	protected TimePeriodPoint(int index, Instrument instrument, TimePeriod period, TimePeriodSerial serial) {
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