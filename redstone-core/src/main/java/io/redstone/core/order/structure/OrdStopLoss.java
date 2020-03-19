package io.redstone.core.order.structure;

import io.redstone.core.trade.enums.TrdDirection;

public final class StopLoss implements Comparable<StopLoss> {

	private long ordSysId;
	private long stopLossPrice;

	public StopLoss(long ordSysId, TrdDirection direction) {
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

	public StopLoss stopLossPrice(long stopLossPrice) {
		this.stopLossPrice = stopLossPrice;
		return this;
	}


	@Override
	public int compareTo(StopLoss o) {
		return 0;
	}

}
