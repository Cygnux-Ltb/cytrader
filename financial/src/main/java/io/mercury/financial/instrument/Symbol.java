package io.mercury.financial.instrument;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

import io.mercury.financial.vector.TradingPeriod;

public interface Symbol {

	int id();

	String code();

	ImmutableSortedSet<TradingPeriod> tradingPeriodSet();

	Exchange exchange();

	PriceMultiplier priceMultiplier();

}
