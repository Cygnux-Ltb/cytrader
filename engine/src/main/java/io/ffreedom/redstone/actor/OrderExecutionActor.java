package io.ffreedom.redstone.actor;

import org.slf4j.Logger;

import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.core.order.api.Order;

/**
 * 统一管理订单<br>
 * 1对订单止损进行管理<br>
 * 2...<br>
 * 
 * @author yellow013
 */

public class VirtualOrderActor {

	private Logger logger = CommonLoggerFactory.getLogger(VirtualOrderActor.class);

	private VirtualOrderActor() {
	}

	public static final VirtualOrderActor Singleton = new VirtualOrderActor();

	public void onOrder(Order order) {}
	
	

	public void onMarketData(BasicMarketData marketData) {
		// TODO
	}

}
