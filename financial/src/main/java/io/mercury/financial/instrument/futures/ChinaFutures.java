package io.mercury.financial.instrument.futures;

import io.mercury.financial.instrument.Futures;

public final class ChinaFutures extends Futures {

	private PriorityCloseType priorityCloseType;

	public ChinaFutures(ChinaFuturesSymbol symbol, int term) {
		super(symbol.acquireInstrumentId(term), (symbol.name() + "" + term).toLowerCase(), symbol);
		this.priorityCloseType = symbol.priorityCloseType();
	}

	@Override
	public PriorityCloseType priorityCloseType() {
		return priorityCloseType;
	}

	@Override
	public String toString() {
		return code();
	}

	@Override
	public String fmtText() {
		// TODO Auto-generated method stub
		return null;
	}

}
