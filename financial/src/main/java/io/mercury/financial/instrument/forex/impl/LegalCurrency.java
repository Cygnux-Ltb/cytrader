package io.mercury.financial.instrument.forex.impl;

import io.mercury.financial.instrument.Symbol;
import io.mercury.financial.instrument.forex.api.Forex;

public class LegalCurrency extends Forex {

	protected LegalCurrency(int instrumentId, String instrumentCode, Symbol symbol) {
		super(instrumentId, instrumentCode, symbol);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String fmtText() {
		// TODO Auto-generated method stub
		return null;
	}

}
