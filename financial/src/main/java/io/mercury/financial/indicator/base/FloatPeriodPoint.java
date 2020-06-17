package io.mercury.financial.indicator.base;

import java.time.ZonedDateTime;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;
import io.mercury.financial.vector.RandomTimeSerial;

public abstract class FloatPeriodPoint<M extends MarketData> extends BasePoint<RandomTimeSerial, M> {

	protected RandomTimeSerial timeSerial;

	protected FloatPeriodPoint(int index, Instrument instrument, RandomTimeSerial timeSerial) {
		super(index, instrument);
		this.timeSerial = timeSerial;
	}

	@Override
	public RandomTimeSerial serial() {
		return timeSerial;
	}

	public ZonedDateTime timePoint() {
		return timeSerial.timePoint();
	}

}