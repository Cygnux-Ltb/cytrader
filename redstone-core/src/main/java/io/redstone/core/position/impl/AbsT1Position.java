package io.redstone.core.position.impl;

public abstract class AbsT1Position extends AbsPosition {

	private long tradeableQty;

	public AbsT1Position(int accountId, int instrumentId, long tradeableQty) {
		super(accountId, instrumentId);
		this.tradeableQty = tradeableQty;
	}

	@Override
	public long tradeableQty() {
		return tradeableQty;
	}

	@Override
	public void tradeableQty(long tradeableQty) {
		this.tradeableQty = tradeableQty;
	}

}
