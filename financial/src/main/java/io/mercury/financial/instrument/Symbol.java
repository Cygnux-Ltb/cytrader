package io.mercury.financial.instrument;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

import io.mercury.financial.FinancialObj;
import io.mercury.financial.vector.TradingPeriod;

public interface Symbol extends FinancialObj {

	ImmutableSortedSet<TradingPeriod> tradingPeriodSet();

	Exchange exchange();

	PriceMultiplier getPriceMultiplier();

	String fmtText();

}
