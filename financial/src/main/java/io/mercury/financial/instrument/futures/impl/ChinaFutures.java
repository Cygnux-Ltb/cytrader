package io.mercury.financial.instrument.futures.impl;

import io.mercury.financial.instrument.futures.api.Futures;

public final class ChinaFutures extends Futures {

	private PriorityClose priorityClose;

	public ChinaFutures(ChinaFuturesSymbol symbol, int term) {
		this(symbol, term, String.valueOf(term));
	}

	public ChinaFutures(ChinaFuturesSymbol symbol, int term, String codeTail) {
		super(symbol.acquireInstrumentId(term), symbol.code() + codeTail, symbol);
		this.priorityClose = symbol.getPriorityClose();
	}

	@Override
	public PriorityClose getPriorityClose() {
		return priorityClose;
	}

	public static void main(String[] args) {

	}

}
