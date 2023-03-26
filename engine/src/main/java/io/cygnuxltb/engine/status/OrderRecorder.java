package io.cygnuxltb.engine.status;

import com.lmax.disruptor.EventHandler;

import io.horizon.trader.order.Order;

public final class OrderRecorder implements EventHandler<Order> {

	@Override
	public void onEvent(Order event, long sequence, boolean endOfBatch) {
		// TODO
	}

}
