package io.ffreedom.redstone.actor;

import com.lmax.disruptor.EventHandler;

import io.ffreedom.redstone.core.order.api.Order;

public final class OrderDumpActor implements EventHandler<Order> {

	@Override
	public void onEvent(Order event, long sequence, boolean endOfBatch) throws Exception {
		// TODO Auto-generated method stub

	}

}
