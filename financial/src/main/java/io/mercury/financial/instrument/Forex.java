package io.mercury.financial.instrument;

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
	public boolean isAvailableNow() {
		return true;
	}

	@Override
	public boolean isNakedShort() {
		return true;
	}

	public long multiplier() {
		return multiplier;
	}

}
