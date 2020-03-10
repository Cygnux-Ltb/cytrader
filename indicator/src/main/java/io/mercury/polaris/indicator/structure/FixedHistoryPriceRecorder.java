package io.mercury.polaris.indicator.structure;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.primitive.ImmutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;

import io.mercury.common.collections.MutableLists;
import io.mercury.polaris.indicator.api.CalculationCycle;

@NotThreadSafe
public class FixedHistoryPriceRecorder {

	private int tail = -1;
	private int count = 0;

	private int capacity;

	private MutableDoubleList priceList;

	private boolean isEmpty = true;
	private boolean isFull = false;

	private final static double NothingPrice = 0.0d;

	private FixedHistoryPriceRecorder(int capacity) {
		this.capacity = capacity;
		this.priceList = MutableLists.newDoubleArrayList(capacity);
	}

	public static FixedHistoryPriceRecorder newRecorder(CalculationCycle cycle) {
		return new FixedHistoryPriceRecorder(cycle.getCycleValue());
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public boolean isFull() {
		return isFull;
	}

	public double tail() {
		if (isEmpty)
			return NothingPrice;
		return priceList.get(tail);
	}

	public double head() {
		if (isEmpty)
			return NothingPrice;
		if (isFull)
			return priceList.get(tail + 1 == capacity ? 0 : tail + 1);
		return priceList.get(tail - count + 1);
	}

	public double sum() {
		return priceList.sum();
	}

	public double max() {
		return priceList.max();
	}

	public double min() {
		return priceList.min();
	}

	public double average() {
		return priceList.average();
	}

	public double median() {
		return priceList.median();
	}

	public ImmutableDoubleList toImmutable() {
		return priceList.toImmutable();
	}

	public int count() {
		return count;
	}

	public FixedHistoryPriceRecorder addTail(double value) {
		updateTail(value);
		return this;
	}

	private void updateTail(double value) {
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

		FixedHistoryPriceRecorder recorder = newRecorder(CalculationCycle.with(10));

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
