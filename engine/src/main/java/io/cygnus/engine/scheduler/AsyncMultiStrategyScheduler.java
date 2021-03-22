package io.cygnus.engine.scheduler;

import java.io.IOException;

import org.slf4j.Logger;

import io.horizon.structure.adaptor.AdaptorEvent;
import io.horizon.structure.market.data.MarkerDataKeeper;
import io.horizon.structure.market.data.MarketData;
import io.horizon.structure.order.ChildOrder;
import io.horizon.structure.order.OrderManager;
import io.horizon.structure.order.OrderReport;
import io.mercury.common.collections.Capacity;
import io.mercury.common.concurrent.queue.WaitingStrategy;
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
public final class AsyncMultiStrategyScheduler<M extends MarketData> extends BaseMultiStrategyScheduler<M> {

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(AsyncMultiStrategyScheduler.class);

	private final JctSingleConsumerQueue<InternalMsg> despatchQueue;

	private static final int MarketData = 0;
	private static final int OrderReport = 1;
	private static final int AdaptorEvent = 2;

	public AsyncMultiStrategyScheduler(Capacity capacity) {
		this.despatchQueue = JctSingleConsumerQueue.newSingleProducerQueue("AsyncMultiStrategyScheduler-Queue")
				.capacity(capacity.value()).waitingStrategy(WaitingStrategy.SpinWaiting).buildWithProcessor(msg -> {
					switch (msg.getMark()) {
					case MarketData:
						M marketData = msg.getMarketData();
						MarkerDataKeeper.onMarketDate(marketData);
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
						ChildOrder order = OrderManager.handleOrderReport(report);
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
		despatchQueue.enqueue(new InternalMsg(marketData));
	}

	// TODO add pools
	@Override
	public void onOrderReport(OrderReport report) {
		despatchQueue.enqueue(new InternalMsg(report));
	}

	// TODO add pools
	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		despatchQueue.enqueue(new InternalMsg(event));
	}

	private class InternalMsg {

		@Getter
		private final int mark;
		@Getter
		private M marketData;
		@Getter
		private OrderReport ordReport;
		@Getter
		private AdaptorEvent adaptorEvent;

		private InternalMsg(M marketData) {
			this.mark = MarketData;
			this.marketData = marketData;
		}

		private InternalMsg(OrderReport ordReport) {
			this.mark = OrderReport;
			this.ordReport = ordReport;
		}

		private InternalMsg(AdaptorEvent adaptorEvent) {
			this.mark = AdaptorEvent;
			this.adaptorEvent = adaptorEvent;
		}

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

}
