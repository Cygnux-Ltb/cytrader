package io.ffreedom.redstone.core.order;

import org.eclipse.collections.api.set.ImmutableSet;

import io.ffreedom.common.collect.ImmutableSets;
import io.ffreedom.redstone.core.order.api.Order;
import io.ffreedom.redstone.core.order.structure.OrdTimestamps;

public abstract class GroupOrder {

	private long groupOrdSysId;
	private OrdTimestamps orderTimestamps;

	private ImmutableSet<Order> orderSet;

	public GroupOrder(Order... orders) {
		this.orderSet = ImmutableSets.newImmutableSet(orders);
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
