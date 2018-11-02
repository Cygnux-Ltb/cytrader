package io.ffreedom.redstone.core.adaptor.dto;

public abstract class CancelOrder {

	private long orderSysId;

	public CancelOrder(long orderSysId) {
		super();
		this.orderSysId = orderSysId;
	}

	public long getOrderSysId() {
		return orderSysId;
	}

}
