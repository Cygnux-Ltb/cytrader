package io.mercury.indicator.impl.base;

import java.time.ZonedDateTime;

import io.gemini.definition.market.data.api.MarketData;
import io.gemini.definition.market.vector.TimePeriodSerial;

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