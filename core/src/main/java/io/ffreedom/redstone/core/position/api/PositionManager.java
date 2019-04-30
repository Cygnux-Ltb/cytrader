package io.ffreedom.redstone.core.position.api;

import io.ffreedom.redstone.core.order.api.Order;

public interface PositionManager<T extends Position> {

	void putPosition(T position);

	T getPosition(int accountId, int instrumentId);

	void onOrder(Order order);

}
