package io.mercury.redstone.core.event;

import io.mercury.redstone.core.order.Order;

public interface OrderHandler {

	void onOrder(Order order);

}
