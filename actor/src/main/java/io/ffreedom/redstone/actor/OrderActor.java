package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongObjectHashMap;

import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.storage.OrderBook;

public class OrderActor {

	private MutableLongObjectMap<Order> ordSysIdOrderMap = LongObjectHashMap.newMap();

	private MutableIntObjectMap<OrderBook> accountIdOrderBookMap = IntObjectHashMap.newMap();

	private MutableIntObjectMap<OrderBook> strategyIdOrderBookMap = IntObjectHashMap.newMap();

	private final static OrderActor INSTANCE = new OrderActor();

	private OrderActor() {
	}

	public static void onOrder(Order order) {
		INSTANCE.onOrder0(order);
	}

	private void onOrder0(Order order) {
		if (ordSysIdOrderMap.containsKey(order.getOrdSysId())) {
			updateOrder(order);
		} else {
			putOrder(order);
		}
	}

	private void updateOrder(Order order) {

	}

	private void putOrder(Order order) {
		ordSysIdOrderMap.put(order.getOrdSysId(), order);
		int accountId = AccountActor.getAccount(order.getSubAccountId()).getAccountId();
		if (!accountIdOrderBookMap.containsKey(accountId)) {
			accountIdOrderBookMap.put(accountId, OrderBook.newInstance());
		}
		accountIdOrderBookMap.get(accountId).putOrder(order);
		if (!strategyIdOrderBookMap.containsKey(order.getStrategyId())) {
			strategyIdOrderBookMap.put(order.getStrategyId(), OrderBook.newInstance());
		}
		strategyIdOrderBookMap.get(order.getStrategyId()).putOrder(order);
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
