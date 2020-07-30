package io.mercury.financial.instrument.futures.impl;

import io.mercury.financial.instrument.futures.api.Futures;

public final class ChinaFutures extends Futures {

	private PriorityClose priorityClose;

	public ChinaFutures(ChinaFuturesSymbol symbol, int term) {
		super(symbol.acquireInstrumentId(term), symbol.code() + term, symbol);
		this.priorityClose = symbol.getPriorityClose();
	}

	@Override
	public PriorityClose getPriorityClose() {
		return priorityClose;
	}

	@Override
	public String fmtText() {
		return "";
	}

}
