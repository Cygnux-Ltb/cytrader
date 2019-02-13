package io.ffreedom.redstone.core.event;

import io.ffreedom.polaris.market.MarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.order.OrderReport;
import io.ffreedom.redstone.core.trade.Signal;

public final class BusEvent {

	private ChannelType channelType;
	private BusEventType busEventType;

	private ControlEvent controlEvent;
	private MarketData marketData;
	private Signal signal;
	private Order order;
	private OrderReport orderReport;

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

	public OrderReport getOrderReport() {
		return orderReport;
	}

	public BusEvent setOrderReport(OrderReport orderReport) {
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
