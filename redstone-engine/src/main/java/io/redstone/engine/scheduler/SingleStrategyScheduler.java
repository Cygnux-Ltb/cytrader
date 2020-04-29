package io.redstone.engine.scheduler;

import io.mercury.financial.market.impl.BasicMarketData;
import io.redstone.core.adaptor.Adaptor.AdaptorStatus;
import io.redstone.core.keeper.OrderKeeper;
import io.redstone.core.order.OrderSupporter;
import io.redstone.core.order.specific.ChildOrder;
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
	public void onOrderReport(OrdReport ordReport) {
		// 根据订单回报查找所属订单
		ChildOrder order = (ChildOrder) OrderKeeper.getOrder(ordReport.getOrdSysId());
		// 更新订单状态
		OrderSupporter.updateOrderWithReport(order, ordReport);
		// 调用策略回调函数
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
