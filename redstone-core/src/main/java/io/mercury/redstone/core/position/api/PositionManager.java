package io.mercury.redstone.core.position.api;

import io.mercury.redstone.core.order.ActChildOrder;

public interface PositionManager<T extends Position> {

	void putPosition(T position);

	T getPosition(int accountId, int instrumentId);

	default void onChildOrder(int accountId, int instrumentId, ActChildOrder order) {
		getPosition(accountId, instrumentId).updatePosition(order);
	}

}
