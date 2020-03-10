package io.mercury.polaris.financial.time.tradingday.api;

import java.time.LocalDate;

@Deprecated
public interface TradingDay extends Comparable<TradingDay> {

	LocalDate getDate();

	@Override
	default int compareTo(TradingDay o) {
		return getDate().compareTo(o.getDate());
	}

}