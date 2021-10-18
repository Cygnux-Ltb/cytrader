package io.cygnus.indicator.impl;

import java.time.Duration;

import io.cygnus.indicator.impl.base.FixedPeriodIndicator.FixedPeriodPoint;
import io.horizon.market.data.impl.BasicMarketData;
import io.horizon.market.instrument.Instrument;
import io.mercury.common.collections.list.LongSlidingWindow;
import io.mercury.common.sequence.TimeWindow;

public abstract class MaPoint extends FixedPeriodPoint<BasicMarketData> {

	protected LongSlidingWindow historyPriceWindow;

	protected long avgPrice;

	protected long lastPrice;

	protected MaPoint(int index, Instrument instrument, Duration duration, TimeWindow timePeriod,
			LongSlidingWindow historyPriceWindow) {
		super(index, timePeriod);
		this.historyPriceWindow = historyPriceWindow;
	}

	public LongSlidingWindow getHistoryPriceWindow() {
		return historyPriceWindow;
	}

	public long getAvgPrice() {
		return avgPrice;
	}

	public long getLastPrice() {
		return lastPrice;
	}

}
