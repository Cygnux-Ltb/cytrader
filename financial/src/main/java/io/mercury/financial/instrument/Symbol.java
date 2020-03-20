package io.mercury.polaris.financial.instrument;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

import io.mercury.polaris.financial.vector.TradingPeriod;

public interface Symbol {

	int id();
	
	String code();

	ImmutableSortedSet<TradingPeriod> tradingPeriodSet();

	Exchange exchange();

}
