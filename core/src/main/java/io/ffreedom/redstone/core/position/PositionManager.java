package io.ffreedom.redstone.core.position;

import io.ffreedom.redstone.core.order.Order;

public interface PositionManager<T extends Position> {

	void putPosition(T position);

	T getPosition();

	void onOrder(Order order);

}
