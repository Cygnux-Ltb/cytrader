package io.cygnus.engine.barrier;

import io.horizon.definition.order.actual.ChildOrder;
import io.horizon.definition.risk.OrderBarrier;

public class AccountBarrier implements OrderBarrier<ChildOrder> {

	@Override
	public boolean filter(ChildOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

}
