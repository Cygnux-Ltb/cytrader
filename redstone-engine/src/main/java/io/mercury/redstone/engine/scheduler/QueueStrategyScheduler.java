package io.mercury.redstone.engine.scheduler;

import org.slf4j.Logger;

import io.mercury.common.concurrent.disruptor.BufferSize;
import io.mercury.common.concurrent.disruptor.SpscQueue;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.financial.market.MarkerDataKeeper;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.redstone.core.adaptor.Adaptor.AdaptorStatus;
import io.mercury.redstone.core.adaptor.AdaptorEvent;
import io.mercury.redstone.core.order.Order;
import io.mercury.redstone.core.order.OrderKeeper;
import io.mercury.redstone.core.order.structure.OrdReport;
import io.mercury.redstone.core.strategy.Strategy;
import io.mercury.redstone.core.strategy.StrategyKeeper;
import io.mercury.redstone.core.strategy.StrategyScheduler;

/**
 * 
 * @author yellow013
 * 
 *         策略执行引擎与整体框架分离
 *
 */
public final class SpscQueueStrategyScheduler implements StrategyScheduler {

	private SpscQueue<DespatchMsg> despatchQueue;

	private static final int MarketData = 0;
	private static final int OrderReport = 1;
	private static final int AdaptorEvent = 2;

	private static final Logger log = CommonLoggerFactory.getLogger(SpscQueueStrategyScheduler.class);

	public SpscQueueStrategyScheduler(BufferSize size) {
		this.despatchQueue = new SpscQueue<>("SPSCStrategyScheduler-Queue", size, true, despatchMsg -> {
			switch (despatchMsg.mark()) {
			case MarketData:
				BasicMarketData marketData = despatchMsg.getMarketData();
				MarkerDataKeeper.onMarketDate(marketData);
				StrategyKeeper.getSubscribedStrategys(marketData.instrument().id()).each(strategy -> {
					if (strategy.isEnabled())
						strategy.onMarketData(marketData);
				});
				break;
			case OrderReport:
				OrdReport ordReport = despatchMsg.getOrdReport();
				log.info("Handle OrdReport, brokerUniqueId==[{}], ordSysId==[{}]", ordReport.getBrokerUniqueId(),
						ordReport.getOrdSysId());
				Order order = OrderKeeper.getOrder(ordReport.getOrdSysId());
				log.info("Search Order OK. BrokerRtnId==[{}], strategyId==[{}], instrumentCode==[{}], ordSysId==[{}]",
						ordReport.getBrokerUniqueId(), order.strategyId(), order.instrument().code(),
						ordReport.getOrdSysId());
				StrategyKeeper.getStrategy(order.strategyId()).onOrder(order);
				break;
			case AdaptorEvent:
				int adaptorId = despatchMsg.getAdaptorId();
				AdaptorStatus adaptorStatus = despatchMsg.getAdaptorStatus();
				AdaptorEvent event = new AdaptorEvent(adaptorId).setAdaptorStatus(adaptorStatus);
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
	public void onOrdReport(OrdReport ordReport) {
		despatchQueue.enqueue(new DespatchMsg(ordReport));
	}

	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		despatchQueue.enqueue(new DespatchMsg(event.adaptorId(), event.adaptorStatus()));
	}

	@Override
	public void addStrategy(Strategy strategy) {
		// TODO Auto-generated method stub
		
	}

	private class DespatchMsg {

		private int mark;
		private BasicMarketData marketData;
		private OrdReport ordReport;
		private int adaptorId;
		private AdaptorStatus adaptorStatus;

		DespatchMsg(BasicMarketData marketData) {
			this.mark = MarketData;
			this.marketData = marketData;
		}

		DespatchMsg(OrdReport ordReport) {
			this.mark = OrderReport;
			this.ordReport = ordReport;
		}

		DespatchMsg(int adaptorId, AdaptorStatus adaptorStatus) {
			this.adaptorId = adaptorId;
			this.adaptorStatus = adaptorStatus;
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

		private int getAdaptorId() {
			return adaptorId;
		}

		private AdaptorStatus getAdaptorStatus() {
			return adaptorStatus;
		}

	}

}