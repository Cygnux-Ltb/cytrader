package io.redstone.core.strategy;

import io.polaris.financial.market.impl.BasicMarketData;
import io.redstone.core.order.impl.OrderReport;

public interface StrategyScheduler {

	void onMarketData(BasicMarketData marketData);

	void onOrderReport(OrderReport orderReport);

}
