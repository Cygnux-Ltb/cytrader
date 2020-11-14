package io.apollo.engine.barrier;

import io.apollo.core.risk.OrderBarrier;
import io.gemini.definition.order.actual.ChildOrder;

public class AdaptorBarrier implements OrderBarrier<ChildOrder> {

	@Override
	public boolean filter(ChildOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

}
