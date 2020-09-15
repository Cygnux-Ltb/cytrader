package io.mercury.redstone.engine.scheduler;

import org.slf4j.Logger;

import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.financial.market.MarkerDataKeeper;
import io.mercury.financial.market.api.MarketData;
import io.mercury.redstone.core.adaptor.AdaptorEvent;
import io.mercury.redstone.core.order.ActualChildOrder;
import io.mercury.redstone.core.order.OrderKeeper;
import io.mercury.redstone.core.order.structure.OrdReport;
import io.mercury.redstone.core.strategy.Strategy;
import io.mercury.redstone.core.strategy.StrategyScheduler;

public final class SingleStrategyScheduler<M extends MarketData> implements StrategyScheduler<M> {

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(SingleStrategyScheduler.class);

	/**
	 * only strategy
	 */
	private final Strategy<M> strategy;

	public SingleStrategyScheduler(Strategy<M> strategy) {
		this.strategy = strategy;
	}

	@Override
	public void onMarketData(M marketData) {
		MarkerDataKeeper.onMarketDate(marketData);
		strategy.onMarketData(marketData);
	}

	@Override
	public void onOrdReport(OrdReport report) {
		ActualChildOrder order = OrderKeeper.onOrdReport(report);
		// 调用策略实现的订单回调函数
		strategy.onOrder(order);
	}

	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		log.error("On Adaptor Event -> {}", event);
		strategy.onAdaptorEvent(event);
	}

	@Override
	public void addStrategy(Strategy<M> strategy) {
		IllegalStateException ex = new IllegalStateException(
				"Unable to add strategy, This scheduler only supports single strategy.");
		log.error("Add strategy failure", ex);
		throw ex;
	}

}
