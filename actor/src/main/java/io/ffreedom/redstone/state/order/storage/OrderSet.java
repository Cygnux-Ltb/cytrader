package io.ffreedom.redstone.state.order.storage;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;

import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.order.enums.OrdSide;

public final class OrderSet {

	private MutableSortedSet<Order> mutableOrders = TreeSortedSet.newSet();

	private OrderSet() {
	}

	public static OrderSet newInstance() {
		return new OrderSet();
	}

	public boolean addOrder(Order order) {
		return mutableOrders.add(order);
	}

	public boolean removeOrder(Order order) {
		return mutableOrders.remove(order);
	}

	public ImmutableSortedSet<Order> immutableSet() {
		return mutableOrders.toImmutable();
	}

	public ImmutableSortedSet<Order> immutableSet(OrdSide ordSide) {
		return mutableOrders.select(order -> order.getOrdSide().equals(ordSide)).toImmutable();
	}

	public static void main(String[] args) {

	}

}
