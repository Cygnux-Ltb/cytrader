package io.ffreedom.redstone.core.assets;

public final class Balance {

	private int accountId;
	private double availableMargin;
	private double availableCredit;

	public final static Balance EMPTY = Balance.create(-1).setAvailableMargin(0).setAvailableCredit(0);

	private Balance(int accountId) {
		this.accountId = accountId;
	}

	public final static Balance create(int accountId) {
		return new Balance(accountId);
	}

	public int getAccountId() {
		return accountId;
	}

	public double getAvailableMargin() {
		return availableMargin;
	}

	public Balance setAvailableMargin(double availableMargin) {
		this.availableMargin = availableMargin;
		return this;
	}

	public double getAvailableCredit() {
		return availableCredit;
	}

	public Balance setAvailableCredit(double availableCredit) {
		this.availableCredit = availableCredit;
		return this;
	}

}
