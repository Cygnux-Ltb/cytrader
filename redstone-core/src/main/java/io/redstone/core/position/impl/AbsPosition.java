package io.redstone.core.position.impl;

import io.redstone.core.position.api.Position;

public abstract class AbsPosition implements Position {

	protected int accountId;
	protected int instrumentId;
	protected long currentQty;

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
	public final long currentQty() {
		return currentQty;
	}

	@Override
	public final void currentQty(long qty) {
		this.currentQty = qty;
	}

	@Override
	public int compareTo(Position pos) {
		return this.accountId < pos.accountId() ? -1
				: this.accountId > pos.accountId() ? 1
						: this.instrumentId < pos.instrumentId() ? -1
								: this.instrumentId > pos.instrumentId() ? 1 : 0;
	}

}
