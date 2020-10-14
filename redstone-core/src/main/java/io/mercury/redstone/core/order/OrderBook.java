package io.mercury.redstone.core.order;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableMaps;
import io.mercury.redstone.core.order.exception.OrdStatusException;

/**
 * 用于存储订单的组件
 * 
 * @author yellow013
 *
 */
public final class OrderBook {

	/**
	 * 存储本OrderBook里的所有订单,以uniqueId索引
	 */
	private MutableLongObjectMap<Order> orders;

	/**
	 * 存储本OrderBook里的所有long订单,以uniqueId索引
	 */
	private MutableLongObjectMap<Order> longOrders;

	/**
	 * 存储本OrderBook里的所有short订单,以uniqueId索引
	 */
	private MutableLongObjectMap<Order> shortOrders;

	/**
	 * 存储本OrderBook里的所有活动状态的订单,以uniqueId索引
	 */
	private MutableLongObjectMap<Order> activeOrders;

	/**
	 * 存储本OrderBook里的所有活动状态的long订单,以uniqueId索引
	 */
	private MutableLongObjectMap<Order> activeLongOrders;

	/**
	 * 存储本OrderBook里的所有活动状态的short订单,以uniqueId索引
	 */
	private MutableLongObjectMap<Order> activeShortOrders;

	/**
	 * 
	 */
	public OrderBook() {
		this(Capacity.L08_SIZE_256);
	}

	/**
	 * 
	 * @param capacity
	 */
	public OrderBook(Capacity capacity) {
		this.orders = MutableMaps.newLongObjectHashMap(capacity);
		this.longOrders = MutableMaps.newLongObjectHashMap(capacity.half());
		this.shortOrders = MutableMaps.newLongObjectHashMap(capacity.half());
		this.activeOrders = MutableMaps.newLongObjectHashMap(capacity.quarter());
		this.activeLongOrders = MutableMaps.newLongObjectHashMap(capacity.quarter());
		this.activeShortOrders = MutableMaps.newLongObjectHashMap(capacity.quarter());
	}

	public Order putOrder(Order order) {
		switch (order.direction()) {
		case Long:
			longOrders.put(order.uniqueId(), order);
			activeLongOrders.put(order.uniqueId(), order);
			break;
		case Short:
			shortOrders.put(order.uniqueId(), order);
			activeShortOrders.put(order.uniqueId(), order);
			break;
		default:
			throw new IllegalStateException("uniqueId: [" + order.uniqueId() + "], direction is invalid");
		}
		orders.put(order.uniqueId(), order);
		return activeOrders.put(order.uniqueId(), order);
	}

	public Order finishOrder(Order order) throws OrdStatusException {
		switch (order.direction()) {
		case Long:
			activeLongOrders.remove(order.uniqueId());
			break;
		case Short:
			activeShortOrders.remove(order.uniqueId());
			break;
		case Invalid:
			throw new OrdStatusException("uniqueId: [" + order.uniqueId() + "], direction is invalid.");
		}
		return activeOrders.remove(order.uniqueId());
	}

	public boolean containsOrder(long uniqueId) {
		return orders.containsKey(uniqueId);
	}

	public Order getOrder(long uniqueId) {
		return orders.get(uniqueId);
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

}
