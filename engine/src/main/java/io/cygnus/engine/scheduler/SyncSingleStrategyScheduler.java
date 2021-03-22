package io.cygnus.engine.scheduler;

import java.io.IOException;

import org.slf4j.Logger;

import io.cygnus.engine.strategy.api.Strategy;
import io.horizon.structure.adaptor.AdaptorEvent;
import io.horizon.structure.event.InboundScheduler;
import io.horizon.structure.market.data.MarkerDataKeeper;
import io.horizon.structure.market.data.MarketData;
import io.horizon.structure.order.ChildOrder;
import io.horizon.structure.order.OrderManager;
import io.horizon.structure.order.OrderReport;
import io.mercury.common.log.CommonLoggerFactory;

public class SyncSingleStrategyScheduler<M extends MarketData> implements InboundScheduler<M> {

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(SyncSingleStrategyScheduler.class);

	/**
	 * only one strategy
	 */
	private final Strategy<M> strategy;

	public SyncSingleStrategyScheduler(Strategy<M> strategy) {
		this.strategy = strategy;
	}

	@Override
	public void onMarketData(M marketData) {
		MarkerDataKeeper.onMarketDate(marketData);
		strategy.onMarketData(marketData);
	}

	@Override
	public void onOrderReport(OrderReport report) {
		ChildOrder order = OrderManager.handleOrderReport(report);
		// 调用策略实现的订单回调函数
		strategy.onOrder(order);
	}

	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		log.error("On Adaptor Event -> {}", event);
		strategy.onAdaptorEvent(event);
	}

	@Override
	public void close() throws IOException {
		log.info("Strategy [{}] closed", strategy.getStrategyName());
	}

}
