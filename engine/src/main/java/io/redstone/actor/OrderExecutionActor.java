package io.redstone.actor;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.slf4j.Logger;

import io.ffreedom.common.log.CommonLoggerFactory;
import io.polaris.market.impl.BasicMarketData;
import io.redstone.core.order.api.Order;
import io.redstone.core.order.enums.OrdSide;
import io.redstone.core.order.impl.ParentOrder;
import io.redstone.core.order.impl.VirtualOrder;
import io.redstone.storage.OrderBook;
import io.redstone.storage.OrderKeeper;

/**
 * 统一管理订单<br>
 * 1对订单止损进行管理<br>
 * 2...<br>
 * 
 * @author yellow013
 */

public class OrderExecutionActor {

	private Logger logger = CommonLoggerFactory.getLogger(OrderExecutionActor.class);

	private OrderExecutionActor() {
	}

	public static final OrderExecutionActor Singleton = new OrderExecutionActor();

	public void onOrder(Order order) {

	}

	public ParentOrder virtualOrderToActual(VirtualOrder virtualOrder) {
		OrderBook instrumentOrderBook = OrderKeeper
				.getInstrumentOrderBook(virtualOrder.getInstrument().getInstrumentId());
		OrdSide side = virtualOrder.getSide();
		switch (side.direction()) {
		case Long:
			MutableLongObjectMap<Order> activeShortOrders = instrumentOrderBook.getActiveShortOrders();
			if (activeShortOrders.notEmpty()) {
				// 撤单
			} else {

			}
			break;
		case Short:
			MutableLongObjectMap<Order> activeLongOrders = instrumentOrderBook.getActiveLongOrders();
			if (activeLongOrders.notEmpty()) {
				// 撤单
			} else {

			}
			break;
		default:
			break;
		}
		return null;
	}

	public void onMarketData(BasicMarketData marketData) {
		// TODO
	}

}
