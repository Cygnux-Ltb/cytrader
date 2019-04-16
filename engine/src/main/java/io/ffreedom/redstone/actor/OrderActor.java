package io.ffreedom.redstone.actor;

import org.slf4j.Logger;

import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.core.order.api.Order;
import io.ffreedom.redstone.storage.OrderKeeper;

/**
 * 统一管理订单<br>
 * 1对订单止损进行管理<br>
 * 2...<br>
 * 
 * @author yellow013
 */
public class OrderActor {

	private Logger logger = CommonLoggerFactory.getLogger(OrderActor.class);

	private OrderActor() {
	}

	public static final OrderActor Singleton = new OrderActor();

	public void onOrder(Order order) {
		logger.info("handle order ordSysId==[{}]", order.getOrdSysId());
		if (OrderKeeper.containsOrder(order.getOrdSysId()))
			OrderKeeper.updateOrder(order);
		else
			OrderKeeper.insertOrder(order);
	}
	
	

	public void onMarketData(BasicMarketData marketData) {
		// TODO
	}

}
