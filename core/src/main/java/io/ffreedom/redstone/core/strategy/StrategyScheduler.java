package io.ffreedom.redstone.core.strategy;

import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.core.order.api.Order;

public interface StrategyScheduler {

	void onMarketData(BasicMarketData marketData);

	void onInboundOrder(Order order);

}
