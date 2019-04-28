package io.ffreedom.redstone.core.order.structure;

public final class StopLoss implements Comparable<StopLoss>{

	private long ordSysId;
	private double stopLossPrice;

	public StopLoss(long ordSysId) {
		super();
		this.ordSysId = ordSysId;
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

	@Override
	public int compareTo(StopLoss o) {
		 
		return 0;
	}

}
