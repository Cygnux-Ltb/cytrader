package io.ffreedom.redstone.storage;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongObjectHashMap;

import io.ffreedom.redstone.core.order.Order;

public class OrderBook {

	// Map<ordSysId, order>
	private MutableLongObjectMap<Order> allOrderMap = LongObjectHashMap.newMap();

	private OrderSet longOrderSet = OrderSet.newLongOrderSet();

	private OrderSet shortOrderSet = OrderSet.newShortOrderSet();

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
		return allOrderMap.put(order.getOrdSysId(), order);
	}

	public Order getOrder(long orderSysId) {
		return allOrderMap.get(orderSysId);
	}

	public OrderSet getLongOrderSet() {
		return longOrderSet;
	}

	public OrderSet getShortOrderSet() {
		return shortOrderSet;
	}

}
