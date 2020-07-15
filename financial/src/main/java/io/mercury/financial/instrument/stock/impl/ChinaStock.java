package io.mercury.financial.instrument.stock.impl;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

import io.mercury.financial.instrument.Exchange;
import io.mercury.financial.instrument.PriceMultiplier;
import io.mercury.financial.instrument.stock.api.Stock;
import io.mercury.financial.vector.TradingPeriod;

public final class ChinaStock extends Stock {

	protected ChinaStock(int instrumentId, String instrumentCode) {
		super(instrumentId, instrumentCode);
	}

	@Override
	public boolean isAvailableImmediately() {
		return false;
	}

	@Override
	public String fmtText() {
		return "";
	}

	@Override
	public PriceMultiplier priceMultiplier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImmutableSortedSet<TradingPeriod> tradingPeriodSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exchange exchange() {
		// TODO Auto-generated method stub
		return Exchange.SHFE;
	}

}
