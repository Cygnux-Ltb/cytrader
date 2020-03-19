package io.redstone.core.account;

public final class Balance {

	private long margin = 0L;
	private long credit = 0L;

	Balance() {
	}

	public final long margin() {
		return margin;
	}

	public final void setMargin(long margin) {
		this.margin = margin;
	}

	public final long credit() {
		return credit;
	}

	public final void setCredit(long credit) {
		this.credit = credit;
	}

}
