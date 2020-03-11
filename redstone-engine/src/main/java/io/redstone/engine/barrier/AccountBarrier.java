package io.redstone.engine.barrier;

import io.redstone.core.order.impl.ChildOrder;
import io.redstone.core.risk.OrderBarrier;

public class AccountBarrier implements OrderBarrier<ChildOrder> {

	@Override
	public boolean filter(ChildOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

}
