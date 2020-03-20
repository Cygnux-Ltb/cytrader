package io.mercury.financial.time.tradingday;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.instrument.Symbol;
import io.mercury.financial.instrument.futures.ChinaFutures;
import io.mercury.financial.instrument.futures.ChinaFuturesSymbol;
import io.mercury.financial.time.tradingday.api.TradingDay;
import io.mercury.financial.time.tradingday.impl.ChinaFuturesTradingDayImpl;
import io.mercury.financial.time.tradingday.impl.CommonTradingDayImpl;

@Deprecated
public final class TradingDayKeeper {

	public static TradingDay get(Instrument instrument) {
		if (instrument instanceof ChinaFutures)
			return ChinaFuturesTradingDayImpl.CURRENT;
		else
			return CommonTradingDayImpl.CURRENT;
	}

	public static TradingDay get(Symbol symbol) {
		if (symbol instanceof ChinaFuturesSymbol)
			return ChinaFuturesTradingDayImpl.CURRENT;
		else
			return CommonTradingDayImpl.CURRENT;
	}

	public static TradingDay get(Instrument instrument, LocalDateTime datetime) {
		if (instrument instanceof ChinaFutures)
			return ChinaFuturesTradingDayImpl.with(datetime);
		else
			return CommonTradingDayImpl.with(datetime);
	}

	public static TradingDay get(Symbol symbol, LocalDateTime datetime) {
		if (symbol instanceof ChinaFuturesSymbol)
			return ChinaFuturesTradingDayImpl.with(datetime);
		else
			return CommonTradingDayImpl.with(datetime);
	}

	public static TradingDay get(Instrument instrument, LocalDate date) {
		if (instrument instanceof ChinaFutures)
			return ChinaFuturesTradingDayImpl.with(date);
		else
			return CommonTradingDayImpl.with(date);
	}

	public static TradingDay get(Symbol symbol, LocalDate date) {
		if (symbol instanceof ChinaFuturesSymbol)
			return ChinaFuturesTradingDayImpl.with(date);
		else
			return CommonTradingDayImpl.with(date);
	}

	public static void main(String[] args) {

	}

}
