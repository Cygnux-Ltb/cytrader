package io.mercury.indicator.impl.ma;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import io.mercury.indicator.api.CalculationCycle;
import io.mercury.indicator.base.BaseTimePeriodIndicator;
import io.mercury.indicator.event.SmaEvent;
import io.mercury.indicator.structure.FixedHistoryPriceRecorder;
import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.mercury.polaris.financial.time.TradingPeriodPool;
import io.mercury.polaris.financial.vector.TimePeriod;
import io.mercury.polaris.financial.vector.TimePeriodSerial;
import io.mercury.polaris.financial.vector.TradingPeriod;

public final class Sma2 extends BaseTimePeriodIndicator<SmaPoint, SmaEvent> {

	private FixedHistoryPriceRecorder historyPriceRecorder;

	public Sma2(Instrument instrument, TimePeriod period, CalculationCycle cycle) {
		super(instrument, period, cycle);
		this.historyPriceRecorder = FixedHistoryPriceRecorder.newRecorder(cycle);
		TradingPeriod tradingPeriod = TradingPeriodPool.Singleton.getAfterTradingPeriod(instrument, LocalTime.now());
		LocalDate nowDate = LocalDate.now();
		ZoneId zoneId = instrument.symbol().exchange().zoneId();
		TimePeriodSerial timePeriod = TimePeriodSerial
				.with(ZonedDateTime.of(nowDate, tradingPeriod.startTime(), zoneId), ZonedDateTime.of(nowDate,
						tradingPeriod.startTime().plusSeconds(period.seconds()).minusNanos(1), zoneId));
		currentPoint = SmaPoint.with(0, instrument, period, timePeriod, cycle, historyPriceRecorder);
	}

	public static Sma2 with(Instrument instrument, TimePeriod period, CalculationCycle cycle) {
		return new Sma2(instrument, period, cycle);
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
