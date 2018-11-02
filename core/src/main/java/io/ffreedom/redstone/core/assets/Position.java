package io.ffreedom.redstone.core.assets;

public final class Position implements Comparable<Position> {

	private int instrumentId;
	private double currentQty;
	private double availableQty;

	public final static Position EMPTY = Position.create(-1).setCurrentQty(0).setAvailableQty(0);

	private Position(int instrumentId) {
		this.instrumentId = instrumentId;
	}

	public final static Position create(int instrumentId) {
		return new Position(instrumentId);
	}

	public Position setCurrentQty(double currentQty) {
		this.currentQty = currentQty;
		return this;
	}

	public Position setAvailableQty(double availableQty) {
		this.availableQty = availableQty;
		return this;
	}

	public int getInstrumentId() {
		return instrumentId;
	}

	public double getCurrentQty() {
		return currentQty;
	}

	public double getAvailableQty() {
		return availableQty;
	}

	@Override
	public int compareTo(Position o) {
		return this.instrumentId < o.instrumentId ? -1 : this.instrumentId > o.instrumentId ? 1 : 0;
	}

}
