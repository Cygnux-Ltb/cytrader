package io.cygnuxltb.engine.barrier;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.stereotype.Component;

import io.horizon.trader.order.ChildOrder;
import io.horizon.trader.risk.OrderBarrier;


@Component
@NotThreadSafe
public class AccountBarrier implements OrderBarrier {


	@Override
	public boolean filter(ChildOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

}
