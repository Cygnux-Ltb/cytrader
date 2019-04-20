package io.ffreedom.redstone.scheduler;

import io.ffreedom.common.queue.impl.disruptor.SPSCQueue;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.actor.QuoteActor;
import io.ffreedom.redstone.actor.StrategyActor;
import io.ffreedom.redstone.core.order.OrderReport;
import io.ffreedom.redstone.core.strategy.StrategyScheduler;

public class SPSCStrategyScheduler implements StrategyScheduler {

	private SPSCQueue<EnqueueMsg> msgQueue;

	private static final int MarketData = 0;
	private static final int OrderReport = 1;

	public SPSCStrategyScheduler(int size) {
		this.msgQueue = new SPSCQueue<>(size, true, (enqueueMsg) -> {
			switch (enqueueMsg.mark()) {
			case MarketData:
				BasicMarketData marketData = enqueueMsg.getMarketData();
				QuoteActor.Singleton.onMarketDate(marketData);
				StrategyActor.Singleton.onMarketData(marketData);
				break;
			case OrderReport:
				OrderReport orderReport = enqueueMsg.getOrderReport();
				StrategyActor.Singleton.onOrderReport(orderReport);
				break;
			default:
				new IllegalArgumentException("mark illegal");
				break;
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
	public void onOrderReport(OrderReport orderReport) {
		msgQueue.enqueue(new EnqueueMsg(OrderReport, orderReport));
	}

	private class EnqueueMsg {

		private int mark;
		private BasicMarketData marketData;
		private OrderReport orderReport;

		EnqueueMsg(int mark, BasicMarketData marketData) {
			this.mark = mark;
			this.marketData = marketData;
		}

		EnqueueMsg(int mark, OrderReport orderReport) {
			this.mark = mark;
			this.orderReport = orderReport;
		}

		private int mark() {
			return mark;
		}

		private BasicMarketData getMarketData() {
			return marketData;
		}

		private OrderReport getOrderReport() {
			return orderReport;
		}

	}

}
