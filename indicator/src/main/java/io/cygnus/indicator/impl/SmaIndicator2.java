package io.cygnus.indicator.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import io.cygnus.indicator.impl.SmaIndicator.SmaEvent;
import io.cygnus.indicator.impl.base.FixedPeriodIndicator;
import io.horizon.market.data.impl.BasicMarketData;
import io.horizon.market.instrument.Instrument;
import io.horizon.market.pool.TradablePeriodPool;
import io.horizon.market.serial.TimePeriodSerial;
import io.horizon.market.serial.TradablePeriodSerial;
import io.mercury.common.collections.list.LongSlidingWindow;

public final class SmaIndicator2 extends FixedPeriodIndicator<SmaPoint, SmaEvent, BasicMarketData> {

	private LongSlidingWindow historyPriceWindow;

	// TODO
	public SmaIndicator2(Instrument instrument, Duration duration, int cycle) {
		super(instrument, duration, cycle);
		this.historyPriceWindow = new LongSlidingWindow(cycle);
		TradablePeriodSerial tradingPeriod = TradablePeriodPool.Singleton.getAfterTradingPeriod(instrument,
				LocalTime.now());
		LocalDate nowDate = LocalDate.now();
		ZoneOffset zoneOffset = instrument.getZoneOffset();
		TimePeriodSerial timePeriod = new TimePeriodSerial(
				ZonedDateTime.of(nowDate, tradingPeriod.getStartTime(), zoneOffset), ZonedDateTime.of(nowDate,
						tradingPeriod.getStartTime().plusSeconds(duration.getSeconds()).minusNanos(1), zoneOffset),
				duration);
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
