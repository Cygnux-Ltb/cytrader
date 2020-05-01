package io.mercury.financial.instrument.stock;

import io.mercury.financial.instrument.Stock;
import io.mercury.financial.instrument.Symbol;

public final class ChinaStock extends Stock {

	protected ChinaStock(int instrumentId, String instrumentCode, Symbol symbol) {
		super(instrumentId, instrumentCode, symbol);
	}

	@Override
	public boolean isAvailableNow() {
		return false;
	}

}
