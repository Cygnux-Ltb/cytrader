package io.ffreedom.redstone.core.position;

import io.ffreedom.redstone.core.order.Order;

public interface Position extends Comparable<Position> {

	int getInstrumentId();

	double getCurrentQty();

	void setCurrentQty(double qty);

	void updatePosition(Order order);

}
