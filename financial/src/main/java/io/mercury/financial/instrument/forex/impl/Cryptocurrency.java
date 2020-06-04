package io.mercury.financial.instrument.forex.impl;

import io.mercury.financial.instrument.Symbol;
import io.mercury.financial.instrument.forex.api.Forex;

public final class Cryptocurrency extends Forex {

	protected Cryptocurrency(int instrumentId, Symbol symbol) {
		super(instrumentId, symbol.code(), symbol);
	}

	@Override
	public String fmtText() {
		return null;
	}

}
