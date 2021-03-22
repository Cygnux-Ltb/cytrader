package io.cygnus.indicator.impl;

import java.time.Duration;

import io.cygnus.indicator.impl.base.FixedPeriodPoint;
import io.horizon.structure.market.data.impl.BasicMarketData;
import io.horizon.structure.market.instrument.Instrument;
import io.horizon.structure.serial.TimePeriodSerial;
import io.mercury.common.collections.list.LongSlidingWindow;
import lombok.Getter;

public abstract class MaPoint extends FixedPeriodPoint<BasicMarketData> {

	@Getter
	protected LongSlidingWindow historyPriceWindow;

	@Getter
	protected long avgPrice;

	@Getter
	protected long lastPrice;

	protected MaPoint(int index, Instrument instrument, Duration duration, TimePeriodSerial timePeriod,
			LongSlidingWindow historyPriceWindow) {
		super(index, timePeriod);
		this.historyPriceWindow = historyPriceWindow;
	}

}
