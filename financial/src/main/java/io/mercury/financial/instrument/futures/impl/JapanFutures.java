package io.mercury.financial.instrument.futures.impl;

import io.mercury.financial.instrument.Symbol;
import io.mercury.financial.instrument.futures.api.Futures;

public class JapanFutures extends Futures {

	public JapanFutures(int id, String code, Symbol symbol) {
		super(id, code, symbol);
	}

	@Override
	public String fmtText() {
		// TODO Auto-generated method stub
		return null;
	}

}
