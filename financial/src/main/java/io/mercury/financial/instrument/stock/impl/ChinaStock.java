package io.mercury.financial.instrument.stock.impl;

import io.mercury.financial.instrument.Symbol;
import io.mercury.financial.instrument.stock.api.Stock;

public final class ChinaStock extends Stock {

	protected ChinaStock(int instrumentId, String instrumentCode, Symbol symbol) {
		super(instrumentId, instrumentCode, symbol);
	}

	@Override
	public boolean isAvailableImmediately() {
		return false;
	}

	@Override
	public String fmtText() {
		// TODO Auto-generated method stub
		return null;
	}

}
