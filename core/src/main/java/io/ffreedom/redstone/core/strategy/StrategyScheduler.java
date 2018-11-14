package io.ffreedom.redstone.core.strategy;

import io.ffreedom.market.BasicMarketData;
import io.ffreedom.redstone.core.order.Order;

public interface StrategyScheduler {

	void onMarketData(BasicMarketData marketData);

	void onOrder(Order order);

}
