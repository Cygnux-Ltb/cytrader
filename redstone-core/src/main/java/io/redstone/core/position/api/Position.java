package io.redstone.core.position.api;

import io.redstone.core.order.api.Order;

public interface Position extends Comparable<Position> {

	int accountId();

	int instrumentId();

	long currentQty();

	void currentQty(long qty);

	long tradeableQty();

	void tradeableQty(long qty);

	void updatePosition(Order order);

}
