package io.mercury.financial.instrument.stock.api;

import io.mercury.financial.instrument.AbsInstrument;
import io.mercury.financial.instrument.InstrumentType;
import io.mercury.financial.instrument.PriceMultiplier;
import io.mercury.financial.instrument.Symbol;

public abstract class Stock extends AbsInstrument implements Symbol {

	protected Stock(int instrumentId, String instrumentCode) {
		super(instrumentId, instrumentCode);
	}

	@Override
	public InstrumentType type() {
		return InstrumentType.STOCK;
	}

	@Override
	public PriceMultiplier getPriceMultiplier() {
		// TODO Auto-generated method stub
		return super.getPriceMultiplier();
	}

	@Override
	public Symbol symbol() {
		return this;
	}

}
