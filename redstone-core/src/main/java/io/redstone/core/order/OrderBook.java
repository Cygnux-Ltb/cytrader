package io.redstone.core.order;

import java.util.Collection;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableMaps;

public final class OrderBook {

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

	private OrderBook(Capacity capacity) {
		// 添加取一半, 1/4, 1/8
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
		return new OrderBook(Capacity.L10_SIZE_1024);
	}

	public static OrderBook newInstance(Capacity capacity) {
		return new OrderBook(capacity);
	}

	public Order putOrder(Order order) {
		switch (order.trdDirection()) {
		case Long:
			longOrders.put(order.ordSysId(), order);
			activeLongOrders.put(order.ordSysId(), order);
			break;
		case Short:
			shortOrders.put(order.ordSysId(), order);
			activeShortOrders.put(order.ordSysId(), order);
			break;
		default:
			throw new RuntimeException(
					"orderSysId : " + order.ordSysId() + ", trdDirection : " + order.trdDirection() + " -> is invalid.");
		}
		orders.put(order.ordSysId(), order);
		return activeOrders.put(order.ordSysId(), order);
	}

	public Order terminatedOrder(Order order) {
		switch (order.trdDirection()) {
		case Long:
			activeLongOrders.remove(order.ordSysId());
			break;
		case Short:
			activeShortOrders.remove(order.ordSysId());
			break;
		default:
			throw new RuntimeException(
					"orderSysId : " + order.ordSysId() + ", trdDirection : " + order.trdDirection() + " -> is invalid.");
		}
		return activeOrders.remove(order.ordSysId());
	}

	public boolean containsOrder(long ordSysId) {
		return orders.containsKey(ordSysId);
	}

	public Order getOrder(long orderSysId) {
		return orders.get(orderSysId);
	}

	public MutableLongObjectMap<Order> orders() {
		return orders;
	}

	public MutableLongObjectMap<Order> activeOrders() {
		return activeOrders;
	}

	public MutableLongObjectMap<Order> longOrders() {
		return longOrders;
	}

	public MutableLongObjectMap<Order> activeLongOrders() {
		return activeLongOrders;
	}

	public MutableLongObjectMap<Order> shortOrders() {
		return shortOrders;
	}

	public MutableLongObjectMap<Order> activeShortOrders() {
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
