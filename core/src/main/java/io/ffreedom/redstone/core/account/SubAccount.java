package io.ffreedom.redstone.core.account;

public class SubAccount extends AbstractAccount {

	private int subAccountId;
	private Account account;

	public SubAccount(Account account, int subAccountId) {
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
