package io.apollo.engine.barrier;

import io.apollo.core.risk.OrderBarrier;
import io.gemini.definition.order.ActualChildOrder;

public class AccountBarrier implements OrderBarrier<ActualChildOrder> {

	@Override
	public boolean filter(ActualChildOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

}
