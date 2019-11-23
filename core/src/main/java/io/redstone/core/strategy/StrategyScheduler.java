package io.ffreedom.redstone.core.strategy;

import io.ffreedom.polaris.market.impl.BasicMarketData;
import io.ffreedom.redstone.core.order.impl.OrderReport;

public interface StrategyScheduler {

	void onMarketData(BasicMarketData marketData);

	void onOrderReport(OrderReport orderReport);

}
