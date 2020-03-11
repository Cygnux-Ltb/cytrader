package io.redstone.core.account;

public final class Balance {

	private long availableMargin = 0L;
	private long availableCredit = 0L;

	Balance() {
	}

	public final long availableMargin() {
		return availableMargin;
	}

	public final void availableMargin(long availableMargin) {
		this.availableMargin = availableMargin;
	}

	public final long availableCredit() {
		return availableCredit;
	}

	public final void availableCredit(long availableCredit) {
		this.availableCredit = availableCredit;
	}

}
