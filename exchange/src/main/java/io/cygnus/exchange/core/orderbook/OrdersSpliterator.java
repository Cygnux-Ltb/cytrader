package io.cygnus.exchange.core.orderbook;

import java.util.Spliterator;
import java.util.function.Consumer;

import io.cygnus.exchange.core.orderbook.OrderBookDirectImpl.DirectOrder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class OrdersSpliterator implements Spliterator<DirectOrder> {

	private DirectOrder pointer;

	@Override
	public boolean tryAdvance(Consumer<? super DirectOrder> action) {
		if (pointer == null) {
			return false;
		} else {
			action.accept(pointer);
			pointer = pointer.getPrev();
			return true;
		}
	}

	@Override
	public Spliterator<DirectOrder> trySplit() {
		return null;
	}

	@Override
	public long estimateSize() {
		return Long.MAX_VALUE;
	}

	@Override
	public int characteristics() {
		return Spliterator.ORDERED;
	}
}
