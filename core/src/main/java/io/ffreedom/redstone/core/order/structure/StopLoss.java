package io.ffreedom.redstone.core.order.structure;

import io.ffreedom.redstone.core.trade.enums.TrdDirection;

public final class StopLoss implements Comparable<StopLoss> {

	private long ordSysId;
	private double stopLossPrice;
	private boolean greaterOrEqual;
	private boolean LessOrEqual;

	public StopLoss(long ordSysId, TrdDirection direction) {
		super();
		this.ordSysId = ordSysId;
		switch (direction) {
		case Long:
			greaterOrEqual = false;
			LessOrEqual = true;
			break;
		case Short:
			greaterOrEqual = true;
			LessOrEqual = false;
			break;
		default:
			throw new RuntimeException("");
		}
	}

	public long getOrdSysId() {
		return ordSysId;
	}

	public double getStopLossPrice() {
		return stopLossPrice;
	}

	public StopLoss setStopLossPrice(double stopLossPrice) {
		this.stopLossPrice = stopLossPrice;
		return this;
	}

	public boolean isGreaterOrEqual() {
		return greaterOrEqual;
	}

	public boolean isLessOrEqual() {
		return LessOrEqual;
	}

	@Override
	public int compareTo(StopLoss o) {
		return 0;
	}

}
