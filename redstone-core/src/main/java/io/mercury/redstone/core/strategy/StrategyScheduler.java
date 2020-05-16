package io.mercury.redstone.core.strategy;

import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.redstone.core.adaptor.AdaptorEvent;
import io.mercury.redstone.core.order.structure.OrdReport;

public interface StrategyScheduler {

	void onMarketData(BasicMarketData marketData);

	void onOrdReport(OrdReport report);

	void onAdaptorEvent(AdaptorEvent event);

	void addStrategy(Strategy strategy);

}
