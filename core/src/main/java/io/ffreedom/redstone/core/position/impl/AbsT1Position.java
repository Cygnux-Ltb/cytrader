package io.ffreedom.redstone.core.position.impl;

public abstract class AbsT1Position extends AbsPosition {

	private long tradeableQty;

	public AbsT1Position(int accountId, int instrumentId, long tradeableQty) {
		super(accountId, instrumentId);
		this.tradeableQty = tradeableQty;
	}

	@Override
	public long getTradeableQty() {
		return tradeableQty;
	}

	public void setTradeableQty(long tradeableQty) {
		this.tradeableQty = tradeableQty;
	}

}
