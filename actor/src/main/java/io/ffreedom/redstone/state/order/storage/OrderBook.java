package io.ffreedom.redstone.state.order.storage;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongObjectHashMap;

import io.ffreedom.redstone.core.order.Order;

public class OrderBook {

	private MutableLongObjectMap<Order> orderMap = LongObjectHashMap.newMap();

	private OrderSet longOrderSet = OrderSet.newInstance();

	private OrderSet shortOrderSet = OrderSet.newInstance();

	private OrderBook() {
	}

	public static OrderBook newInstance() {
		return new OrderBook();
	}

	public Order putOrder(Order order) {
		switch (order.getOrdSide()) {
		case BUY:
		case MARGIN_BUY:
			longOrderSet.addOrder(order);
			break;
		case SELL:
		case SHORT_SELL:
			shortOrderSet.addOrder(order);
			break;
		default:
			throw new RuntimeException("OrderSysId : " + order.getOrdSysId() + ", OrdSide : " + order.getOrdSide()
					+ " -> is undefined.");
		}
		return orderMap.put(order.getOrdSysId(), order);
	}

	public Order getOrder(long orderSysId) {
		return orderMap.get(orderSysId);
	}

	public OrderSet getLongOrderSet() {
		return longOrderSet;
	}

	public OrderSet getShortOrderSet() {
		return shortOrderSet;
	}

}
