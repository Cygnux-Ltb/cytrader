package io.redstone.core.account;

public final class Balance {

	private long margin = 0L;
	private long credit = 0L;

	Balance() {
	}

	public long margin() {
		return margin;
	}

	public void setMargin(long margin) {
		this.margin = margin;
	}

	public long credit() {
		return credit;
	}

	public void setCredit(long credit) {
		this.credit = credit;
	}

}
