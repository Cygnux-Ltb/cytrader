package io.ffreedom.redstone.core.order;

import io.ffreedom.redstone.core.order.enums.OrdStatus;

public final class OrderReport {

	private long ordSysId;
	private OrdStatus ordStatus;
	/**
	 * 
	 */
	private String borkerRtnId;
	/**
	 * 
	 */
	private double filledQty;
	/**
	 * 
	 */
	private double leavesQty;

	private double execPrice;

	public long getOrdSysId() {
		return ordSysId;
	}

	public OrderReport setOrdSysId(long ordSysId) {
		this.ordSysId = ordSysId;
		return this;
	}

	public OrdStatus getOrdStatus() {
		return ordStatus;
	}

	public OrderReport setOrdStatus(OrdStatus ordStatus) {
		this.ordStatus = ordStatus;
		return this;
	}

	public String getBorkerRtnId() {
		return borkerRtnId;
	}

	public OrderReport setBorkerRtnId(String borkerRtnId) {
		this.borkerRtnId = borkerRtnId;
		return this;
	}

	public double getFilledQty() {
		return filledQty;
	}

	public OrderReport setFilledQty(double filledQty) {
		this.filledQty = filledQty;
		return this;
	}

	public double getLeavesQty() {
		return leavesQty;
	}

	public OrderReport setLeavesQty(double leavesQty) {
		this.leavesQty = leavesQty;
		return this;
	}

	public double getExecPrice() {
		return execPrice;
	}

	public OrderReport setExecPrice(double execPrice) {
		this.execPrice = execPrice;
		return this;
	}

}
