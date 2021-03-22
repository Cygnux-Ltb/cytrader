package io.cygnus.engine.barrier;

import io.horizon.structure.order.ChildOrder;
import io.horizon.structure.risk.OrderBarrier;

public class AccountBarrier implements OrderBarrier<ChildOrder> {

	@Override
	public boolean filter(ChildOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

}
