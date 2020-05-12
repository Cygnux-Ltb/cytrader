package io.redstone.core.order;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableMaps;
import io.redstone.core.order.exception.OrderStatusException;

/**
 * 订单存储组件
 * 
 * @author yellow013
 *
 */
public final class OrderBook {

	/**
	 * 存储本OrderBook里的所有订单,以ordSysId索引
	 */
	private MutableLongObjectMap<Order> orders;

	/**
	 * 存储本OrderBook里的所有long订单,以ordSysId索引
	 */
	private MutableLongObjectMap<Order> longOrders;

	/**
	 * 存储本OrderBook里的所有short订单,以ordSysId索引
	 */
	private MutableLongObjectMap<Order> shortOrders;

	/**
	 * 存储本OrderBook里的所有活动状态的订单,以ordSysId索引
	 */
	private MutableLongObjectMap<Order> activeOrders;

	/**
	 * 存储本OrderBook里的所有活动状态的long订单,以ordSysId索引
	 */
	private MutableLongObjectMap<Order> activeLongOrders;

	/**
	 * 存储本OrderBook里的所有活动状态的short订单,以ordSysId索引
	 */
	private MutableLongObjectMap<Order> activeShortOrders;

	public OrderBook() {
		this(Capacity.L09_SIZE_512);
	}

	public OrderBook(Capacity capacity) {
		this.orders = MutableMaps.newLongObjectHashMap(capacity);
		this.longOrders = MutableMaps.newLongObjectHashMap(capacity.half());
		this.shortOrders = MutableMaps.newLongObjectHashMap(capacity.half());
		this.activeOrders = MutableMaps.newLongObjectHashMap(capacity.quarter());
		this.activeLongOrders = MutableMaps.newLongObjectHashMap(capacity.quarter());
		this.activeShortOrders = MutableMaps.newLongObjectHashMap(capacity.quarter());
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
			throw new OrderStatusException("orderSysId: [" + order.ordSysId() + "], trdDirection: ["
					+ order.trdDirection() + "] -> direction is invalid.");
		}
		orders.put(order.ordSysId(), order);
		return activeOrders.put(order.ordSysId(), order);
	}

	public Order finishOrder(Order order) throws OrderStatusException {
		switch (order.trdDirection()) {
		case Long:
			activeLongOrders.remove(order.ordSysId());
			break;
		case Short:
			activeShortOrders.remove(order.ordSysId());
			break;
		default:
			throw new OrderStatusException("orderSysId: [" + order.ordSysId() + "], trdDirection: ["
					+ order.trdDirection() + "] -> direction is invalid.");
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

}
