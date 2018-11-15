package io.ffreedom.redstone.scheduler;

import io.ffreedom.common.queue.disruptor.SPSCQueue;
import io.ffreedom.market.BasicMarketData;
import io.ffreedom.redstone.actor.StrategyState;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.strategy.StrategyScheduler;

public class SPSCStrategyScheduler implements StrategyScheduler {

	private SPSCQueue<RecvMsg<?>> recvQueue;

	private final int marketDataMark = 0;

	private final int orderMark = 1;

	public SPSCStrategyScheduler(int size) {
		this.recvQueue = new SPSCQueue<>(size, true, recvMsg -> {
			switch (recvMsg.getMark()) {
			case marketDataMark:
				BasicMarketData marketData = (BasicMarketData) recvMsg.getContent();
				StrategyState.INSTANCE.onMarketData(marketData);
				break;
			case orderMark:
				Order order = (Order) recvMsg.getContent();
				StrategyState.INSTANCE.onOrder(order);
				break;
			default:
				break;
			}
		});
	}

	// TODO Pool RecvMsg
	@Override
	public void onMarketData(BasicMarketData marketData) {
		recvQueue.enQueue(new RecvMsg<BasicMarketData>(marketDataMark, marketData));
	}

	// TODO Pool RecvMsg
	@Override
	public void onOrder(Order order) {
		recvQueue.enQueue(new RecvMsg<Order>(orderMark, order));
	}

	private class RecvMsg<T> {

		private int mark;
		private T t;

		RecvMsg(int mark, T t) {
			super();
			this.mark = mark;
			this.t = t;
		}

		public int getMark() {
			return mark;
		}

		public T getContent() {
			return t;
		}

	}

}
