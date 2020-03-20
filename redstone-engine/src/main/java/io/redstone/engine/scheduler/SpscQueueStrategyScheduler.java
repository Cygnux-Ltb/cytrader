package io.redstone.engine.scheduler;

import org.slf4j.Logger;

import io.mercury.common.concurrent.disruptor.BufferSize;
import io.mercury.common.concurrent.disruptor.SpscQueue;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.financial.market.QuoteKeeper;
import io.mercury.financial.market.impl.BasicMarketData;
import io.redstone.core.adaptor.AdaptorEvent;
import io.redstone.core.order.Order;
import io.redstone.core.order.OrderKeeper;
import io.redstone.core.order.structure.OrdReport;
import io.redstone.core.strategy.StrategyKeeper;
import io.redstone.core.strategy.StrategyScheduler;

/**
 * 
 * @author yellow013
 * 
 *         策略执行引擎与整体框架分离
 *
 */
public final class SpscQueueStrategyScheduler implements StrategyScheduler {

	private SpscQueue<EnqueueMsg> msgQueue;

	private static final int MarketData = 0;
	private static final int OrderReport = 1;
	private static final int AdaptorEvent = 2;

	private static Logger log = CommonLoggerFactory.getLogger(SpscQueueStrategyScheduler.class);

	public SpscQueueStrategyScheduler(BufferSize size) {
		this.msgQueue = new SpscQueue<>("SPSCStrategyScheduler-Queue", size, true, (enqueueMsg) -> {
			switch (enqueueMsg.mark()) {
			case MarketData:
				BasicMarketData marketData = enqueueMsg.getMarketData();
				QuoteKeeper.onMarketDate(marketData);
				StrategyKeeper.getSubscribedStrategys(marketData.instrument().id()).forEach(strategy -> {
					if (strategy.isEnabled())
						strategy.onMarketData(marketData);
				});
				break;
			case OrderReport:
				OrdReport orderReport = enqueueMsg.getOrdReport();
				log.info("Handle OrdReport, brokerUniqueId==[{}], ordSysId==[{}]", orderReport.getBrokerUniqueId(),
						orderReport.getOrdSysId());
				Order order = OrderKeeper.getOrder(orderReport.getOrdSysId());
				log.info("Search Order OK. BrokerRtnId==[{}], strategyId==[{}], instrumentCode==[{}], ordSysId==[{}]",
						orderReport.getBrokerUniqueId(), order.strategyId(), order.instrument().code(),
						orderReport.getOrdSysId());
				StrategyKeeper.getStrategy(order.strategyId()).onOrder(order);
				break;
			default:
				throw new IllegalStateException("mark illegal");
			}
		});
	}

	// TODO add pools
	@Override
	public void onMarketData(BasicMarketData marketData) {
		msgQueue.enqueue(new EnqueueMsg(MarketData, marketData));
	}

	// TODO add pools
	@Override
	public void onOrderReport(OrdReport orderReport) {
		msgQueue.enqueue(new EnqueueMsg(OrderReport, orderReport));
	}

	@Override
	public void onAdaptorEvent(int adaptorId, AdaptorEvent event) {

	}

	private class EnqueueMsg {

		private int mark;
		private BasicMarketData marketData;
		private OrdReport ordReport;
		private AdaptorEvent adaptorEvent;

		EnqueueMsg(int mark, BasicMarketData marketData) {
			this.mark = mark;
			this.marketData = marketData;
		}

		EnqueueMsg(int mark, OrdReport ordReport) {
			this.mark = mark;
			this.ordReport = ordReport;
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

	}

}
