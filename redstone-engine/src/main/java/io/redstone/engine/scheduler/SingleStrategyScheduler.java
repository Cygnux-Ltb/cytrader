package io.redstone.engine.scheduler;

import io.mercury.financial.market.impl.BasicMarketData;
import io.redstone.core.adaptor.AdaptorStatus;
import io.redstone.core.order.structure.OrdReport;
import io.redstone.core.strategy.Strategy;
import io.redstone.core.strategy.StrategyScheduler;

public class SingleStrategyScheduler implements StrategyScheduler {

	private Strategy strategy;

	@Override
	public void onMarketData(BasicMarketData marketData) {
		strategy.onMarketData(marketData);
	}

	@Override
	public void onOrderReport(OrdReport orderReport) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdaptorStatus(int adaptorId, AdaptorStatus status) {
		// TODO Auto-generated method stub
		strategy.onAdaptorStatus(adaptorId, status);
	}

	@Override
	public void addStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

}
