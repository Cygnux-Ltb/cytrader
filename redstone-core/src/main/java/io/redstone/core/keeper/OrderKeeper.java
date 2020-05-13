package io.redstone.core.keeper;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.redstone.core.order.Order;
import io.redstone.core.order.OrderBook;
import io.redstone.core.order.OrderUpdater;
import io.redstone.core.order.specific.ChildOrder;
import io.redstone.core.order.structure.OrdReport;

/**
 * 统一管理订单<br>
 * OrderKeeper只对订单进行存储<br>
 * 不处理订单<br>
 * 
 * @author yellow013
 */

@NotThreadSafe
public final class OrderKeeper {

	private static final Logger log = CommonLoggerFactory.getLogger(OrderKeeper.class);

	/**
	 * 存储所有的order
	 */
	private static final OrderBook AllOrders = new OrderBook(Capacity.L10_SIZE_1024);

	/**
	 * 按照subAccount分组存储
	 */
	private static final MutableIntObjectMap<OrderBook> SubAccountOrderBooks = MutableMaps.newIntObjectHashMap();

	/**
	 * 按照account分组存储
	 */
	private static final MutableIntObjectMap<OrderBook> AccountOrderBooks = MutableMaps.newIntObjectHashMap();

	/**
	 * 按照strategy分组存储
	 */
	private static final MutableIntObjectMap<OrderBook> StrategyOrderBooks = MutableMaps.newIntObjectHashMap();

	/**
	 * 按照instrument分组存储
	 */
	private static final MutableIntObjectMap<OrderBook> InstrumentOrderBooks = MutableMaps.newIntObjectHashMap();

	private OrderKeeper() {
	}

	public static void onOrder(Order order) {
		int subAccountId = order.subAccountId();
		int accountId = AccountKeeper.getAccountBySubAccountId(subAccountId).accountId();
		switch (order.ordStatus()) {
		case PendingNew:
			AllOrders.putOrder(order);
			getSubAccountOrders(subAccountId).putOrder(order);
			getAccountOrders(accountId).putOrder(order);
			getStrategyOrders(order.strategyId()).putOrder(order);
			getInstrumentOrders(order.instrument()).putOrder(order);
			break;
		case Filled:
		case Canceled:
		case NewRejected:
		case CancelRejected:
			AllOrders.finishOrder(order);
			getSubAccountOrders(subAccountId).finishOrder(order);
			getAccountOrders(accountId).finishOrder(order);
			getStrategyOrders(order.strategyId()).finishOrder(order);
			getInstrumentOrders(order.instrument()).finishOrder(order);
			break;
		default:
			log.info("OrderKeeper :: Not need processed, ordSysId==[{}], ordStatus==[{}]", order.ordSysId(),
					order.ordStatus());
			break;
		}
	}

	/**
	 * 处理订单回报
	 * 
	 * @param report
	 * @return
	 */
	public static ChildOrder onOrdReport(OrdReport report) {
		log.info(
				"Handle OrderReport, brokerUniqueId==[{}], ordSysId==[{}], executePrice==[{}], filledQty==[{}], instrument -> {}",
				report.getBrokerUniqueId(), report.getOrdSysId(), report.getExecutePrice(), report.getFilledQty(),
				report.getInstrument());
		// 根据订单回报查找所属订单
		Order order = getOrder(report.getOrdSysId());
		if (order == null) {
			// TODO 处理订单由外部系统发出而收到报单回报
			log.warn("OrderKeeper :: Received other source order, ordSysId==[{}]", report.getOrdSysId());
		} else {
			log.info("OrderKeeper :: Search order OK, strategyId==[{}], subAccountId==[{}]", order.strategyId(),
					order.subAccountId());
		}
		ChildOrder childOrder = (ChildOrder) order;
		// 更新订单状态
		OrderUpdater.updateOrderWithReport(childOrder, report);
		onOrder(childOrder);
		return childOrder;
	}

	public static boolean containsOrder(long ordSysId) {
		return AllOrders.containsOrder(ordSysId);
	}

	public static Order getOrder(long ordSysId) {
		return AllOrders.getOrder(ordSysId);
	}

	public static OrderBook getSubAccountOrders(int subAccountId) {
		return SubAccountOrderBooks.getIfAbsentPut(subAccountId, OrderBook::new);
	}

	public static OrderBook getAccountOrders(int accountId) {
		return AccountOrderBooks.getIfAbsentPut(accountId, OrderBook::new);
	}

	public static OrderBook getStrategyOrders(int strategyId) {
		return StrategyOrderBooks.getIfAbsentPut(strategyId, OrderBook::new);
	}

	public static OrderBook getInstrumentOrders(Instrument instrument) {
		return InstrumentOrderBooks.getIfAbsentPut(instrument.id(), OrderBook::new);
	}

	public static void onMarketData(BasicMarketData marketData) {
		// TODO
	}

}
