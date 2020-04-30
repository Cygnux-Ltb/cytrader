package io.redstone.core.account;

public final class Balance {

	private int margin = 0;
	private int credit = 0;

	Balance() {
	}

	public long margin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	public long credit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

}