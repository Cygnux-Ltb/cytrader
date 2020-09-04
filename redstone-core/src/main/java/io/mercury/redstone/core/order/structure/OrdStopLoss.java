package io.mercury.redstone.core.order.structure;

import io.mercury.redstone.core.order.enums.TrdDirection;

public final class OrdStopLoss implements Comparable<OrdStopLoss> {

	private long uniqueId;
	private long stopLossPrice;

	public OrdStopLoss(long uniqueId, TrdDirection direction) {
		super();
		this.uniqueId = uniqueId;
		switch (direction) {
		case Long:
			stopLossPrice = Long.MAX_VALUE;
			break;
		case Short:
			stopLossPrice = Long.MIN_VALUE;
			break;
		default:
			throw new RuntimeException("direction error");
		}
	}

	public long uniqueId() {
		return uniqueId;
	}

	public long stopLossPrice() {
		return stopLossPrice;
	}

	public OrdStopLoss stopLossPrice(long stopLossPrice) {
		this.stopLossPrice = stopLossPrice;
		return this;
	}

	@Override
	public int compareTo(OrdStopLoss o) {
		return uniqueId < o.uniqueId ? -1 : uniqueId > o.uniqueId ? 1 : 0;
	}

}
