package io.cygnus.indicator.impl.base;

import java.time.ZonedDateTime;

import io.horizon.market.data.MarketData;
import io.horizon.market.serial.TimePeriodSerial;

public abstract class FixedPeriodPoint<M extends MarketData> extends BasePoint<TimePeriodSerial, M> {

	protected FixedPeriodPoint(int index, TimePeriodSerial serial) {
		super(index, serial);
	}

	public ZonedDateTime getStartTime() {
		return serial.getStartTime();
	}

	public ZonedDateTime getEndTime() {
		return serial.getEndTime();
	}

}