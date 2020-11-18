package io.cygnus.engine.barrier;

import io.cygnus.core.risk.OrderBarrier;
import io.horizon.definition.order.actual.ChildOrder;

public class AccountBarrier implements OrderBarrier<ChildOrder> {

	@Override
	public boolean filter(ChildOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

}
