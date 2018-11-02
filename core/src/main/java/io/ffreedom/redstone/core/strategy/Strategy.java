package io.ffreedom.redstone.core.strategy;

import io.ffreedom.financial.Instrument;
import io.ffreedom.market.MarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.trade.enums.Direction;

public interface Strategy extends CircuitBreaker {

	int getStrategyId();

	boolean isTradable();

	void init();

	default void onControlEvent(StrategyControlEvent event) {
		if (event != null) {
			event.onEvent();
		}
	}

	void onMarketData(MarketData marketData);

	void onNewOrder(Order order);

	void onNewOrderReject(Order order);

	void onCancelOrder(Order order);

	void onCancelOrderReject(Order order);

	void onOrderFilled(Order order);

	void onOrderPartiallyFilled(Order order);

	void onError(Throwable throwable);

	void positionsTo(int strategyId, Instrument instrument, Direction direction, double price, double qty);

}
