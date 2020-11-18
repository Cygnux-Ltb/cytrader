package io.apollo.indicator.impl;

import java.time.Duration;

import io.apollo.indicator.impl.base.FixedPeriodPoint;
import io.horizon.definition.market.data.impl.BasicMarketData;
import io.horizon.definition.market.instrument.Instrument;
import io.horizon.definition.market.vector.TimePeriodSerial;
import io.mercury.common.collections.list.FixedLengthRecorder;

public abstract class MaPoint extends FixedPeriodPoint<BasicMarketData> {

	protected FixedLengthRecorder historyPriceRecorder;
	protected long avgPrice;
	protected long lastPrice;

	protected MaPoint(int index, Instrument instrument, Duration duration, TimePeriodSerial timePeriod,
			FixedLengthRecorder historyPriceRecorder) {
		super(index,  timePeriod);
		this.historyPriceRecorder = historyPriceRecorder;
	}

	public double avgPrice() {
		return avgPrice;
	}

	public double lastPrice() {
		return lastPrice;
	}

	public FixedLengthRecorder historyPriceRecorder() {
		return historyPriceRecorder;
	}

}
