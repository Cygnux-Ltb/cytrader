package io.redstone.core.strategy;

import io.mercury.financial.market.impl.BasicMarketData;
import io.redstone.core.adaptor.AdaptorEvent;
import io.redstone.core.order.structure.OrdReport;

public interface StrategyScheduler {

	void onMarketData(BasicMarketData marketData);

	void onOrderReport(OrdReport orderReport);

	void onAdaptorEvent(int adaptorId, AdaptorEvent event);

}
