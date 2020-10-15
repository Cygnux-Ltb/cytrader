package io.apollo.core.position.impl;

public abstract class AbsT0Position extends AbsPosition {

	public AbsT0Position(int accountId, int instrumentId) {
		super(accountId, instrumentId);
	}

	@Override
	public int tradeableQty() {
		return currentQty;
	}

	@Override
	public void setTradeableQty(int qty) {
		setCurrentQty(qty);
	}

}
