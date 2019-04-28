package io.ffreedom.redstone.core.position.impl;

import io.ffreedom.redstone.core.order.api.Order;

public class GenericT1Position extends AbsPosition {

	private long tradeableQty;

	public GenericT1Position(int accountId, int instrumentId, long tradeableQty) {
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

	@Override
	public void updatePosition(Order order) {
		// TODO Auto-generated method stub

	}

}
