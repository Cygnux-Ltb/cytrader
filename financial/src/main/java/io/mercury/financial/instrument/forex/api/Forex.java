package io.mercury.financial.instrument.forex.api;

import io.mercury.financial.instrument.AbsInstrument;
import io.mercury.financial.instrument.InstrumentType;
import io.mercury.financial.instrument.Symbol;

public abstract class Forex extends AbsInstrument {

	private Symbol symbol;

	protected Forex(int instrumentId, String instrumentCode, Symbol symbol) {
		super(instrumentId, instrumentCode);
		this.symbol = symbol;
	}

	@Override
	public InstrumentType type() {
		return InstrumentType.FOREX;
	}

	@Override
	public boolean isAvailableImmediately() {
		return true;
	}

	@Override
	public Symbol symbol() {
		return symbol;
	}

}
