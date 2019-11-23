package io.redstone.barrier;

import io.redstone.core.barrier.OrderBarrier;
import io.redstone.core.order.impl.ChildOrder;

public class AdaptorBarrier implements OrderBarrier<ChildOrder> {

	@Override
	public boolean filter(ChildOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

}
