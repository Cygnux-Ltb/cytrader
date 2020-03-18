package io.redstone.engine.scheduler;

import io.mercury.common.concurrent.disruptor.BufferSize;
import io.mercury.common.concurrent.disruptor.SpscQueue;
import io.mercury.polaris.financial.market.QuoteKeeper;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.redstone.core.adaptor.api.AdaptorEvent;
import io.redstone.core.order.structure.OrdReport;
import io.redstone.core.strategy.StrategyScheduler;
import io.redstone.engine.actor.StrategyActor;

public final class SpscQueueStrategyScheduler implements StrategyScheduler {

	private SpscQueue<EnqueueMsg> msgQueue;

	private static final int MarketData = 0;
	private static final int OrderReport = 1;

	public SpscQueueStrategyScheduler(BufferSize size) {
		this.msgQueue = new SpscQueue<>("SPSCStrategyScheduler-Queue", size, true, (enqueueMsg) -> {
			switch (enqueueMsg.mark()) {
			case MarketData:
				BasicMarketData marketData = enqueueMsg.getMarketData();
				QuoteKeeper.onMarketDate(marketData);
				StrategyActor.Singleton.onMarketData(marketData);
				break;
			case OrderReport:
				OrdReport orderReport = enqueueMsg.getOrderReport();
				StrategyActor.Singleton.onOrderReport(orderReport);
				break;
			default:
				throw new IllegalArgumentException("mark illegal");
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
		// TODO Auto-generated method stub
	}

	private class EnqueueMsg {

		private int mark;
		private BasicMarketData marketData;
		private OrdReport orderReport;

		EnqueueMsg(int mark, BasicMarketData marketData) {
			this.mark = mark;
			this.marketData = marketData;
		}

		EnqueueMsg(int mark, OrdReport orderReport) {
			this.mark = mark;
			this.orderReport = orderReport;
		}

		private int mark() {
			return mark;
		}

		private BasicMarketData getMarketData() {
			return marketData;
		}

		private OrdReport getOrderReport() {
			return orderReport;
		}

	}

}
