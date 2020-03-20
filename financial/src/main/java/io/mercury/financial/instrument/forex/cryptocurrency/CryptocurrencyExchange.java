package io.mercury.polaris.financial.instrument.forex.cryptocurrency;

import io.mercury.polaris.financial.instrument.Symbol;
import io.mercury.polaris.financial.instrument.forex.base.Forex;

public final class CryptocurrencyExchange extends Forex {

	protected CryptocurrencyExchange(int instrumentId, Symbol symbol) {
		super(instrumentId, symbol.code(), symbol);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String code() {
		// TODO Auto-generated method stub
		return null;
	}

}
