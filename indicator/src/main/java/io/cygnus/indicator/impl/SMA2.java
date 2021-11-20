package io.cygnus.indicator.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import io.cygnus.indicator.impl.SmaIndicator.SmaEvent;
import io.cygnus.indicator.impl.base.FixedPeriodIndicator;
import io.horizon.market.data.impl.BasicMarketData;
import io.horizon.market.instrument.Instrument;
import io.horizon.market.instrument.TradablePeriod;
import io.horizon.market.pool.TradablePeriodPool;
import io.mercury.common.collections.list.LongSlidingWindow;
import io.mercury.common.sequence.TimeWindow;

public final class SmaIndicator2 extends FixedPeriodIndicator<SmaPoint, SmaEvent, BasicMarketData> {

	private LongSlidingWindow historyPriceWindow;

	// TODO
	public SmaIndicator2(Instrument instrument, Duration duration, int cycle) {
		super(instrument, duration, cycle);
		this.historyPriceWindow = new LongSlidingWindow(cycle);
		TradablePeriod tradingPeriod = TradablePeriodPool.Singleton.nextTradingPeriod(instrument, LocalTime.now());
		LocalDate date = LocalDate.now();
		ZoneOffset offset = instrument.getZoneOffset();
		TimeWindow timePeriod = TimeWindow.with(LocalDateTime.of(date, tradingPeriod.getStart()),
				LocalDateTime.of(date, tradingPeriod.getStart()).plusSeconds(duration.getSeconds()), offset);
		currentPoint = SmaPoint.with(0, instrument, duration, timePeriod, cycle, historyPriceWindow);
	}

	public static SmaIndicator2 with(Instrument instrument, Duration duration, int cycle) {
		return new SmaIndicator2(instrument, duration, cycle);
	}

	@Override
	public void onMarketData(BasicMarketData marketData) {

	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {

	}

}
