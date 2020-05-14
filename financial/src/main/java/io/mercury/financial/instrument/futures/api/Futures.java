package io.mercury.financial.instrument.futures.api;

import io.mercury.financial.instrument.BaseInstrument;
import io.mercury.financial.instrument.InstrumentType;
import io.mercury.financial.instrument.Symbol;

public abstract class Futures extends BaseInstrument {

	public Futures(int instrumentId, String instrumentCode, Symbol symbol) {
		super(instrumentId, instrumentCode, symbol);
	}

	@Override
	public InstrumentType type() {
		return InstrumentType.FUTURES;
	}

	@Override
	public boolean isAvailableImmediately() {
		return true;
	}

}
