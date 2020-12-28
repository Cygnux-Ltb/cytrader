package io.cygnus.engine.actor;

import com.lmax.disruptor.EventHandler;

import io.horizon.structure.order.Order;

public final class OrderRecoreder implements EventHandler<Order> {

	@Override
	public void onEvent(Order event, long sequence, boolean endOfBatch) throws Exception {
		// TODO
	}

}
