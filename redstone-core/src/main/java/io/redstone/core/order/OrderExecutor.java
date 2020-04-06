package io.redstone.core.order;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableLists;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.financial.market.impl.BasicMarketData;
import io.redstone.core.order.enums.OrdType;
import io.redstone.core.order.enums.TrdDirection;
import io.redstone.core.order.specific.ParentOrder;
import io.redstone.core.order.specific.StrategyOrder;

/**
 * 统一管理订单<br>
 * 1对订单止损进行管理<br>
 * 2...<br>
 * 
 * @author yellow013
 */

public final class OrderExecutor {

	private Logger log = CommonLoggerFactory.getLogger(OrderExecutor.class);

	private OrderExecutor() {
	}

	public static MutableList<ParentOrder> onStrategyOrder(@Nonnull StrategyOrder... strategyOrders) {
		MutableList<ParentOrder> parentOrders = MutableLists.newFastList();
		for (StrategyOrder strategyOrder : strategyOrders) {
			OrderBook instrumentOrderBook = OrderKeeper.getInstrumentOrderBook(strategyOrder.instrument().id());
			long offerQty = strategyOrder.ordQty().offerQty();
			switch (strategyOrder.trdDirection()) {
			case Long:
				MutableLongObjectMap<Order> activeShortOrders = instrumentOrderBook.activeShortOrders();
				if (activeShortOrders.notEmpty()) {
					// TODO 当有活动的反向订单时选择撤单
				}
				// TODO 检查当前头寸, 如果有反向头寸, 选择平仓
				// TODO 计算平仓后还需要开仓的数量
				long needOpenLong = offerQty - 0;
				ParentOrder openLongOrder = strategyOrder.toOpenActualOrder(TrdDirection.Long, needOpenLong,
						OrdType.Limit);
				parentOrders.add(openLongOrder);
				break;
			case Short:
				MutableLongObjectMap<Order> activeLongOrders = instrumentOrderBook.activeLongOrders();
				if (activeLongOrders.notEmpty()) {
					// TODO 当有活动的反向订单时选择撤单
				}
				// TODO 检查当前头寸, 如果有反向头寸, 选择平仓
				// TODO 计算平仓后还需要开仓的数量
				long needOpenShort = offerQty - 0;
				ParentOrder openShortOrder = strategyOrder.toOpenActualOrder(TrdDirection.Short, needOpenShort,
						OrdType.Limit);
				parentOrders.add(openShortOrder);
				break;
			default:
				break;
			}
		}
		return parentOrders;
	}

	public static void onMarketData(BasicMarketData marketData) {
		// TODO
	}

}
