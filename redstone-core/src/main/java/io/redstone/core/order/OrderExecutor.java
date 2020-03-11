package io.redstone.core.order;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.slf4j.Logger;

import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.redstone.core.order.api.Order;
import io.redstone.core.order.enums.OrdSide;
import io.redstone.core.order.impl.ParentOrder;
import io.redstone.core.order.impl.StrategyOrder;
import io.redstone.core.order.storage.OrderBook;
import io.redstone.core.order.storage.OrderKeeper;

/**
 * 统一管理订单<br>
 * 1对订单止损进行管理<br>
 * 2...<br>
 * 
 * @author yellow013
 */

public final class OrderExecutor {

	private Logger logger = CommonLoggerFactory.getLogger(OrderExecutor.class);

	private OrderExecutor() {
	}

	public static void onOrder(Order order) {

	}

	public static ParentOrder onStrategyOrder(@Nonnull StrategyOrder... strategyOrders) {
		StrategyOrder strategyOrder = strategyOrders[0];
		OrderBook instrumentOrderBook = OrderKeeper.getInstrumentOrderBook(strategyOrder.instrument().id());
		OrdSide side = strategyOrder.ordSide();
		switch (side.direction()) {
		case Long:
			MutableLongObjectMap<Order> activeShortOrders = instrumentOrderBook.activeShortOrders();
			if (activeShortOrders.notEmpty()) {
				// 撤单
			} else {

			}
			break;
		case Short:
			MutableLongObjectMap<Order> activeLongOrders = instrumentOrderBook.activeLongOrders();
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

	public static void onMarketData(BasicMarketData marketData) {
		// TODO
	}

}
