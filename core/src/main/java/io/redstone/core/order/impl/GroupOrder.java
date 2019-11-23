package io.redstone.core.order.impl;

import org.eclipse.collections.api.set.ImmutableSet;

import io.ffreedom.common.collections.ImmutableSets;
import io.redstone.core.order.api.Order;
import io.redstone.core.order.structure.OrdTimestamps;

@Deprecated
public abstract class GroupOrder {

	private long groupOrdSysId;
	private OrdTimestamps orderTimestamps;

	private ImmutableSet<Order> orderSet;

	public GroupOrder(Order... orders) {
		this.orderSet = ImmutableSets.newSet(orders);
		this.orderTimestamps = OrdTimestamps.generate();
	}

	public long getGroupOrdSysId() {
		return groupOrdSysId;
	}

	public ImmutableSet<Order> getOrderSet() {
		return orderSet;
	}

	public OrdTimestamps getOrderTimestamps() {
		return orderTimestamps;
	}

}
