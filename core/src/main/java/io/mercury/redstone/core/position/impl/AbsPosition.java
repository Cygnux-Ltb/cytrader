package io.mercury.redstone.core.position.impl;

import io.mercury.redstone.core.position.api.Position;

public abstract class AbsPosition implements Position {

	protected int accountId;
	protected int instrumentId;
	protected int currentQty;

	public AbsPosition(int accountId, int instrumentId) {
		this.instrumentId = instrumentId;
	}

	@Override
	public final int accountId() {
		return accountId;
	}

	@Override
	public final int instrumentId() {
		return instrumentId;
	}

	@Override
	public final int currentQty() {
		return currentQty;
	}

	@Override
	public final void setCurrentQty(int qty) {
		this.currentQty = qty;
	}

	@Override
	public int compareTo(Position position) {
		return this.accountId < position.accountId() ? -1
				: this.accountId > position.accountId() ? 1
						: this.instrumentId < position.instrumentId() ? -1
								: this.instrumentId > position.instrumentId() ? 1 : 0;
	}

}
