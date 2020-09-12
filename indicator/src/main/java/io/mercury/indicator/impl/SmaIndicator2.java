package io.mercury.indicator.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import io.mercury.common.collections.list.FixedLengthRecorder;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.time.TradingPeriodPool;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.financial.vector.TimePeriodSerial;
import io.mercury.financial.vector.TradingPeriod;
import io.mercury.indicator.impl.SmaIndicator.SmaEvent;
import io.mercury.indicator.impl.base.FixedPeriodIndicator;

public final class SmaIndicator2 extends FixedPeriodIndicator<SmaPoint, SmaEvent, BasicMarketData> {

	private FixedLengthRecorder historyPriceRecorder;

	// TODO
	public SmaIndicator2(Instrument instrument, TimePeriod period, int cycle) {
		super(instrument, period, cycle);
		this.historyPriceRecorder = FixedLengthRecorder.newRecorder(cycle);
		TradingPeriod tradingPeriod = TradingPeriodPool.Singleton.getAfterTradingPeriod(instrument, LocalTime.now());
		LocalDate nowDate = LocalDate.now();
		ZoneId zoneId = instrument.symbol().exchange().zoneId();
		TimePeriodSerial timePeriod = TimePeriodSerial
				.newWith(ZonedDateTime.of(nowDate, tradingPeriod.startTime(), zoneId), ZonedDateTime.of(nowDate,
						tradingPeriod.startTime().plusSeconds(period.seconds()).minusNanos(1), zoneId), period);
		currentPoint = SmaPoint.with(0, instrument, period, timePeriod, cycle, historyPriceRecorder);
	}

	public static SmaIndicator2 with(Instrument instrument, TimePeriod period, int cycle) {
		return new SmaIndicator2(instrument, period, cycle);
	}

	@Override
	public void onMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

}
