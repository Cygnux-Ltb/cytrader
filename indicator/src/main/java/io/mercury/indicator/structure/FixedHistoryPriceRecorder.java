package io.mercury.indicator.structure;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.primitive.ImmutableLongList;
import org.eclipse.collections.api.list.primitive.MutableLongList;

import io.mercury.common.collections.MutableLists;

@NotThreadSafe
public class FixedHistoryPriceRecorder {

	private int tail = -1;
	private int count = 0;

	private int capacity;

	private MutableLongList priceList;

	private boolean isEmpty = true;
	private boolean isFull = false;

	private FixedHistoryPriceRecorder(int capacity) {
		this.capacity = capacity;
		this.priceList = MutableLists.newLongArrayList(capacity);
	}

	public static FixedHistoryPriceRecorder newRecorder(int cycle) {
		return new FixedHistoryPriceRecorder(cycle);
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public boolean isFull() {
		return isFull;
	}

	public long tail() {
		if (isEmpty)
			return 0L;
		return priceList.get(tail);
	}

	public long head() {
		if (isEmpty)
			return 0L;
		if (isFull)
			return priceList.get(tail + 1 == capacity ? 0 : tail + 1);
		return priceList.get(tail - count + 1);
	}

	public long sum() {
		return priceList.sum();
	}

	public long max() {
		return priceList.max();
	}

	public long min() {
		return priceList.min();
	}

	public long average() {
		return (long) priceList.average();
	}

	public long median() {
		return (long) priceList.median();
	}

	public ImmutableLongList toImmutable() {
		return priceList.toImmutable();
	}

	public int count() {
		return count;
	}

	public FixedHistoryPriceRecorder addTail(long value) {
		updateTail(value);
		return this;
	}

	private void updateTail(long value) {
		updateTailIndex();
		updateCount();
		if (isFull)
			priceList.set(tail, value);
		else
			priceList.add(value);
	}

	private void updateTailIndex() {
		if (++tail == capacity)
			tail = 0;
	}

	private void updateCount() {
		if (!isFull) {
			if (count == capacity) {
				isFull = true;
				return;
			}
			count++;
		}
		if (isEmpty) {
			isEmpty = false;
		}
	}

	public static void main(String[] args) {

		FixedHistoryPriceRecorder recorder = newRecorder(10);

		for (int i = 1; i < 30; i++) {
			recorder.addTail(i);
			System.out.println("Count -> " + recorder.count);
			System.out.print("Value -> ");
			recorder.priceList.each(value -> System.out.print(value + " , "));
			System.out.println();
			System.out.println("Head -> " + recorder.head());
			System.out.println("Tail -> " + recorder.tail());
		}

	}

}
