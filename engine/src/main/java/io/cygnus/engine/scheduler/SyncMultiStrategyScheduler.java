package io.cygnus.engine.scheduler;

import java.io.IOException;

import org.slf4j.Logger;

import io.horizon.structure.adaptor.AdaptorEvent;
import io.horizon.structure.market.data.MarkerDataKeeper;
import io.horizon.structure.market.data.MarketData;
import io.horizon.structure.order.OrderReport;
import io.horizon.structure.order.OrderBookKeeper;
import io.horizon.structure.order.actual.ChildOrder;
import io.mercury.common.collections.Capacity;
import io.mercury.common.concurrent.queue.WaitingStrategy;
import io.mercury.common.concurrent.queue.jct.JctScQueue;
import io.mercury.common.log.CommonLoggerFactory;

/**
 * 
 * @author yellow013
 * 
 *         策略执行引擎与整体框架分离
 *
 */
public final class SyncMultiStrategyScheduler<M extends MarketData> extends MultiStrategyScheduler<M> {

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(SyncMultiStrategyScheduler.class);

	private JctScQueue<DespatchMsg> despatchQueue;

	private static final int MarketData = 0;
	private static final int OrderReport = 1;
	private static final int AdaptorEvent = 2;

	public SyncMultiStrategyScheduler(Capacity capacity) {
		this.despatchQueue = JctScQueue.spsc("QueueStrategyScheduler-Despatch").capacity(capacity.value())
				.waitingStrategy(WaitingStrategy.SpinWaiting).buildWithProcessor(despatchMsg -> {
					switch (despatchMsg.mark()) {
					case MarketData:
						M marketData = despatchMsg.getMarketData();
						MarkerDataKeeper.onMarketDate(marketData);
						subscribedMap.get(marketData.getInstrumentId()).each(strategy -> {
							if (strategy.isEnabled()) {
								strategy.onMarketData(marketData);
							}
						});
						break;
					case OrderReport:
						OrderReport report = despatchMsg.getOrdReport();
						log.info("Handle OrderReport, brokerUniqueId==[{}], ordSysId==[{}]", report.getBrokerUniqueId(),
								report.getOrdSysId());
						ChildOrder order = OrderBookKeeper.handleOrderReport(report);
						log.info(
								"Search Order OK. brokerUniqueId==[{}], strategyId==[{}], instrumentCode==[{}], ordSysId==[{}]",
								report.getBrokerUniqueId(), order.getStrategyId(),
								order.getInstrument().getInstrumentCode(), report.getOrdSysId());
						strategyMap.get(order.getStrategyId()).onOrder(order);
						break;
					case AdaptorEvent:
						AdaptorEvent adaptorEvent = despatchMsg.getAdaptorEvent();
						adaptorEvent.getAdaptorId();
						break;
					default:
						throw new IllegalStateException("scheduler mark illegal");
					}
				});
	}

	// TODO add pools
	@Override
	public void onMarketData(M marketData) {
		despatchQueue.enqueue(new DespatchMsg(marketData));
	}

	// TODO add pools
	@Override
	public void onOrderReport(OrderReport report) {
		despatchQueue.enqueue(new DespatchMsg(report));
	}

	// TODO add pools
	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		despatchQueue.enqueue(new DespatchMsg(event));
	}

	private class DespatchMsg {

		private int mark;
		private M marketData;
		private OrderReport ordReport;
		private AdaptorEvent adaptorEvent;

		DespatchMsg(M marketData) {
			this.mark = MarketData;
			this.marketData = marketData;
		}

		DespatchMsg(OrderReport ordReport) {
			this.mark = OrderReport;
			this.ordReport = ordReport;
		}

		DespatchMsg(AdaptorEvent adaptorEvent) {
			this.mark = AdaptorEvent;
			this.adaptorEvent = adaptorEvent;
		}

		private int mark() {
			return mark;
		}

		private M getMarketData() {
			return marketData;
		}

		private OrderReport getOrdReport() {
			return ordReport;
		}

		private AdaptorEvent getAdaptorEvent() {
			return adaptorEvent;
		}

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
