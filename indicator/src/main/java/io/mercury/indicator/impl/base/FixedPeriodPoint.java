package io.mercury.indicator.impl.base;

import java.time.ZonedDateTime;

import io.mercury.financial.market.api.MarketData;
import io.mercury.financial.vector.TimePeriodSerial;

public abstract class FixedPeriodPoint<M extends MarketData> extends BasePoint<TimePeriodSerial, M> {

	protected FixedPeriodPoint(int index, TimePeriodSerial serial) {
		super(index, serial);
	}

	public ZonedDateTime startTime() {
		return serial.startTime();
	}

	public ZonedDateTime endTime() {
		return serial.endTime();
	}

}