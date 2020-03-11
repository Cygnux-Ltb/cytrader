package io.redstone.core.barrier;

import java.util.function.Predicate;

import io.redstone.core.order.api.Order;

@FunctionalInterface
public interface OrderBarrier<O extends Order> extends Predicate<O> {

	boolean filter(O order);

	@Override
	default boolean test(O t) {
		return filter(t);
	}

}
