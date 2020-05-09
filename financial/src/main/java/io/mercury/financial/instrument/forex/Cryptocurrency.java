package io.mercury.financial.instrument.forex;

import io.mercury.financial.instrument.Forex;
import io.mercury.financial.instrument.Symbol;

public final class Cryptocurrency extends Forex {

	protected Cryptocurrency(int instrumentId, Symbol symbol) {
		super(instrumentId, symbol.code(), symbol);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String code() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String fmtText() {
		// TODO Auto-generated method stub
		return null;
	}

}
