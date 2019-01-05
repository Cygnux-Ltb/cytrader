package io.ffreedom.redstone.core.order;

import org.eclipse.collections.api.set.ImmutableSet;

import io.ffreedom.common.collect.ECollections;
import io.ffreedom.redstone.core.order.base.OrdTimestamps;

public abstract class GroupOrder {

	private long groupOrdSysId;
	private OrdTimestamps orderTimestamps;

	private ImmutableSet<Order> orderSet;

	public GroupOrder(Order... orders) {
		this.orderSet = ECollections.newImmutableSet(orders);
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
