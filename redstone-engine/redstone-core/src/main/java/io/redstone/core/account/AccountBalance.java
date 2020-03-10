package io.redstone.core.account;

public final class AccountBalance extends Balance {

	private int accountId;

	private AccountBalance(int accountId) {
		this.accountId = accountId;
	}

	public final static AccountBalance newInstance(int accountId) {
		return new AccountBalance(accountId);
	}

	public int accountId() {
		return accountId;
	}

}
