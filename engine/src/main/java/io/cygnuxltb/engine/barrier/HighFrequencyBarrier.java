package io.cygnuxltb.engine.barrier;

import io.horizon.trader.order.ChildOrder;
import io.horizon.trader.risk.OrderBarrier;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import org.slf4j.Logger;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class HighFrequencyBarrier implements OrderBarrier {

	private static final Logger log = Log4j2LoggerFactory.getLogger(HighFrequencyBarrier.class);

	@Override
	public boolean filter(ChildOrder order) {
		switch (order.getDirection()) {
			case Long -> {
				return false;
			}
			case Short -> {
				return false;
			}
			default -> {
				log.error("");
				return false;
			}
		}
	}

	public static void main(String[] args) {

		System.out.println(Integer.MAX_VALUE);

	}

}
