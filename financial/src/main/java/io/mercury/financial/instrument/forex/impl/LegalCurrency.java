package io.mercury.financial.instrument.forex.impl;

import io.mercury.financial.instrument.Symbol;
import io.mercury.financial.instrument.forex.api.Forex;

public class LegalCurrency extends Forex {

	protected LegalCurrency(int instrumentId, String instrumentCode, Symbol symbol) {
		super(instrumentId, instrumentCode, symbol);
	}

	@Override
	public String fmtText() {
		return null;
	}

}
