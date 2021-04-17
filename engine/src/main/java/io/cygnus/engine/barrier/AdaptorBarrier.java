package io.cygnus.engine.barrier;

import io.horizon.transaction.order.ChildOrder;
import io.horizon.transaction.risk.OrderBarrier;

public class AdaptorBarrier implements OrderBarrier<ChildOrder> {

	@Override
	public boolean filter(ChildOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

}
