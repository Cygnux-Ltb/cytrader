package io.mercury.redstone.engine.barrier;

import io.mercury.redstone.core.order.ChildOrder;
import io.mercury.redstone.core.risk.OrderBarrier;

public class AccountBarrier implements OrderBarrier<ChildOrder> {

	@Override
	public boolean filter(ChildOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

}
