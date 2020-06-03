package io.mercury.financial.instrument;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

import io.mercury.financial.FinancialProduct;
import io.mercury.financial.vector.TradingPeriod;

public interface Symbol extends FinancialProduct {

	ImmutableSortedSet<TradingPeriod> tradingPeriodSet();

	Exchange exchange();

	PriceMultiplier priceMultiplier();

	String fmtText();

}
