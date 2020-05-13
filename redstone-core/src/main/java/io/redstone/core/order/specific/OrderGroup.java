package io.redstone.core.order.specific;

import org.eclipse.collections.api.set.ImmutableSet;

import io.mercury.common.collections.ImmutableSets;
import io.redstone.core.order.Order;
import io.redstone.core.order.structure.OrdTimestamps;

public final class OrderGroup {

	private long groupOrdSysId;
	private OrdTimestamps orderTimestamps;

	private ImmutableSet<Order> orderSet;

	public OrderGroup(Order... orders) {
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
