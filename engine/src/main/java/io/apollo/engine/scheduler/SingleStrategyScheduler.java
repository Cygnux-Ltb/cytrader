package io.apollo.engine.scheduler;

import org.slf4j.Logger;

import io.apollo.engine.strategy.Strategy;
import io.horizon.definition.adaptor.AdaptorEvent;
import io.horizon.definition.event.InboundScheduler;
import io.horizon.definition.market.data.MarkerDataKeeper;
import io.horizon.definition.market.data.MarketData;
import io.horizon.definition.order.OrderKeeper;
import io.horizon.definition.order.actual.ChildOrder;
import io.horizon.definition.order.structure.OrdReport;
import io.mercury.common.log.CommonLoggerFactory;

public final class SingleStrategyScheduler<M extends MarketData> implements InboundScheduler<M> {

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
		ChildOrder order = OrderKeeper.onOrdReport(report);
		// 调用策略实现的订单回调函数
		strategy.onOrder(order);
	}

	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		log.error("On Adaptor Event -> {}", event);
		strategy.onAdaptorEvent(event);
	}

}