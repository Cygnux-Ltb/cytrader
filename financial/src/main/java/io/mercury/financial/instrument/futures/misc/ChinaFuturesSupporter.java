package io.mercury.financial.instrument.futures.misc;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import io.mercury.common.util.StringUtil;
import io.mercury.financial.instrument.futures.impl.ChinaFuturesSymbol;

public final class ChinaFuturesSupporter {

	private ChinaFuturesSupporter() {
	}

	public static final LocalTime TRADING_DAY_DIVIDING_LINE = LocalTime.of(15, 15);

	public static LocalDate analyzeTradingDay(LocalDateTime dateTime) {
		DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
		// 判断是否是夜盘
		if (isNightTrading(dateTime.toLocalTime())) {
			// 判断是否周五, 如果是加3天, 否则加1天
			if (dayOfWeek.equals(DayOfWeek.FRIDAY))
				return dateTime.plusDays(3).toLocalDate();
			else
				return dateTime.plusDays(1).toLocalDate();
		} else {
			// 判断是否周六, 如果是加2天, 否则不加.
			if (dayOfWeek.equals(DayOfWeek.SATURDAY))
				return dateTime.plusDays(2).toLocalDate();
			else
				return dateTime.toLocalDate();
		}
	}

	private static boolean isNightTrading(LocalTime time) {
		if (time.isAfter(TRADING_DAY_DIVIDING_LINE))
			return true;
		else
			return false;

	}

	public static String analyzeSymbolCode(String instrumentCode) {
		if (StringUtil.isNullOrEmpty(instrumentCode))
			return instrumentCode;
		return instrumentCode.replaceAll("[\\d]", "").trim();
	}

	public static int analyzeInstrumentTerm(String instrumentCode) {
		if (StringUtil.isNullOrEmpty(instrumentCode))
			return 0;
		return Integer.parseInt(instrumentCode.replaceAll("[^\\d]", "").trim());
	}

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);

		System.out.println(ChinaFuturesSymbol.AG.exchange().id());
		System.out.println(ChinaFuturesSymbol.AG.id());
		System.out.println(ChinaFuturesSymbol.AG.acquireInstrumentId(1906));

		System.out.println(analyzeSymbolCode("rb1901"));
		System.out.println(analyzeInstrumentTerm("rb1901"));
		ChinaFuturesSymbol of = ChinaFuturesSymbol.of(analyzeSymbolCode("rb1901"));
		
		System.out.println(of);
	}

}
