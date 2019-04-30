package io.ffreedom.redstone.barrier;

import io.ffreedom.redstone.core.barrier.OrderBarrier;
import io.ffreedom.redstone.core.order.impl.ChildOrder;

public class AccountBarrier implements OrderBarrier<ChildOrder> {

	@Override
	public boolean filter(ChildOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

}
