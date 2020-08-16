package io.mercury.redstone.engine.barrier;

import io.mercury.redstone.core.order.ActualChildOrder;
import io.mercury.redstone.core.risk.OrderBarrier;

public class AccountBarrier implements OrderBarrier<ActualChildOrder> {

	@Override
	public boolean filter(ActualChildOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

}
