package io.ffreedom.redstone.core.barrier;

import java.util.function.Predicate;

import io.ffreedom.redstone.core.order.api.Order;

@FunctionalInterface
public interface OrderBarrier<O extends Order> extends Predicate<O> {

	boolean filter(O order);

	@Override
	default boolean test(O t) {
		return filter(t);
	}

}
