package io.ffreedom.redstone.core.order;

import io.ffreedom.redstone.core.order.enums.OrdStatus;

public final class OrderReport {

	/**
	 * mapping to order ordSysId
	 */
	private long ordSysId;

	/**
	 * report epoch milliseconds
	 */
	private long epochMilliseconds;

	/**
	 * order status of now report
	 */
	private OrdStatus ordStatus;

	/**
	 * broker return id
	 */
	private String brokerRtnId;

	/**
	 * filled quantity
	 */
	private double filledQty;

	/**
	 * leaves quantity
	 */
	private double leavesQty;

	/**
	 * order execute price
	 */
	private double executePrice;

	public long getOrdSysId() {
		return ordSysId;
	}

	public OrderReport setOrdSysId(long ordSysId) {
		this.ordSysId = ordSysId;
		return this;
	}

	public long getEpochMilliseconds() {
		return epochMilliseconds;
	}

	public OrderReport setEpochMilliseconds(long epochMilliseconds) {
		this.epochMilliseconds = epochMilliseconds;
		return this;
	}

	public OrdStatus getOrdStatus() {
		return ordStatus;
	}

	public OrderReport setOrdStatus(OrdStatus ordStatus) {
		this.ordStatus = ordStatus;
		return this;
	}

	public String getBrokerRtnId() {
		return brokerRtnId;
	}

	public OrderReport setBrokerRtnId(String brokerRtnId) {
		this.brokerRtnId = brokerRtnId;
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

	public double getExecutePrice() {
		return executePrice;
	}

	public OrderReport setExecutePrice(double executePrice) {
		this.executePrice = executePrice;
		return this;
	}

}
