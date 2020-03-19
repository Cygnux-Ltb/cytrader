package io.redstone.engine.actor;

import com.lmax.disruptor.EventHandler;

import io.redstone.core.order.Order;

public final class OrderRecoreder implements EventHandler<Order> {

	@Override
	public void onEvent(Order event, long sequence, boolean endOfBatch) throws Exception {
		// TODO Auto-generated method stub

	}

}
