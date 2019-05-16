package io.ffreedom.redstone.core.position.impl;

import io.ffreedom.redstone.core.position.api.Position;

public abstract class AbsPosition implements Position {

	protected int accountId;
	protected int instrumentId;
	protected long currentQty;

	public AbsPosition(int accountId, int instrumentId) {
		this.instrumentId = instrumentId;
	}

	@Override
	public final int getAccountId() {
		return accountId;
	}

	@Override
	public final int getInstrumentId() {
		return instrumentId;
	}

	@Override
	public final long getCurrentQty() {
		return currentQty;
	}

	@Override
	public final void setCurrentQty(long qty) {
		this.currentQty = qty;
	}

	@Override
	public int compareTo(Position pos) {
		return this.accountId < pos.getAccountId() ? -1
				: this.accountId > pos.getAccountId() ? 1
						: this.instrumentId < pos.getInstrumentId() ? -1
								: this.instrumentId > pos.getInstrumentId() ? 1 : 0;
	}

}
