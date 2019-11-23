package io.redstone.core.position.api;

import io.redstone.core.order.api.Order;

public interface Position extends Comparable<Position> {

	int getAccountId();

	int getInstrumentId();

	long getCurrentQty();

	void setCurrentQty(long qty);

	long getTradeableQty();

	void setTradeableQty(long qty);

	void updatePosition(Order order);

}
