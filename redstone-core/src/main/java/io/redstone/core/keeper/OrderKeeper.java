package io.redstone.core.order;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.slf4j.Logger;

import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableLists;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.redstone.core.account.AccountKeeper;
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

@NotThreadSafe
public final class OrderKeeper {

	private static final Logger log = CommonLoggerFactory.getLogger(OrderKeeper.class);

	/**
	 * 存储所有的order
	 */
	private final OrderBook allOrders = OrderBook.newInstance(Capacity.L16_SIZE_65536);

	/**
	 * 按照subAccount分组存储
	 */
	private final MutableIntObjectMap<OrderBook> subAccountOrderBookMap = MutableMaps.newIntObjectHashMap();

	/**
	 * 按照account分组存储
	 */
	private final MutableIntObjectMap<OrderBook> accountOrderBookMap = MutableMaps.newIntObjectHashMap();

	/**
	 * 按照strategy分组存储
	 */
	private final MutableIntObjectMap<OrderBook> strategyOrderBookMap = MutableMaps.newIntObjectHashMap();

	/**
	 * 按照instrument分组存储
	 */
	private final MutableIntObjectMap<OrderBook> instrumentOrderBookMap = MutableMaps.newIntObjectHashMap();

	private static final OrderKeeper Singleton = new OrderKeeper();

	private OrderKeeper() {
	}

	public static void updateOrder(Order order) {
		OrderBook allOrders = getAllOrders();
		int subAccountId = order.subAccountId();
		int accountId = AccountKeeper.getAccountBySubAccountId(subAccountId).accountId();
		OrderBook subAccountOrders = getSubAccountOrders(subAccountId);
		OrderBook accountOrders = getAccountOrders(accountId);
		OrderBook strategyOrders = getStrategyOrders(order.strategyId());
		OrderBook instrumentOrders = getInstrumentOrders(order.instrument());
		switch (order.ordStatus()) {
		case PendingNew:
			allOrders.putOrder(order);
			subAccountOrders.putOrder(order);
			accountOrders.putOrder(order);
			strategyOrders.putOrder(order);
			instrumentOrders.putOrder(order);
			break;
		case Filled:
		case Canceled:
		case NewRejected:
		case CancelRejected:
			allOrders.terminatedOrder(order);
			subAccountOrders.terminatedOrder(order);
			accountOrders.terminatedOrder(order);
			strategyOrders.terminatedOrder(order);
			instrumentOrders.terminatedOrder(order);
			break;
		default:
			log.info("Not need processed -> OrdSysId==[{}], OrdStatus==[{}]", order.ordSysId(), order.ordStatus());
			break;
		}
	}

	public static boolean containsOrder(long ordSysId) {
		return Singleton.allOrders.containsOrder(ordSysId);
	}

	public static Order getOrder(long ordSysId) {
		return Singleton.allOrders.getOrder(ordSysId);
	}

	public static OrderBook getAllOrders() {
		return Singleton.allOrders;
	}

	public static OrderBook getSubAccountOrders(int subAccountId) {
		return Singleton.subAccountOrderBookMap.getIfAbsentPut(subAccountId,
				OrderBook.newInstance(Capacity.L07_SIZE_128));
	}

	public static OrderBook getAccountOrders(int accountId) {
		return Singleton.accountOrderBookMap.getIfAbsentPut(accountId, OrderBook.newInstance(Capacity.L08_SIZE_256));
	}

	public static OrderBook getStrategyOrders(int strategyId) {
		return Singleton.strategyOrderBookMap.getIfAbsentPut(strategyId, OrderBook.newInstance(Capacity.L10_SIZE_1024));
	}

	public static OrderBook getInstrumentOrders(Instrument instrument) {
		return Singleton.instrumentOrderBookMap.getIfAbsentPut(instrument.id(),
				OrderBook.newInstance(Capacity.L11_SIZE_2048));
	}

	public static MutableList<ParentOrder> onStrategyOrder(@Nonnull StrategyOrder... strategyOrders) {
		MutableList<ParentOrder> parentOrders = MutableLists.newFastList(strategyOrders.length);
		for (StrategyOrder strategyOrder : strategyOrders) {
			OrderBook instrumentOrderBook = getInstrumentOrders(strategyOrder.instrument());
			int offerQty = strategyOrder.ordQty().offerQty();
			switch (strategyOrder.trdDirection()) {
			case Long:
				MutableLongObjectMap<Order> activeShortOrders = instrumentOrderBook.activeShortOrders();
				if (activeShortOrders.notEmpty()) {
					// TODO 当有活动的反向订单时选择撤单
				}
				// TODO 检查当前头寸, 如果有反向头寸, 选择平仓
				// TODO 计算平仓后还需要开仓的数量
				int needOpenLong = offerQty - 0;
				ParentOrder openLongOrder = strategyOrder.toActualOrder(TrdDirection.Long, needOpenLong, OrdType.Limit);
				parentOrders.add(openLongOrder);
				break;
			case Short:
				MutableLongObjectMap<Order> activeLongOrders = instrumentOrderBook.activeLongOrders();
				if (activeLongOrders.notEmpty()) {
					// TODO 当有活动的反向订单时选择撤单
				}
				// TODO 检查当前头寸, 如果有反向头寸, 选择平仓
				// TODO 计算平仓后还需要开仓的数量
				int needOpenShort = offerQty - 0;
				ParentOrder openShortOrder = strategyOrder.toActualOrder(TrdDirection.Short, needOpenShort,
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
