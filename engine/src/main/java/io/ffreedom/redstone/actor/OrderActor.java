package io.ffreedom.redstone.actor;

import org.slf4j.Logger;

import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.order.storage.OrderKeeper;

public class OrderActor {

	private static Logger logger = CommonLoggerFactory.getLogger(OrderActor.class);

	private static final OrderActor INSTANCE = new OrderActor();

	private OrderActor() {
	}

	public static void onOrder(Order order) {
		if (OrderKeeper.containsOrder(order.getOrdSysId()))
			OrderKeeper.updateOrder(order);
		else
			OrderKeeper.insertOrder(order);
	}

}
