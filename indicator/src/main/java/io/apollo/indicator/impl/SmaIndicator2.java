package io.apollo.indicator.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import io.apollo.indicator.impl.SmaIndicator.SmaEvent;
import io.apollo.indicator.impl.base.FixedPeriodIndicator;
import io.horizon.definition.market.data.impl.BasicMarketData;
import io.horizon.definition.market.instrument.Instrument;
import io.horizon.definition.market.vector.TimePeriodSerial;
import io.horizon.definition.market.vector.TradingPeriod;
import io.horizon.definition.pool.TradingPeriodPool;
import io.mercury.common.collections.list.FixedLengthRecorder;

public final class SmaIndicator2 extends FixedPeriodIndicator<SmaPoint, SmaEvent, BasicMarketData> {

	private FixedLengthRecorder historyPriceRecorder;

	// TODO
	public SmaIndicator2(Instrument instrument, Duration duration, int cycle) {
		super(instrument, duration, cycle);
		this.historyPriceRecorder = FixedLengthRecorder.newRecorder(cycle);
		TradingPeriod tradingPeriod = TradingPeriodPool.Singleton.getAfterTradingPeriod(instrument, LocalTime.now());
		LocalDate nowDate = LocalDate.now();
		ZoneId zoneId = instrument.symbol().exchange().zoneId();
		TimePeriodSerial timePeriod = TimePeriodSerial
				.newSerial(ZonedDateTime.of(nowDate, tradingPeriod.startTime(), zoneId), ZonedDateTime.of(nowDate,
						tradingPeriod.startTime().plusSeconds(duration.getSeconds()).minusNanos(1), zoneId), duration);
		currentPoint = SmaPoint.with(0, instrument, duration, timePeriod, cycle, historyPriceRecorder);
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
