package io.mercury.redstone.engine.scheduler;

import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.redstone.core.adaptor.AdaptorEvent;
import io.mercury.redstone.core.keeper.LastMarkerDataKeeper;
import io.mercury.redstone.core.keeper.OrderKeeper;
import io.mercury.redstone.core.order.Order;
import io.mercury.redstone.core.order.structure.OrdReport;
import io.mercury.redstone.core.strategy.Strategy;
import io.mercury.redstone.core.strategy.StrategyScheduler;

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
	public void onAdaptorEvent(AdaptorEvent event) {
		strategy.onAdaptorEvent(event);
	}

	@Override
	public void addStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

}
