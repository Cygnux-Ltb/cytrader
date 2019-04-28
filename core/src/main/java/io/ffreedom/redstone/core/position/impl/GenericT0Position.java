package io.ffreedom.redstone.core.position.impl;

import io.ffreedom.redstone.core.order.api.Order;

public class GenericT0Position extends AbsPosition {

	public GenericT0Position(int accountId, int instrumentId) {
		super(accountId, instrumentId);
	}

	@Override
	public long getTradeableQty() {
		return currentQty;
	}
	
	@Override
	public void setTradeableQty(long qty) {
		setCurrentQty(qty);
	}

	@Override
	public void updatePosition(Order order) {
		// TODO Auto-generated method stub

	}

}
