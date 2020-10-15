package io.apollo.core.risk;

import java.util.function.Predicate;

import io.gemini.definition.order.Order;

@FunctionalInterface
public interface OrderBarrier<O extends Order> extends Predicate<O> {

	boolean filter(O order);

	@Override
	default boolean test(O t) {
		return filter(t);
	}

}
