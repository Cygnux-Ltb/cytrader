package io.ffreedom.redstone.actor;

import org.slf4j.Logger;

import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.order.storage.OrderKeeper;

public class OrderActor {

	private static Logger logger = CommonLoggerFactory.getLogger(OrderActor.class);

	private OrderActor() {
	}

	private static final OrderActor singleton = new OrderActor();

	public static OrderActor getSingleton() {
		return singleton;
	}

	public void onOrder(Order order) {
		if (OrderKeeper.containsOrder(order.getOrdSysId()))
			OrderKeeper.updateOrder(order);
		else
			OrderKeeper.insertOrder(order);
	}

	public void onMarketData(BasicMarketData marketData) {
		// TODO
	}

}
