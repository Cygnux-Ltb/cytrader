package io.apollo.core.position.impl;

public abstract class BasePositionT1 extends BasePosition {

	private int tradeableQty;

	public BasePositionT1(int accountId, int instrumentId, int tradeableQty) {
		super(accountId, instrumentId);
		this.tradeableQty = tradeableQty;
	}

	@Override
	public int tradeableQty() {
		return tradeableQty;
	}

	@Override
	public void setTradeableQty(int tradeableQty) {
		this.tradeableQty = tradeableQty;
	}

}
