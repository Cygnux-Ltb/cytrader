package io.ffreedom.redstone.core.account;

import io.ffreedom.common.fsm.EnableComponent;

public class SubAccount extends EnableComponent {

	private int subAccountId;
	private String subAccountName;
	private Account account;

	public SubAccount(int subAccountId, Account account) {
		this(subAccountId, null, account);
	}

	public SubAccount(int subAccountId, String subAccountName, Account account) {
		if (account == null)
			throw new IllegalArgumentException("");
		this.subAccountId = subAccountId;
		if (subAccountName == null)
			this.subAccountName = "subAccount[" + subAccountId + "]-account[" + account.getAccountId() + "]";
		else
			this.subAccountName = subAccountName;
		account.addSubAccount(this);
	}

	public int getSubAccountId() {
		return subAccountId;
	}

	public String getSubAccountName() {
		return subAccountName;
	}

	public Account getAccount() {
		return account;
	}

}
