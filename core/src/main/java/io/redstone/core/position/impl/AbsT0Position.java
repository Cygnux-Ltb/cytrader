package io.redstone.core.position.impl;

public abstract class AbsT0Position extends AbsPosition {

	public AbsT0Position(int accountId, int instrumentId) {
		super(accountId, instrumentId);
	}

	@Override
	public long tradeableQty() {
		return currentQty;
	}

	@Override
	public void tradeableQty(long qty) {
		currentQty(qty);
	}

}
