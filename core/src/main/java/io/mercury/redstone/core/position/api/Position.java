package io.mercury.redstone.core.position.api;

import io.mercury.redstone.core.order.Order;

public interface Position extends Comparable<Position> {

	int accountId();

	int instrumentId();

	int currentQty();

	void setCurrentQty(int qty);

	int tradeableQty();

	void setTradeableQty(int qty);

	void updatePosition(Order order);

}
