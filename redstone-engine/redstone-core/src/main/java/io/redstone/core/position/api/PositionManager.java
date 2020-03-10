package io.redstone.core.position.api;

import io.redstone.core.order.impl.ChildOrder;

public interface PositionManager<T extends Position> {

	void putPosition(T position);

	T getPosition(int accountId, int instrumentId);

	default void onChildOrder(int accountId, int instrumentId, ChildOrder order) {
		getPosition(accountId, instrumentId).updatePosition(order);
	}

}
