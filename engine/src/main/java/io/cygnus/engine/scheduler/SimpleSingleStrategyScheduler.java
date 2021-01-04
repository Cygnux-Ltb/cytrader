package io.cygnus.engine.scheduler;

import org.slf4j.Logger;

import io.cygnus.engine.strategy.Strategy;
import io.horizon.structure.adaptor.AdaptorEvent;
import io.horizon.structure.event.InboundScheduler;
import io.horizon.structure.market.data.MarkerDataKeeper;
import io.horizon.structure.market.data.MarketData;
import io.horizon.structure.order.OrdReport;
import io.horizon.structure.order.OrderBookKeeper;
import io.horizon.structure.order.actual.ChildOrder;
import io.mercury.common.log.CommonLoggerFactory;

public final class SimpleSingleStrategyScheduler<M extends MarketData> implements InboundScheduler<M> {

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(SimpleSingleStrategyScheduler.class);

	/**
	 * only one strategy
	 */
	private final Strategy<M> strategy;

	public SimpleSingleStrategyScheduler(Strategy<M> strategy) {
		this.strategy = strategy;
	}

	@Override
	public void onMarketData(M marketData) {
		MarkerDataKeeper.onMarketDate(marketData);
		strategy.onMarketData(marketData);
	}

	@Override
	public void onOrdReport(OrdReport report) {
		ChildOrder order = OrderBookKeeper.onOrdReport(report);
		// 调用策略实现的订单回调函数
		strategy.onOrder(order);
	}

	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		log.error("On Adaptor Event -> {}", event);
		strategy.onAdaptorEvent(event);
	}

}