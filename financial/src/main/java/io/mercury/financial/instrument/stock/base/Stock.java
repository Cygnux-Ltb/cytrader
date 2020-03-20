package io.mercury.financial.instrument.stock.base;

import io.mercury.financial.instrument.AbstractInstrument;
import io.mercury.financial.instrument.Symbol;

public abstract class Stock extends AbstractInstrument {

	protected Stock(int instrumentId, String instrumentCode, Symbol symbol) {
		super(instrumentId, instrumentCode, symbol);
	}

	@Override
	public InstrumentType type() {
		return InstrumentType.STOCK;
	}

}
