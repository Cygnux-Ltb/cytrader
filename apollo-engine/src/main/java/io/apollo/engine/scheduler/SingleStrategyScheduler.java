package io.apollo.engine.scheduler;

import org.slf4j.Logger;

import io.gemini.definition.adaptor.AdaptorEvent;
import io.gemini.definition.event.InboundScheduler;
import io.gemini.definition.market.data.MarkerDataKeeper;
import io.gemini.definition.market.data.api.MarketData;
import io.gemini.definition.order.ActualChildOrder;
import io.gemini.definition.order.OrderKeeper;
import io.gemini.definition.order.structure.OrdReport;
import io.gemini.definition.strategy.Strategy;
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
		ActualChildOrder order = OrderKeeper.onOrdReport(report);
		// 调用策略实现的订单回调函数
		strategy.onOrder(order);
	}

	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		log.error("On Adaptor Event -> {}", event);
		strategy.onAdaptorEvent(event);
	}

}
