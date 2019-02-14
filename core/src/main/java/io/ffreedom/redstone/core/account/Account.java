package io.ffreedom.redstone.core.account;

import io.ffreedom.common.fsm.Enable;
import io.ffreedom.redstone.core.balance.Balance;

public class Account implements Enable {

	private int accountId;
	private AccountInfo accountInfo;
	private Balance balance;
	private boolean isEnable = false;

	public final static Account EMPTY = new Account(-1, null, Balance.EMPTY);

	public Account(int accountId, AccountInfo accountInfo, Balance balance) {
		super();
		this.accountId = accountId;
		this.accountInfo = accountInfo;
		this.balance = balance;
	}

	@Override
	public boolean enabled() {
		return isEnable;
	}

	@Override
	public void setEnable(boolean enable) {
		if (enable)
			this.isEnable = true;
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
