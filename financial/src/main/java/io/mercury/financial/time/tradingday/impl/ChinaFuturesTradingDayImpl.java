package io.mercury.financial.time.tradingday.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.annotation.concurrent.ThreadSafe;

import io.mercury.financial.instrument.futures.ChinaFuturesSupporter;
import io.mercury.financial.time.tradingday.api.TradingDay;

/**
 * 在收盘后至15点05分之前启动获得的TradinDay将存在误差
 * 
 * @author yellow013
 *
 */
@Deprecated
@ThreadSafe
public final class ChinaFuturesTradingDayImpl implements TradingDay {

	public final static ChinaFuturesTradingDayImpl CURRENT = ChinaFuturesTradingDayImpl.with(LocalDateTime.now());

	private LocalDate date;

	private ChinaFuturesTradingDayImpl(LocalDateTime datetime) {
		this.date = ChinaFuturesSupporter.analyzeTradingDay(datetime);
	}

	public static ChinaFuturesTradingDayImpl with(LocalDateTime datetime) {
		return new ChinaFuturesTradingDayImpl(datetime);
	}

	public static ChinaFuturesTradingDayImpl with(LocalDate date) {
		return new ChinaFuturesTradingDayImpl(LocalDateTime.of(date, LocalTime.of(8, 0, 0)));
	}

	@Override
	public LocalDate getDate() {
		return date;
	}

	public static void main(String[] args) {

		TradingDay tradingDay = ChinaFuturesTradingDayImpl.CURRENT;
		System.out.println(tradingDay.getDate());

	}

}
