package io.redstone.engine.storage;

import java.util.Collection;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.ffreedom.common.collections.InitialCapacity;
import io.ffreedom.common.collections.MutableMaps;
import io.redstone.core.order.api.Order;

public class OrderBook {

	// 存储本OrderBook里的所有订单,以ordSysId索引
	private MutableLongObjectMap<Order> orders;

	// 存储本OrderBook里的所有活动状态的订单,以ordSysId索引
	private MutableLongObjectMap<Order> activeOrders;

	// 存储本OrderBook里的所有long订单,以ordSysId索引
	private MutableLongObjectMap<Order> longOrders;

	// 存储本OrderBook里的所有活动状态的long订单,以ordSysId索引
	private MutableLongObjectMap<Order> activeLongOrders;

	// 存储本OrderBook里的所有short订单,以ordSysId索引
	private MutableLongObjectMap<Order> shortOrders;

	// 存储本OrderBook里的所有活动状态的short订单,以ordSysId索引
	private MutableLongObjectMap<Order> activeShortOrders;

	private OrderBook(InitialCapacity capacity) {
		//添加取一半, 1/4, 1/8
//		this.orders = MutableMaps.newLongObjectHashMap(size);
//		this.activeOrders = MutableMaps.newLongObjectHashMap(size / 4);
//		this.longOrders = MutableMaps.newLongObjectHashMap(size / 2);
//		this.activeLongOrders = MutableMaps.newLongObjectHashMap(size / 8);
//		this.shortOrders = MutableMaps.newLongObjectHashMap(size / 2);
//		this.activeShortOrders = MutableMaps.newLongObjectHashMap(size / 8);
		this.orders = MutableMaps.newLongObjectHashMap(capacity);
		this.activeOrders = MutableMaps.newLongObjectHashMap(capacity);
		this.longOrders = MutableMaps.newLongObjectHashMap(capacity);
		this.activeLongOrders = MutableMaps.newLongObjectHashMap(capacity);
		this.shortOrders = MutableMaps.newLongObjectHashMap(capacity);
		this.activeShortOrders = MutableMaps.newLongObjectHashMap(capacity);
	}

	public static OrderBook newInstance() {
		return new OrderBook(InitialCapacity.L10_Size_1024);
	}

	public static OrderBook newInstance(InitialCapacity capacity) {
		return new OrderBook(capacity);
	}

	public Order putOrder(Order order) {
		switch (order.getSide().direction()) {
		case Long:
			longOrders.put(order.getOrdSysId(), order);
			activeLongOrders.put(order.getOrdSysId(), order);
			break;
		case Short:
			shortOrders.put(order.getOrdSysId(), order);
			activeShortOrders.put(order.getOrdSysId(), order);
			break;
		default:
			throw new RuntimeException(
					"OrderSysId : " + order.getOrdSysId() + ", OrdSide : " + order.getSide() + " -> is undefined.");
		}
		orders.put(order.getOrdSysId(), order);
		return activeOrders.put(order.getOrdSysId(), order);
	}

	public Order terminatedOrder(Order order) {
		switch (order.getSide().direction()) {
		case Long:
			activeLongOrders.remove(order.getOrdSysId());
			break;
		case Short:
			activeShortOrders.remove(order.getOrdSysId());
			break;
		default:
			throw new RuntimeException(
					"OrderSysId : " + order.getOrdSysId() + ", OrdSide : " + order.getSide() + " -> is undefined.");
		}
		return activeOrders.remove(order.getOrdSysId());
	}

	public boolean containsOrder(long ordSysId) {
		return orders.containsKey(ordSysId);
	}

	public Order getOrder(long orderSysId) {
		return orders.get(orderSysId);
	}

	public MutableLongObjectMap<Order> getOrders() {
		return orders;
	}

	public MutableLongObjectMap<Order> getActiveOrders() {
		return activeOrders;
	}

	public MutableLongObjectMap<Order> getLongOrders() {
		return longOrders;
	}

	public MutableLongObjectMap<Order> getActiveLongOrders() {
		return activeLongOrders;
	}

	public MutableLongObjectMap<Order> getShortOrders() {
		return shortOrders;
	}

	public MutableLongObjectMap<Order> getActiveShortOrders() {
		return activeShortOrders;
	}

	public Collection<Order> values() {
		return orders.values();
	}

	public Collection<Order> activeValues() {
		return activeOrders.values();
	}

	public Collection<Order> longValues() {
		return longOrders.values();
	}

	public Collection<Order> activeLongValues() {
		return activeLongOrders.values();
	}

	public Collection<Order> shortValues() {
		return shortOrders.values();
	}

	public Collection<Order> activeShortValues() {
		return activeShortOrders.values();
	}

}
