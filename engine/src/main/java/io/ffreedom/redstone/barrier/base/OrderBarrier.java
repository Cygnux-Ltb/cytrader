package io.ffreedom.redstone.barrier.base;

import java.util.function.Predicate;

import io.ffreedom.redstone.core.order.Order;

@FunctionalInterface
public interface OrderBarrier<O extends Order> extends Predicate<O> {

	boolean filter(O order);

	@Override
	default boolean test(O t) {
		return filter(t);
	}

}
