package io.mercury.redstone.core.strategy;

import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.redstone.core.adaptor.Adaptor.AdaptorStatus;
import io.mercury.redstone.core.order.structure.OrdReport;

public interface StrategyScheduler {

	void onMarketData(BasicMarketData marketData);

	void onOrderReport(OrdReport report);

	void onAdaptorStatus(int adaptorId, AdaptorStatus status);

	void addStrategy(Strategy strategy);

}
