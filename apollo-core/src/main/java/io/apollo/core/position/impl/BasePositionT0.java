package io.apollo.core.position.impl;

public abstract class BasePositionT0 extends BasePosition {

	public BasePositionT0(int accountId, int instrumentId) {
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
