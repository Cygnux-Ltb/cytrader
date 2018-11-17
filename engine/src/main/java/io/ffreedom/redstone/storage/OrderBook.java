package io.ffreedom.redstone.storage;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongObjectHashMap;

import io.ffreedom.redstone.core.order.Order;

public class OrderBook {

	// Map<ordSysId, order>
	private MutableLongObjectMap<Order> orderMap = LongObjectHashMap.newMap();

	private OrderSet longOrderSet = OrderSet.newLongSet();

	private OrderSet shortOrderSet = OrderSet.newShortSet();

	private OrderBook() {
	}

	public static OrderBook newInstance() {
		return new OrderBook();
	}

	public Order saveOrder(Order order) {
		switch (order.getSide().direction()) {
		case Long:
			longOrderSet.put(order);
			break;
		case Short:
			shortOrderSet.put(order);
			break;
		default:
			throw new RuntimeException(
					"OrderSysId : " + order.getOrdSysId() + ", OrdSide : " + order.getSide() + " -> is undefined.");
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
