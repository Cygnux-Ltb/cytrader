package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongObjectHashMap;
import org.slf4j.Logger;

import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.storage.OrderBook;

public class OrderActor {

	private MutableLongObjectMap<Order> ordSysIdOrderMap = LongObjectHashMap.newMap();

	private MutableIntObjectMap<OrderBook> accountIdOrderBookMap = IntObjectHashMap.newMap();

	private MutableIntObjectMap<OrderBook> strategyIdOrderBookMap = IntObjectHashMap.newMap();

	private final static OrderActor INSTANCE = new OrderActor();

	private static Logger logger = LoggerFactory.getLogger(OrderActor.class);

	private OrderActor() {
	}

	public static void onOrder(Order order) {
		INSTANCE.onOrder0(order);
	}

	private void onOrder0(Order order) {
		if (ordSysIdOrderMap.containsKey(order.getOrdSysId())) {
			updateOrder(order);
		} else {
			saveOrder(order);
		}
	}

	private void updateOrder(Order order) {
		switch (order.getStatus()) {
		case New:
			break;
		case Canceled:
			break;
		case PartiallyFilled:
			break;
		case Filled:
			break;
		case NewRejected:
			break;
		case CancelRejected:
			break;
		default:
			logger.warn("Not processed : OrdSysId -> {}, OrdStatus -> {}", order.getOrdSysId(), order.getStatus());
			break;
		}
	}

	private void saveOrder(Order order) {
		ordSysIdOrderMap.put(order.getOrdSysId(), order);
		int accountId = AccountActor.getAccount(order.getSubAccountId()).getAccountId();
		if (!accountIdOrderBookMap.containsKey(accountId)) {
			accountIdOrderBookMap.put(accountId, OrderBook.newInstance());
		}
		accountIdOrderBookMap.get(accountId).saveOrder(order);
		if (!strategyIdOrderBookMap.containsKey(order.getStrategyId())) {
			strategyIdOrderBookMap.put(order.getStrategyId(), OrderBook.newInstance());
		}
		strategyIdOrderBookMap.get(order.getStrategyId()).saveOrder(order);
	}

	public static Order getOrder(long orderSysId) {
		return INSTANCE.getOrder0(orderSysId);
	}

	private Order getOrder0(long orderSysId) {
		return ordSysIdOrderMap.get(orderSysId);
	}

	public OrderBook getStrategyOrderBook(int strategyId) {
		if (!strategyIdOrderBookMap.containsKey(strategyId)) {
			strategyIdOrderBookMap.put(strategyId, OrderBook.newInstance());
		}
		return strategyIdOrderBookMap.get(strategyId);
	}

	public OrderBook getAccountOrderBook(int accountId) {
		if (!accountIdOrderBookMap.containsKey(accountId)) {
			accountIdOrderBookMap.put(accountId, OrderBook.newInstance());
		}
		return accountIdOrderBookMap.get(accountId);
	}

}
