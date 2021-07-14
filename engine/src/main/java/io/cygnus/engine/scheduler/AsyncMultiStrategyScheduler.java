package io.cygnus.engine.scheduler;

import java.io.IOException;

import org.slf4j.Logger;

import io.cygnus.engine.om.OrderKeeper;
import io.horizon.market.data.MarketData;
import io.horizon.market.data.MarketDataKeeper;
import io.horizon.trader.adaptor.AdaptorEvent;
import io.horizon.trader.order.ChildOrder;
import io.horizon.trader.order.OrderReport;
import io.mercury.common.collections.Capacity;
import io.mercury.common.concurrent.queue.jct.JctSingleConsumerQueue;
import io.mercury.common.log.CommonLoggerFactory;
import lombok.Getter;

/**
 * 
 * @author yellow013
 * 
 *         策略执行引擎与整体框架分离
 *
 */
public final class AsyncMultiStrategyScheduler<M extends MarketData> extends AbstractMultiStrategyScheduler<M> {

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(AsyncMultiStrategyScheduler.class);

	private final JctSingleConsumerQueue<QueueMsg> queue;

	private static final int MarketData = 0;
	private static final int OrderReport = 1;
	private static final int AdaptorEvent = 2;

	public AsyncMultiStrategyScheduler(Capacity capacity) {
		this.queue = JctSingleConsumerQueue.singleProducer("AsyncMultiStrategyScheduler-Queue")
				.setCapacity(capacity.value()).useSpinStrategy().buildWithProcessor(msg -> {
					switch (msg.getMark()) {
					case MarketData:
						M marketData = msg.getMarketData();
						MarketDataKeeper.onMarketDate(marketData);
						subscribedMap.get(marketData.getInstrumentId()).each(strategy -> {
							if (strategy.isEnabled()) {
								strategy.onMarketData(marketData);
							}
						});
						break;
					case OrderReport:
						OrderReport report = msg.getOrdReport();
						log.info("Handle OrderReport, brokerUniqueId==[{}], ordSysId==[{}]", report.getBrokerUniqueId(),
								report.getOrdSysId());
						ChildOrder order = OrderKeeper.handleOrderReport(report);
						log.info(
								"Search Order OK. brokerUniqueId==[{}], strategyId==[{}], instrumentCode==[{}], ordSysId==[{}]",
								report.getBrokerUniqueId(), order.getStrategyId(),
								order.getInstrument().getInstrumentCode(), report.getOrdSysId());
						strategyMap.get(order.getStrategyId()).onOrder(order);
						break;
					case AdaptorEvent:
						AdaptorEvent adaptorEvent = msg.getAdaptorEvent();
						adaptorEvent.getAdaptorId();
						log.info("Recv AdaptorEvent -> {}", adaptorEvent);
						break;
					default:
						throw new IllegalStateException("scheduler mark illegal");
					}
				});
	}

	// TODO add pools
	@Override
	public void onMarketData(M marketData) {
		queue.enqueue(new QueueMsg(marketData));
	}

	// TODO add pools
	@Override
	public void onOrderReport(OrderReport report) {
		queue.enqueue(new QueueMsg(report));
	}

	// TODO add pools
	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		queue.enqueue(new QueueMsg(event));
	}

	private class QueueMsg {

		@Getter
		private final int mark;
		@Getter
		private M marketData;
		@Getter
		private OrderReport ordReport;
		@Getter
		private AdaptorEvent adaptorEvent;

		private QueueMsg(M marketData) {
			this.mark = MarketData;
			this.marketData = marketData;
		}

		private QueueMsg(OrderReport ordReport) {
			this.mark = OrderReport;
			this.ordReport = ordReport;
		}

		private QueueMsg(AdaptorEvent adaptorEvent) {
			this.mark = AdaptorEvent;
			this.adaptorEvent = adaptorEvent;
		}

	}

	@Override
	protected void close0() throws IOException {
		// TODO Auto-generated method stub

	}

}
