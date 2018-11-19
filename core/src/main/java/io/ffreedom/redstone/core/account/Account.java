package io.ffreedom.redstone.core.account;

public class Account {

	private int accountId;
	private AccountInfo info;
	
	
	public final static Account EMPTY = new Account(-1, null);

	public Account(int accountId, AccountInfo info) {
		super();
		this.accountId = accountId;
		this.info = info;
	}

	public int getAccountId() {
		return accountId;
	}

	public AccountInfo getInfo() {
		return info;
	}

}
