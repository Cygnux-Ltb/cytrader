package io.redstone.core.event;

import io.mercury.common.fsm.Signal;
import io.mercury.financial.market.api.MarketData;
import io.redstone.core.order.Order;
import io.redstone.core.order.structure.OrdReport;

public final class BusEvent {

	private ChannelType channelType;
	private BusEventType busEventType;

	private ControlEvent controlEvent;
	private MarketData marketData;
	private Signal signal;
	private Order order;
	private OrdReport orderReport;

	public ChannelType getChannelType() {
		return channelType;
	}

	public BusEvent setChannelType(ChannelType channelType) {
		this.channelType = channelType;
		return this;
	}

	public BusEventType getBusEventType() {
		return busEventType;
	}

	public BusEvent setBusEventType(BusEventType busEventType) {
		this.busEventType = busEventType;
		return this;
	}

	public ControlEvent getControlEvent() {
		return controlEvent;
	}

	public BusEvent setControlEvent(ControlEvent controlEvent) {
		this.controlEvent = controlEvent;
		return this;
	}

	public MarketData getMarketData() {
		return marketData;
	}

	public BusEvent setMarketData(MarketData marketData) {
		this.marketData = marketData;
		return this;
	}

	public Signal getSignal() {
		return signal;
	}

	public BusEvent setSignal(Signal signal) {
		this.signal = signal;
		return this;
	}

	public Order getOrder() {
		return order;
	}

	public BusEvent setOrder(Order order) {
		this.order = order;
		return this;
	}

	public OrdReport getOrderReport() {
		return orderReport;
	}

	public BusEvent setOrderReport(OrdReport orderReport) {
		this.orderReport = orderReport;
		return this;
	}

	public static enum ChannelType {
		InBound, Outbound
	}

	public static enum BusEventType {
		MarketData, Order, Signal, ControlEvent,
	}

}
