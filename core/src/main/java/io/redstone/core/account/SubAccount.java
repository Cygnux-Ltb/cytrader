package io.redstone.core.account;

import io.mercury.common.fsm.EnableComponent;

public class SubAccount extends EnableComponent {

	private int subAccountId;
	private String subAccountName;
	private SubAccountBalance subAccountBalance;
	private Account account;

	public SubAccount(int subAccountId, Account account) {
		this(subAccountId, null, account);
	}

	public SubAccount(int subAccountId, String subAccountName, Account account) {
		if (account == null)
			throw new IllegalArgumentException("Param:[account] can not be null");
		this.account = account;
		this.subAccountId = subAccountId;
		String attachName = "account[" + account.getAccountId() + "]-subAccount[" + subAccountId + "]";
		if (subAccountName == null)
			this.subAccountName = attachName;
		else
			this.subAccountName = subAccountName + "-" + attachName;
		account.addSubAccount(this);
	}

	public SubAccount(int subAccountId, String subAccountName, SubAccountBalance subAccountBalance, Account account) {
		if (account == null)
			throw new IllegalArgumentException("Param:[account] can not be null");
		this.account = account;
		this.subAccountId = subAccountId;
		if (subAccountBalance == null) {
			AccountBalance accountBalance = account.getAccountBalance();
			this.subAccountBalance = SubAccountBalance.newInstance(subAccountId);
			this.subAccountBalance.setAvailableMargin(accountBalance.getAvailableMargin());
			this.subAccountBalance.setAvailableCredit(accountBalance.getAvailableCredit());
		} else
			this.subAccountBalance = subAccountBalance;
		String attachName = "account[" + account.getAccountId() + "]-subAccount[" + subAccountId + "]";
		if (subAccountName == null)
			this.subAccountName = attachName;
		else
			this.subAccountName = subAccountName + "-" + attachName;
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
