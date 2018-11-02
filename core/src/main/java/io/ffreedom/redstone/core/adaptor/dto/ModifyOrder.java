package io.ffreedom.redstone.core.adaptor.dto;

import io.ffreedom.redstone.core.order.OrdQtyPrice;

public abstract class ModifyOrder {

	private String orderSysId;
	private OrdQtyPrice newOrdQtyPrice;

	public ModifyOrder(String orderSysId, OrdQtyPrice newOrdQtyPrice) {
		super();
		this.orderSysId = orderSysId;
		this.newOrdQtyPrice = newOrdQtyPrice;
	}

	public String getOrderSysId() {
		return orderSysId;
	}

	public OrdQtyPrice getNewOrdQtyPrice() {
		return newOrdQtyPrice;
	}

	

}
