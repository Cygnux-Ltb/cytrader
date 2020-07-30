package io.mercury.financial.instrument;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

import io.mercury.financial.FinancialObject;
import io.mercury.financial.vector.TradingPeriod;

public interface Symbol extends FinancialObject {

	ImmutableSortedSet<TradingPeriod> tradingPeriodSet();

	Exchange exchange();

	PriceMultiplier getPriceMultiplier();

	String fmtText();

}
