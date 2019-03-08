package io.ffreedom.redstone.core.order.storage;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.ffreedom.common.collect.ECollections;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.redstone.core.account.storage.AccountKeeper;
import io.ffreedom.redstone.core.order.Order;

@NotThreadSafe
public final class OrderKeeper {

	private static final Logger logger = CommonLoggerFactory.getLogger(OrderKeeper.class);

	private static final OrderKeeper INSTANCE = new OrderKeeper();

	// * 存储所有的order
	private final OrderBook allOrders = OrderBook.newInstance(65536);

	// * 按照subAccount分组存储
	private final MutableIntObjectMap<OrderBook> subAccountOrderBookMap = ECollections.newIntObjectHashMap();

	// * 按照account分组存储
	private final MutableIntObjectMap<OrderBook> accountOrderBookMap = ECollections.newIntObjectHashMap();

	// * 按照strategy分组存储
	private final MutableIntObjectMap<OrderBook> strategyOrderBookMap = ECollections.newIntObjectHashMap();

	// * 按照instrument分组存储
	private final MutableIntObjectMap<OrderBook> instrumentOrderBookMap = ECollections.newIntObjectHashMap();

	private OrderKeeper() {
	}

	public static void updateOrder(Order order) {
		switch (order.getStatus()) {
		case Filled:
		case Canceled:
		case NewRejected:
			getAllOrders().terminatedOrder(order);
			int subAccountId = order.getSubAccountId();
			int accountId = AccountKeeper.getAccountId(subAccountId);
			getSubAccountOrderBook(subAccountId).terminatedOrder(order);
			getAccountOrderBook(accountId).terminatedOrder(order);
			getStrategyOrderBook(order.getStrategyId()).terminatedOrder(order);
			getInstrumentOrderBook(order.getInstrument().getInstrumentId()).terminatedOrder(order);
			break;
		default:
			logger.info("Not need processed -> OrdSysId==[{}], OrdStatus==[{}]", order.getOrdSysId(),
					order.getStatus());
			break;
		}
	}

	public static void insertOrder(Order order) {
		getAllOrders().putOrder(order);
		int subAccountId = order.getSubAccountId();
		int accountId = AccountKeeper.getAccountId(subAccountId);
		getSubAccountOrderBook(subAccountId).putOrder(order);
		getAccountOrderBook(accountId).putOrder(order);
		getStrategyOrderBook(order.getStrategyId()).putOrder(order);
		getInstrumentOrderBook(order.getInstrument().getInstrumentId()).putOrder(order);
	}

	public static boolean containsOrder(long ordSysId) {
		return INSTANCE.allOrders.containsOrder(ordSysId);
	}

	public static Order getOrder(long ordSysId) {
		return INSTANCE.allOrders.getOrder(ordSysId);
	}

	public static OrderBook getAllOrders() {
		return INSTANCE.allOrders;
	}

	public static OrderBook getSubAccountOrderBook(int subAccountId) {
		OrderBook subAccountOrderBook = INSTANCE.subAccountOrderBookMap.get(subAccountId);
		if (subAccountOrderBook == null) {
			subAccountOrderBook = OrderBook.newInstance(1024);
			INSTANCE.subAccountOrderBookMap.put(subAccountId, subAccountOrderBook);
		}
		return subAccountOrderBook;
	}

	public static OrderBook getAccountOrderBook(int accountId) {
		OrderBook accountOrderBook = INSTANCE.accountOrderBookMap.get(accountId);
		if (accountOrderBook == null) {
			accountOrderBook = OrderBook.newInstance(2048);
			INSTANCE.accountOrderBookMap.put(accountId, accountOrderBook);
		}
		return accountOrderBook;
	}

	public static OrderBook getStrategyOrderBook(int strategyId) {
		OrderBook strategyOrderBook = INSTANCE.strategyOrderBookMap.get(strategyId);
		if (strategyOrderBook == null) {
			strategyOrderBook = OrderBook.newInstance(4096);
			INSTANCE.strategyOrderBookMap.put(strategyId, strategyOrderBook);
		}
		return strategyOrderBook;
	}

	public static OrderBook getInstrumentOrderBook(int instrumentId) {
		OrderBook instrumentOrderBook = INSTANCE.instrumentOrderBookMap.get(instrumentId);
		if (instrumentOrderBook == null) {
			instrumentOrderBook = OrderBook.newInstance(2048);
			INSTANCE.instrumentOrderBookMap.put(instrumentId, instrumentOrderBook);
		}
		return instrumentOrderBook;
	}

}
