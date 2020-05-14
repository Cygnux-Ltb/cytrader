package io.mercury.financial.instrument;

public abstract class Stock extends BaseInstrument {

	protected Stock(int instrumentId, String instrumentCode, Symbol symbol) {
		super(instrumentId, instrumentCode, symbol);
	}

	@Override
	public InstrumentType type() {
		return InstrumentType.STOCK;
	}

}
