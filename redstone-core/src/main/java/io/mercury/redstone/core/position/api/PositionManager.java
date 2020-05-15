package io.mercury.redstone.core.position.api;

import io.mercury.redstone.core.order.specific.ChildOrder;

public interface PositionManager<T extends Position> {

	void putPosition(T position);

	T getPosition(int accountId, int instrumentId);

	default void onChildOrder(int accountId, int instrumentId, ChildOrder order) {
		getPosition(accountId, instrumentId).updatePosition(order);
	}

}
