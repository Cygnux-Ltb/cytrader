package io.redstone.core.order.structure;

import io.redstone.core.order.enums.TrdDirection;

public final class OrdStopLoss implements Comparable<OrdStopLoss> {

	private long ordSysId;
	private long stopLossPrice;

	public OrdStopLoss(long ordSysId, TrdDirection direction) {
		super();
		this.ordSysId = ordSysId;
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

	public long ordSysId() {
		return ordSysId;
	}

	public double stopLossPrice() {
		return stopLossPrice;
	}

	public OrdStopLoss stopLossPrice(long stopLossPrice) {
		this.stopLossPrice = stopLossPrice;
		return this;
	}


	@Override
	public int compareTo(OrdStopLoss o) {
		return 0;
	}

}
