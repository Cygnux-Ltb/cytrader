package io.ffreedom.redstone.core.account;

import io.ffreedom.common.fsm.EnableController;
import io.ffreedom.redstone.core.balance.Balance;

public class Account extends EnableController {

	private int accountId;
	private AccountInfo accountInfo;
	private Balance balance;

	public Account(int accountId, AccountInfo accountInfo, Balance balance) {
		super();
		this.accountId = accountId;
		this.accountInfo = accountInfo;
		this.balance = balance;
	}

	public Account(int accountId, AccountInfo accountInfo) {
		this(accountId, accountInfo, Balance.EMPTY);
	}

	public Account(int accountId) {
		this(accountId, AccountInfo.EMPTY, Balance.EMPTY);
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

	public Account setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
		return this;
	}

	public Account setBalance(Balance balance) {
		this.balance = balance;
		return this;
	}

}
