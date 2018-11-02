package io.ffreedom.redstone.core.adaptor.dto;

import io.ffreedom.financial.Instrument;
import io.ffreedom.redstone.core.order.OrdQtyPrice;
import io.ffreedom.redstone.core.order.enums.OrdSide;

public final class NewOrder {

	private long orderSysId;
	private Instrument instrument;
	private OrdQtyPrice ordQtyPrice;
	private OrdSide ordSide;

	public NewOrder(long orderSysId, Instrument instrument, OrdQtyPrice ordQtyPrice, OrdSide ordSide) {
		super();
		this.orderSysId = orderSysId;
		this.instrument = instrument;
		this.ordQtyPrice = ordQtyPrice;
		this.ordSide = ordSide;
	}

	public long getOrderSysId() {
		return orderSysId;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public OrdQtyPrice getOrdQtyPrice() {
		return ordQtyPrice;
	}

	public OrdSide getOrdSide() {
		return ordSide;
	}

}
