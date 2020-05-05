package io.redstone.engine.scheduler;

import io.mercury.financial.market.impl.BasicMarketData;
import io.redstone.core.adaptor.Adaptor.AdaptorStatus;
import io.redstone.core.keeper.OrderKeeper;
import io.redstone.core.order.Order;
import io.redstone.core.order.OrderUpdater;
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
		Order order = OrderKeeper.getOrder(ordReport.getOrdSysId());
		if (order == null) {
			// TODO 必须处理手动下单后收到报单回报的情况
		}
		ChildOrder childOrder = (ChildOrder) order;
		// 更新订单状态
		OrderUpdater.updateOrderWithReport(childOrder, ordReport);
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
