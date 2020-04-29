package io.redstone.engine.scheduler;

import io.mercury.financial.market.impl.BasicMarketData;
import io.redstone.core.adaptor.Adaptor.AdaptorStatus;
import io.redstone.core.order.structure.OrdReport;
import io.redstone.core.strategy.Strategy;
import io.redstone.core.strategy.StrategyScheduler;

public final class ChronicleStrategyScheduler implements StrategyScheduler {

	@Override
	public void onMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOrderReport(OrdReport orderReport) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAdaptorStatus(int adaptorId, AdaptorStatus status) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addStrategy(Strategy strategy) {
		// TODO Auto-generated method stub
		
	}

}
