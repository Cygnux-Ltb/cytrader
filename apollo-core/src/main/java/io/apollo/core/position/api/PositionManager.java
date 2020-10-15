package io.apollo.core.position.api;

import io.gemini.definition.order.ActualChildOrder;

public interface PositionManager<T extends Position> {

	void putPosition(T position);

	T getPosition(int accountId, int instrumentId);

	default void onChildOrder(int accountId, int instrumentId, ActualChildOrder order) {
		getPosition(accountId, instrumentId).updatePosition(order);
	}

}
