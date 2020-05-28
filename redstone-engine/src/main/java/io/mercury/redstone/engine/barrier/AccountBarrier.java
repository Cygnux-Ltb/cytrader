package io.mercury.redstone.engine.barrier;

import io.mercury.redstone.core.order.ActChildOrder;
import io.mercury.redstone.core.risk.OrderBarrier;

public class AccountBarrier implements OrderBarrier<ActChildOrder> {

	@Override
	public boolean filter(ActChildOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

}
