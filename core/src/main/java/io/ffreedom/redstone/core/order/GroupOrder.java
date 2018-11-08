package io.ffreedom.redstone.core.order;

import org.eclipse.collections.api.set.ImmutableSet;

import io.ffreedom.common.collect.EclipseCollections;

public abstract class GroupOrder {

	private long groupOrderId;
	private OrdTimestamps orderTimestamps;

	private ImmutableSet<Order> orderSet;

	public GroupOrder(Order... orders) {
		this.orderSet = EclipseCollections.immutableSet(orders);
	}

	public long getGroupOrderId() {
		return groupOrderId;
	}

	public ImmutableSet<Order> getOrderSet() {
		return orderSet;
	}

	public OrdTimestamps getOrderTimestamps() {
		return orderTimestamps;
	}

}
