package io.ffreedom.redstone.barrier;

import org.slf4j.Logger;

import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.redstone.barrier.base.OrderBarrier;
import io.ffreedom.redstone.core.order.ChildOrder;

public final class HighFrequencyBarrier implements OrderBarrier<ChildOrder> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean filter(ChildOrder order) {
		switch (order.getSide()) {
		case BUY:
		case MARGIN_BUY:
			
			return false;

		case SELL:
		case SHORT_SELL:

			return false;

		default:
			logger.error("");
			return false;
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println(Integer.MAX_VALUE);
		
		
	}

}
