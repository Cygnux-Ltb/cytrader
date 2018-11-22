package io.ffreedom.redstone.core.account;

import io.ffreedom.redstone.core.balance.Balance;

public class Account {

	private int accountId;
	private AccountInfo accountInfo;
	private Balance balance;

	public final static Account EMPTY = new Account(-1, null, Balance.EMPTY);

	public Account(int accountId, AccountInfo accountInfo, Balance balance) {
		super();
		this.accountId = accountId;
		this.accountInfo = accountInfo;
		this.balance = balance;
	}

	public int getAccountId() {
		return accountId;
	}

	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	public Balance getBalance() {
		return balance;
	}

}
