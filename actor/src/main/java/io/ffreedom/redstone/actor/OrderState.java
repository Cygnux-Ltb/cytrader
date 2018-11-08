package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.eclipse.collections.impl.map.mutable.primitive.LongObjectHashMap;

import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.storage.OrderBook;

public class OrderState {

	private MutableLongObjectMap<Order> orderSysIdOrderMap = LongObjectHashMap.newMap();

	private MutableIntObjectMap<OrderBook> accountIdOrderBookMap = IntObjectHashMap.newMap();

	private MutableIntObjectMap<OrderBook> strategyIdOrderBookMap = IntObjectHashMap.newMap();

	public final static OrderState INSTANCE = new OrderState();

	private OrderState() {
	}

	public void onOrder(Order order) {
		if (orderSysIdOrderMap.containsKey(order.getOrdSysId())) {
			updateOrder(order);
		} else {
			putOrder(order);
		}
	}

	private void updateOrder(Order order) {

	}

	private void putOrder(Order order) {
		orderSysIdOrderMap.put(order.getOrdSysId(), order);
		int accountId = AccountState.getAccount(order.getSubAccountId()).getAccountId();
		if (!accountIdOrderBookMap.containsKey(accountId)) {
			accountIdOrderBookMap.put(accountId, OrderBook.newInstance());
		}
		accountIdOrderBookMap.get(accountId).putOrder(order);
		if (!strategyIdOrderBookMap.containsKey(order.getStrategyId())) {
			strategyIdOrderBookMap.put(order.getStrategyId(), OrderBook.newInstance());
		}
		strategyIdOrderBookMap.get(order.getStrategyId()).putOrder(order);
	}

	public Order getOrder(long orderSysId) {
		return orderSysIdOrderMap.get(orderSysId);
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
