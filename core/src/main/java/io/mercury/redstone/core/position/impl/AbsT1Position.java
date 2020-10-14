package io.mercury.redstone.core.position.impl;

public abstract class AbsT1Position extends AbsPosition {

	private int tradeableQty;

	public AbsT1Position(int accountId, int instrumentId, int tradeableQty) {
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
