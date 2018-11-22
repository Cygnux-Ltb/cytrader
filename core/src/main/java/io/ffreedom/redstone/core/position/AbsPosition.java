package io.ffreedom.redstone.core.position;

public abstract class AbsPosition implements Position {

	protected int instrumentId;
	protected double currentQty;

	public AbsPosition(int instrumentId) {
		this.instrumentId = instrumentId;
	}

	@Override
	public int getInstrumentId() {
		return instrumentId;
	}

	@Override
	public double getCurrentQty() {
		return currentQty;
	}

	@Override
	public void setCurrentQty(double qty) {
		this.currentQty = qty;
	}

	@Override
	public int compareTo(Position o) {
		return this.instrumentId < o.getInstrumentId() ? -1 : this.instrumentId > o.getInstrumentId() ? 1 : 0;
	}

}
