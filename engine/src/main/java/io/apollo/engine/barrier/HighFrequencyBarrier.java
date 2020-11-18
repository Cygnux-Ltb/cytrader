package io.apollo.engine.barrier;

import org.slf4j.Logger;

import io.apollo.core.risk.OrderBarrier;
import io.horizon.definition.order.actual.ChildOrder;
import io.mercury.common.log.CommonLoggerFactory;

public final class HighFrequencyBarrier implements OrderBarrier<ChildOrder> {

	private static final Logger log = CommonLoggerFactory.getLogger(HighFrequencyBarrier.class);

	@Override
	public boolean filter(ChildOrder order) {
		switch (order.direction()) {
		case Long:
			
			return false;
		
		case Short:

			return false;

		default:
			log.error("");
			return false;
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println(Integer.MAX_VALUE);
		
		
	}

}
