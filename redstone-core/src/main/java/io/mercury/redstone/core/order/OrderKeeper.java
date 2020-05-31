package io.mercury.redstone.core.order;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.io.Dumper;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.redstone.core.account.AccountKeeper;
import io.mercury.redstone.core.order.enums.OrdType;
import io.mercury.redstone.core.order.enums.TrdAction;
import io.mercury.redstone.core.order.enums.TrdDirection;
import io.mercury.redstone.core.order.structure.OrdReport;

/**
 * 统一管理订单<br>
 * OrderKeeper只对订单进行存储<br>
 * 不处理订单<br>
 * 
 * @author yellow013
 */

@NotThreadSafe
public final class OrderKeeper implements Dumper<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8581377004396461013L;

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(OrderKeeper.class);

	/**
	 * 存储所有的order
	 */
	private static final OrderBook AllOrders = new OrderBook(Capacity.L10_SIZE_1024);

	/**
	 * 按照subAccountId分组存储
	 */
	private static final MutableIntObjectMap<OrderBook> SubAccountOrderBooks = MutableMaps.newIntObjectHashMap();

	/**
	 * 按照accountId分组存储
	 */
	private static final MutableIntObjectMap<OrderBook> AccountOrderBooks = MutableMaps.newIntObjectHashMap();

	/**
	 * 按照strategyId分组存储
	 */
	private static final MutableIntObjectMap<OrderBook> StrategyOrderBooks = MutableMaps.newIntObjectHashMap();

	/**
	 * 按照instrumentId分组存储
	 */
	private static final MutableIntObjectMap<OrderBook> InstrumentOrderBooks = MutableMaps.newIntObjectHashMap();

	private OrderKeeper() {
	}

	static void onOrder(Order order) {
		int subAccountId = order.subAccountId();
		int accountId = AccountKeeper.getAccountBySubAccountId(subAccountId).accountId();
		switch (order.ordStatus()) {
		case PendingNew:
			AllOrders.putOrder(order);
			getSubAccountOrderBook(subAccountId).putOrder(order);
			getAccountOrderBook(accountId).putOrder(order);
			getStrategyOrderBook(order.strategyId()).putOrder(order);
			getInstrumentOrderBook(order.instrument()).putOrder(order);
			break;
		case Filled:
		case Canceled:
		case NewRejected:
		case CancelRejected:
			AllOrders.finishOrder(order);
			getSubAccountOrderBook(subAccountId).finishOrder(order);
			getAccountOrderBook(accountId).finishOrder(order);
			getStrategyOrderBook(order.strategyId()).finishOrder(order);
			getInstrumentOrderBook(order.instrument()).finishOrder(order);
			break;
		default:
			log.info("Not need processed, ordSysId==[{}], ordStatus==[{}]", order.ordSysId(), order.ordStatus());
			break;
		}
	}

	/**
	 * 处理订单回报
	 * 
	 * @param report
	 * @return
	 */
	public static ActChildOrder onOrdReport(OrdReport report) {
		log.info("Handle OrdReport, report -> {}", report);
		// 根据订单回报查找所属订单
		Order order = getOrder(report.getOrdSysId());
		if (order != null) {
			// TODO 处理订单由外部系统发出而收到报单回报
			log.info("Search order OK, strategyId==[{}], subAccountId==[{}]", order.strategyId(), order.subAccountId());
		} else {
			log.warn("Received other source order, ordSysId==[{}]", report.getOrdSysId());
		}
		ActChildOrder childOrder = (ActChildOrder) order;
		// 更新订单状态
		OrderUpdater.updateWithReport(childOrder, report);
		onOrder(childOrder);
		return childOrder;
	}

	public static boolean containsOrder(long ordSysId) {
		return AllOrders.containsOrder(ordSysId);
	}

	public static Order getOrder(long ordSysId) {
		return AllOrders.getOrder(ordSysId);
	}

	public static OrderBook getSubAccountOrderBook(int subAccountId) {
		return SubAccountOrderBooks.getIfAbsentPut(subAccountId, OrderBook::new);
	}

	public static OrderBook getAccountOrderBook(int accountId) {
		return AccountOrderBooks.getIfAbsentPut(accountId, OrderBook::new);
	}

	public static OrderBook getStrategyOrderBook(int strategyId) {
		return StrategyOrderBooks.getIfAbsentPut(strategyId, OrderBook::new);
	}

	public static OrderBook getInstrumentOrderBook(Instrument instrument) {
		return InstrumentOrderBooks.getIfAbsentPut(instrument.id(), OrderBook::new);
	}

	public static void onMarketData(BasicMarketData marketData) {
		// TODO 处理行情
	}

	/**
	 * 创建[ParentOrder], 并存入Keeper
	 * 
	 * @param strategyId
	 * @param accountId
	 * @param subAccountId
	 * @param instrument
	 * @param offerQty
	 * @param offerPrice
	 * @param ordType
	 * @param direction
	 * @param action
	 * @return
	 */
	public static ActParentOrder createParentOrder(int strategyId, int accountId, int subAccountId,
			Instrument instrument, int offerQty, long offerPrice, OrdType ordType, TrdDirection direction,
			TrdAction action) {
		ActParentOrder parentOrder = new ActParentOrder(strategyId, accountId, subAccountId, instrument, offerQty,
				offerPrice, ordType, direction, action);
		onOrder(parentOrder);
		return parentOrder;
	}

	/**
	 * 将[ParentOrder]转换为[ChildOrder], 并存入Keeper
	 * 
	 * @param parentOrder
	 * @return
	 */
	public static ActChildOrder toChildOrder(ActParentOrder parentOrder) {
		ActChildOrder childOrder = parentOrder.toChildOrder();
		onOrder(childOrder);
		return childOrder;
	}

	public static MutableList<ActChildOrder> splitChildOrder(ActParentOrder parentOrder, int count) {
		MutableList<ActChildOrder> childOrders = parentOrder.splitChildOrder(count);
		childOrders.each(OrderKeeper::onOrder);
		return childOrders;
	}

	@Override
	public String dump() {
		return "";
	}

}
