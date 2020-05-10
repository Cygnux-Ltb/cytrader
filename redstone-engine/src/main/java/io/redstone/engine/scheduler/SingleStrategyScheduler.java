package io.redstone.engine.scheduler;

import io.mercury.financial.market.impl.BasicMarketData;
import io.redstone.core.adaptor.Adaptor.AdaptorStatus;
import io.redstone.core.keeper.LastMarkerDataKeeper;
import io.redstone.core.keeper.OrderKeeper;
import io.redstone.core.order.Order;
import io.redstone.core.order.structure.OrdReport;
import io.redstone.core.strategy.Strategy;
import io.redstone.core.strategy.StrategyScheduler;

public class SingleStrategyScheduler implements StrategyScheduler {

	private Strategy strategy;

	@Override
	public void onMarketData(BasicMarketData marketData) {
		LastMarkerDataKeeper.onMarketDate(marketData);
		strategy.onMarketData(marketData);
	}

	@Override
	public void onOrderReport(OrdReport report) {
		Order order = OrderKeeper.onOrdReport(report);
		// 调用策略实现的订单回调函数
		strategy.onOrder(order);
	}

	@Override
	public void onAdaptorStatus(int adaptorId, AdaptorStatus status) {
		strategy.onAdaptorStatus(adaptorId, status);
	}

	@Override
	public void addStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

}
