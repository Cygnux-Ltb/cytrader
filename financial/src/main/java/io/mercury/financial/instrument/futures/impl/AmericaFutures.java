package io.mercury.financial.instrument.futures.impl;

import io.mercury.financial.instrument.Symbol;
import io.mercury.financial.instrument.futures.api.Futures;

public final class AmericaFutures extends Futures {

	public AmericaFutures(int instrumentId, String instrumentCode, Symbol symbol) {
		super(instrumentId, instrumentCode, symbol);
	}

	@Override
	public String fmtText() {
		return "";
	}

}
