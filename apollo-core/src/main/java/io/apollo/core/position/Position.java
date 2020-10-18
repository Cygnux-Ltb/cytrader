package io.apollo.core.position.api;

import io.gemini.definition.order.Order;

public interface Position extends Comparable<Position> {

	int accountId();

	int instrumentId();

	int currentQty();

	void setCurrentQty(int qty);

	int tradeableQty();

	void setTradeableQty(int qty);

	void updatePosition(Order order);

}
