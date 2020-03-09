package io.redstone.core.order.structure;

import io.redstone.core.trade.enums.TrdDirection;

public final class StopLoss implements Comparable<StopLoss> {

	private long ordSysId;
	private long stopLossPrice;
	private boolean greaterOrEqual;
	private boolean lessOrEqual;

	public StopLoss(long ordSysId, TrdDirection direction) {
		super();
		this.ordSysId = ordSysId;
		switch (direction) {
		case Long:
			greaterOrEqual = false;
			lessOrEqual = true;
			break;
		case Short:
			greaterOrEqual = true;
			lessOrEqual = false;
			break;
		default:
			throw new RuntimeException("");
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

	public boolean isGreaterOrEqual() {
		return greaterOrEqual;
	}

	public boolean isLessOrEqual() {
		return lessOrEqual;
	}

	@Override
	public int compareTo(StopLoss o) {
		return 0;
	}

}
