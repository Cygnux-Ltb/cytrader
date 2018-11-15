package io.ffreedom.redstone.barrier;

import io.ffreedom.redstone.barrier.base.OrderBarrier;
import io.ffreedom.redstone.core.order.ChildOrder;

public class AccountBarrier implements OrderBarrier<ChildOrder> {

	@Override
	public boolean filter(ChildOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

}
