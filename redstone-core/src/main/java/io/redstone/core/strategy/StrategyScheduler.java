package io.redstone.core.strategy;

import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.redstone.core.order.structure.OrdReport;

public interface StrategyScheduler {

	void onMarketData(BasicMarketData marketData);

	void onOrderReport(OrdReport orderReport);

}
