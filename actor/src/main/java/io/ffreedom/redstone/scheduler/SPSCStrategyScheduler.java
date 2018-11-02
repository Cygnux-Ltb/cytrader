package io.ffreedom.redstone.scheduler;

import io.ffreedom.common.queue.disruptor.SPSCQueue;
import io.ffreedom.market.MarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.strategy.StrategyScheduler;
import io.ffreedom.redstone.state.StrategyState;

public class SPSCStrategyScheduler implements StrategyScheduler {

	private SPSCQueue<RecvMsg<?>> recvQueue;

	private final int marketDataMark = 0;

	private final int orderMark = 1;

	public SPSCStrategyScheduler(int size) {
		this.recvQueue = new SPSCQueue<>(size, true, recvMsg -> {
			switch (recvMsg.getMark()) {
			case marketDataMark:
				MarketData marketData = (MarketData) recvMsg.getContent();
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
	public void onMarketData(MarketData marketData) {
		recvQueue.enQueue(new RecvMsg<MarketData>(marketDataMark, marketData));
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
