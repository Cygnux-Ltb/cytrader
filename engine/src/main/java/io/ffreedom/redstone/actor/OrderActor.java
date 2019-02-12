package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.ffreedom.common.collect.ECollections;
import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.order.storage.OrderBook;

public class OrderActor {

	private static Logger logger = LoggerFactory.getLogger(OrderActor.class);

	private static final OrderActor INSTANCE = new OrderActor();

	// * 存储所有的Order
	private OrderBook allOrders = OrderBook.newInstance(65536);

	// * 按照subAccount分组存储
	private MutableIntObjectMap<OrderBook> subAccountOrderBookMap = ECollections.newIntObjectHashMap();

	// * 按照account分组存储
	private MutableIntObjectMap<OrderBook> accountOrderBookMap = ECollections.newIntObjectHashMap();

	// * 按照strategy分组存储
	private MutableIntObjectMap<OrderBook> strategyOrderBookMap = ECollections.newIntObjectHashMap();

	// * 按照instrument分组存储
	private MutableIntObjectMap<OrderBook> instrumentOrderBookMap = ECollections.newIntObjectHashMap();

	private OrderActor() {
	}

	public static void onOrder(Order order) {
		INSTANCE.onOrder0(order);
	}

	private void onOrder0(Order order) {
		if (allOrders.containsOrder(order.getOrdSysId()))
			updateOrder(order);
		else
			insertOrder(order);
	}

	private void updateOrder(Order order) {
		switch (order.getStatus()) {
		case Canceled:
		case Filled:
		case NewRejected:
		case CancelRejected:
			
			break;
		default:
			logger.warn("Not processed : OrdSysId -> {}, OrdStatus -> {}", order.getOrdSysId(), order.getStatus());
			break;
		}
	}

	private void insertOrder(Order order) {
		allOrders.putOrder(order);
		int subAccountId = order.getSubAccountId();
		int accountId = AccountActor.getAccount(subAccountId).getAccountId();
		getSubAccountOrderBook(subAccountId).putOrder(order);
		getAccountOrderBook(accountId).putOrder(order);
		getStrategyOrderBook(order.getStrategyId()).putOrder(order);
		getInstrumentOrderBook(order.getInstrument().getInstrumentId()).putOrder(order);
	}

	public static Order getOrder(long orderSysId) {
		return INSTANCE.getOrder0(orderSysId);
	}

	private Order getOrder0(long orderSysId) {
		return allOrders.getOrder(orderSysId);
	}

	public OrderBook getAllOrders() {
		return allOrders;
	}

	public OrderBook getSubAccountOrderBook(int subAccountId) {
		OrderBook subAccountOrderBook = subAccountOrderBookMap.get(subAccountId);
		if (subAccountOrderBook == null) {
			subAccountOrderBook = OrderBook.newInstance(1024);
			subAccountOrderBookMap.put(subAccountId, subAccountOrderBook);
		}
		return subAccountOrderBook;
	}

	public OrderBook getAccountOrderBook(int accountId) {
		OrderBook accountOrderBook = accountOrderBookMap.get(accountId);
		if (accountOrderBook == null) {
			accountOrderBook = OrderBook.newInstance(2048);
			accountOrderBookMap.put(accountId, accountOrderBook);
		}
		return accountOrderBook;
	}

	public OrderBook getStrategyOrderBook(int strategyId) {
		OrderBook strategyOrderBook = strategyOrderBookMap.get(strategyId);
		if (strategyOrderBook == null) {
			strategyOrderBook = OrderBook.newInstance(4096);
			strategyOrderBookMap.put(strategyId, strategyOrderBook);
		}
		return strategyOrderBook;
	}

	public OrderBook getInstrumentOrderBook(int instrumentId) {
		OrderBook instrumentOrderBook = instrumentOrderBookMap.get(instrumentId);
		if (instrumentOrderBook == null) {
			instrumentOrderBook = OrderBook.newInstance(2048);
			instrumentOrderBookMap.put(instrumentId, instrumentOrderBook);
		}
		return instrumentOrderBook;
	}

}
