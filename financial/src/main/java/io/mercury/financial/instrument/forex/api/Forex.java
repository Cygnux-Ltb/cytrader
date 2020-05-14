package io.mercury.financial.instrument.forex.api;

import io.mercury.financial.instrument.BaseInstrument;
import io.mercury.financial.instrument.InstrumentType;
import io.mercury.financial.instrument.Symbol;

public abstract class Forex extends BaseInstrument {

	private long multiplier;

	protected Forex(int instrumentId, String instrumentCode, Symbol symbol) {
		super(instrumentId, instrumentCode, symbol);
	}

	@Override
	public InstrumentType type() {
		return InstrumentType.FOREX;
	}

	@Override
	public boolean isAvailableImmediately() {
		return true;
	}

	public long multiplier() {
		return multiplier;
	}

}
