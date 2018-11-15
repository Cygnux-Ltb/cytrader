package io.ffreedom.redstone.storage;

import java.util.Collection;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import io.ffreedom.common.collect.EclipseCollections;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.trade.enums.TrdDirection;

public final class OrderSet {

	private TrdDirection direction;
	private MutableLongObjectMap<Order> orders = EclipseCollections.newLongObjectHashMap();

	private OrderSet(TrdDirection direction) {
		this.direction = direction;
	}

	public static OrderSet newLongSet() {
		return new OrderSet(TrdDirection.Long);
	}

	public static OrderSet newShortSet() {
		return new OrderSet(TrdDirection.Short);
	}

	public TrdDirection getDirection() {
		return direction;
	}

	public Order put(Order order) {
		return orders.put(order.getOrdSysId(), order);
	}

	public Order remove(Order order) {
		return orders.remove(order.getOrdSysId());
	}

	public Order get(long ordSysId) {
		return orders.get(ordSysId);
	}

	public Collection<Order> getAll() {
		return orders.values();
	}

}
