package io.ffreedom.redstone.core.position.api;

import io.ffreedom.redstone.core.order.api.Order;

public interface Position extends Comparable<Position> {

	int getAccountId();

	int getInstrumentId();

	long getCurrentQty();

	void setCurrentQty(long qty);

	long getTradeableQty();

	void setTradeableQty(long qty);

	void updatePosition(Order order);

}
