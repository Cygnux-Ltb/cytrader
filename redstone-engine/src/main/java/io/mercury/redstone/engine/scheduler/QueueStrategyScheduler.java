package io.mercury.redstone.engine.scheduler;

import io.mercury.common.collections.Capacity;
import io.mercury.common.concurrent.disruptor.SpscQueue;
import io.mercury.financial.market.MarkerDataKeeper;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.redstone.core.adaptor.AdaptorEvent;
import io.mercury.redstone.core.order.ActualChildOrder;
import io.mercury.redstone.core.order.OrderKeeper;
import io.mercury.redstone.core.order.structure.OrdReport;

/**
 * 
 * @author yellow013
 * 
 *         策略执行引擎与整体框架分离
 *
 */
public final class QueueStrategyScheduler extends MultipleStrategyScheduler<BasicMarketData> {

	private SpscQueue<DespatchMsg> despatchQueue;

	private static final int MarketData = 0;
	private static final int OrderReport = 1;
	private static final int AdaptorEvent = 2;

	public QueueStrategyScheduler(Capacity capacity) {
		this.despatchQueue = new SpscQueue<>("QueueStrategyScheduler-Despatch", capacity, true, despatchMsg -> {
			switch (despatchMsg.mark()) {
			case MarketData:
				BasicMarketData marketData = despatchMsg.getMarketData();
				MarkerDataKeeper.onMarketDate(marketData);
				subscribedMap.get(marketData.getInstrument().id()).each(strategy -> {
					if (strategy.isEnabled()) {
						strategy.onMarketData(marketData);
					}
				});
				break;
			case OrderReport:
				OrdReport ordReport = despatchMsg.getOrdReport();
				log.info("Handle OrdReport, brokerUniqueId==[{}], uniqueId==[{}]", ordReport.getBrokerUniqueId(),
						ordReport.getUniqueId());
				ActualChildOrder order = OrderKeeper.onOrdReport(ordReport);
				log.info("Search Order OK. brokerUniqueId==[{}], strategyId==[{}], instrumentCode==[{}], uniqueId==[{}]",
						ordReport.getBrokerUniqueId(), order.strategyId(), order.instrument().code(),
						ordReport.getUniqueId());
				strategyMap.get(order.strategyId()).onOrder(order);
				break;
			case AdaptorEvent:
				despatchMsg.getAdaptorEvent();
				break;
			default:
				throw new IllegalStateException("scheduler mark illegal");
			}
		});
	}

	// TODO add pools
	@Override
	public void onMarketData(BasicMarketData marketData) {
		despatchQueue.enqueue(new DespatchMsg(marketData));
	}

	// TODO add pools
	@Override
	public void onOrdReport(OrdReport report) {
		despatchQueue.enqueue(new DespatchMsg(report));
	}

	// TODO add pools
	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		despatchQueue.enqueue(new DespatchMsg(event));
	}

	private class DespatchMsg {

		private int mark;
		private BasicMarketData marketData;
		private OrdReport ordReport;
		private AdaptorEvent adaptorEvent;

		DespatchMsg(BasicMarketData marketData) {
			this.mark = MarketData;
			this.marketData = marketData;
		}

		DespatchMsg(OrdReport ordReport) {
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

		private BasicMarketData getMarketData() {
			return marketData;
		}

		private OrdReport getOrdReport() {
			return ordReport;
		}

		private AdaptorEvent getAdaptorEvent() {
			return adaptorEvent;
		}

	}

}
