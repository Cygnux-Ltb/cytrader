package io.mercury.redstone.core.order.specific;

import org.eclipse.collections.api.set.ImmutableSet;

import io.mercury.common.collections.ImmutableSets;
import io.mercury.redstone.core.order.Order;
import io.mercury.redstone.core.order.structure.OrdTimestamp;

public final class OrderGroup {

	private long groupOrdSysId;
	private OrdTimestamp ordTimestamp;

	private ImmutableSet<Order> orderSet;

	public OrderGroup(Order... orders) {
		this.orderSet = ImmutableSets.newSet(orders);
		this.ordTimestamp = OrdTimestamp.generate();
	}

	public long getGroupOrdSysId() {
		return groupOrdSysId;
	}

	public ImmutableSet<Order> getOrderSet() {
		return orderSet;
	}

	public OrdTimestamp getOrderTimestamps() {
		return ordTimestamp;
	}

}
