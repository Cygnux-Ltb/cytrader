package io.mercury.financial.instrument.futures.impl;

import io.mercury.financial.instrument.futures.api.Futures;

public final class ChinaFutures extends Futures {

	private PriorityClose priorityClose;

	public ChinaFutures(ChinaFuturesSymbol symbol, int term) {
		super(symbol.acquireInstrumentId(term), (symbol.name() + term).toLowerCase(), symbol);
		this.priorityClose = symbol.priorityClose();
	}

	@Override
	public PriorityClose priorityClose() {
		return priorityClose;
	}

	@Override
	public String fmtText() {
		// TODO Auto-generated method stub
		return null;
	}

}
