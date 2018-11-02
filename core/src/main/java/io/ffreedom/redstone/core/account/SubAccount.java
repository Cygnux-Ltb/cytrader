package io.ffreedom.redstone.core.account;

public class SubAccount {

	private int subAccountId;
	private Account account;

	public SubAccount(Account account, int subAccountId) {
		super();
		this.account = account;
		this.subAccountId = subAccountId;
	}

	public Account getAccount() {
		return account;
	}

	public int getSubAccountId() {
		return subAccountId;
	}

}
