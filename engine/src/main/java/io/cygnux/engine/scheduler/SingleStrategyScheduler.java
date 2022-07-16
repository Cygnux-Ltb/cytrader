package io.cygnux.engine.scheduler;

import java.io.IOException;

import io.cygnux.engine.trader.OrderKeeper;
import org.slf4j.Logger;

import io.horizon.market.data.MarketData;
import io.horizon.market.data.MarketDataKeeper;
import io.horizon.trader.handler.InboundHandler;
import io.horizon.trader.order.ChildOrder;
import io.horizon.trader.strategy.Strategy;
import io.horizon.trader.transport.outbound.AdaptorReport;
import io.horizon.trader.transport.outbound.OrderReport;
import io.mercury.common.log.Log4j2LoggerFactory;

import javax.annotation.Nonnull;

/**
 * 
 * 单策略调度器, 直接调用策略回调函数, 没有异步操作, 最简单并且速度最快的调度器
 * 
 * @author yellow013
 *
 * @param <M>
 */
public class SingleStrategyScheduler<M extends MarketData> implements InboundHandler<M> {

	/**
	 * Logger
	 */
	private static final Logger log = Log4j2LoggerFactory.getLogger(SingleStrategyScheduler.class);

	/**
	 * Only one strategy
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
		ChildOrder order = OrderKeeper.handleOrderReport(report);
		// 调用策略实现的订单回调函数
		strategy.onOrder(order);
	}

	@Override
	public void onAdaptorReport(@Nonnull AdaptorReport report) {
		log.error("On Adaptor Report -> {}", report);
		strategy.onAdaptorReport(report);
	}

	@Override
	public void close() throws IOException {
		strategy.close();
		log.info("Strategy [{}] closed", strategy.getStrategyName());
	}

}
