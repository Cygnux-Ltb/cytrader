package io.redstone.engine.actor;

import com.lmax.disruptor.EventHandler;

import io.redstone.core.order.api.Order;

public final class OrderDumpActor implements EventHandler<Order> {

	@Override
	public void onEvent(Order event, long sequence, boolean endOfBatch) throws Exception {
		// TODO Auto-generated method stub

	}

}
