package io.mercury.indicator.impl.base;

import java.time.ZonedDateTime;

import io.mercury.financial.market.api.MarketData;
import io.mercury.financial.vector.TimePointSerial;

public abstract class FloatPeriodPoint<M extends MarketData> extends BasePoint<TimePointSerial, M> {

	protected FloatPeriodPoint(int index, TimePointSerial serial) {
		super(index, serial);
	}

	public ZonedDateTime timePoint() {
		return serial.timePoint();
	}

}