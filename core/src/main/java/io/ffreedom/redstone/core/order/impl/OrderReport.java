package io.ffreedom.redstone.core.order.impl;

import io.ffreedom.redstone.core.order.enums.OrdStatus;

public final class OrderReport {

	/**
	 * mapping to order ordSysId
	 */
	private long ordSysId;

	/**
	 * report epoch milliseconds
	 */
	private long epochMillis;

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
	private long filledQty;

	/**
	 * leaves quantity
	 */
	// private long leavesQty;

	/**
	 * order execute price
	 */
	private double executePrice;

	public long getOrdSysId() {
		return ordSysId;
	}

	public long getEpochMillis() {
		return epochMillis;
	}

	public OrdStatus getOrdStatus() {
		return ordStatus;
	}

	public String getBrokerRtnId() {
		return brokerRtnId;
	}

	public long getFilledQty() {
		return filledQty;
	}

	public double getExecutePrice() {
		return executePrice;
	}

	public OrderReport setOrdSysId(long ordSysId) {
		this.ordSysId = ordSysId;
		return this;
	}

	public OrderReport setEpochMillis(long epochMillis) {
		this.epochMillis = epochMillis;
		return this;
	}

	public OrderReport setOrdStatus(OrdStatus ordStatus) {
		this.ordStatus = ordStatus;
		return this;
	}

	public OrderReport setBrokerRtnId(String brokerRtnId) {
		this.brokerRtnId = brokerRtnId;
		return this;
	}

	public OrderReport setFilledQty(long filledQty) {
		this.filledQty = filledQty;
		return this;
	}

	public OrderReport setExecutePrice(double executePrice) {
		this.executePrice = executePrice;
		return this;
	}

}
