package io.cygnus.engine.scheduler;

import java.io.IOException;

import org.slf4j.Logger;

import io.cygnus.engine.strategy.api.Strategy;
import io.horizon.market.data.MarketData;
import io.horizon.market.data.MarketDataKeeper;
import io.horizon.trader.adaptor.AdaptorEvent;
import io.horizon.trader.event.InboundScheduler;
import io.horizon.trader.order.ChildOrder;
import io.horizon.trader.order.OrderManager;
import io.horizon.trader.order.OrderReport;
import io.mercury.common.log.CommonLoggerFactory;

/**
 * 
 * 单策略调度器, 直接调用策略回调函数, 没有异步操作, 最简单并且速度最快的调度器
 * 
 * @author yellow013
 *
 * @param <M>
 */
public class SingleStrategyScheduler<M extends MarketData> implements InboundScheduler<M> {

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(SingleStrategyScheduler.class);

	/**
	 * only one strategy
	 */
	private final Strategy<M> strategy;

	public SingleStrategyScheduler(Strategy<M> strategy) {
		this.strategy = strategy;
	}

	@Override
	public void onMarketData(M marketData) {
		MarketDataKeeper.onMarketDate(marketData);
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
		strategy.close();
		log.info("Strategy [{}] closed", strategy.getStrategyName());
	}

}
