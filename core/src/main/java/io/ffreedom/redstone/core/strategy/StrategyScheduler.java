package io.ffreedom.redstone.core.strategy;

import io.ffreedom.market.MarketData;
import io.ffreedom.redstone.core.order.Order;

public interface StrategyScheduler {

	void onMarketData(MarketData marketData);

	void onOrder(Order order);

}
