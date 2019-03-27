package io.ffreedom.redstone.scheduler;

import io.ffreedom.common.queue.impl.disruptor.SPSCQueue;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.actor.StrategyActor;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.strategy.StrategyScheduler;

public class SPSCStrategyScheduler implements StrategyScheduler {

	private SPSCQueue<EnqueueMsg> msgQueue;

	private static final int MarketData = 0;
	private static final int InboundOrder = 1;
	private static final int OutboundOrder = 2;

	public SPSCStrategyScheduler(int size) {
		this.msgQueue = new SPSCQueue<>(size, true, enqueueMsg -> {
			switch (enqueueMsg.mark()) {
			case MarketData:
				BasicMarketData marketData = enqueueMsg.getMarketData();
				StrategyActor.Singleton.onMarketData(marketData);
				
				break;
			case InboundOrder:
				Order inboundOrder = enqueueMsg.getOrder();
				StrategyActor.Singleton.onOrder(inboundOrder);
				break;
			case OutboundOrder:
				Order outboundOrder = enqueueMsg.getOrder();
				
				break;
			default:
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
	public void onInboundOrder(Order order) {
		msgQueue.enqueue(new EnqueueMsg(InboundOrder, order));
	}

	private class EnqueueMsg {

		private int mark;
		private BasicMarketData marketData;
		private Order order;

		EnqueueMsg(int mark, BasicMarketData marketData) {
			this.mark = mark;
			this.marketData = marketData;
		}

		EnqueueMsg(int mark, Order order) {
			this.mark = mark;
			this.order = order;
		}

		public int mark() {
			return mark;
		}

		public BasicMarketData getMarketData() {
			return marketData;
		}

		public Order getOrder() {
			return order;
		}

	}

}
