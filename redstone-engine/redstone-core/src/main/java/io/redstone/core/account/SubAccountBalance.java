package io.redstone.core.account;

public final class SubAccountBalance extends Balance {

	private int subAccountId;

	private SubAccountBalance(int subAccountId) {
		this.subAccountId = subAccountId;
	}

	public final static SubAccountBalance newInstance(int subAccountId) {
		return new SubAccountBalance(subAccountId);
	}

	public int subAccountId() {
		return subAccountId;
	}

}
