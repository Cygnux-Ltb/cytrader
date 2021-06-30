package io.cygnus.engine.barrier;

import io.horizon.trader.order.ChildOrder;
import io.horizon.trader.risk.OrderBarrier;

public class AccountBarrier implements OrderBarrier<ChildOrder> {

	@Override
	public boolean filter(ChildOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

}
