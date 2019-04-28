package io.ffreedom.redstone.core.account;

import io.ffreedom.common.fsm.EnableController;

public class SubAccount extends EnableController {

	private int subAccountId;
	private Account account;

	public SubAccount(int subAccountId, Account account) {
		super();
		this.subAccountId = subAccountId;
		this.account = account;
	}

	public int getSubAccountId() {
		return subAccountId;
	}

	public Account getAccount() {
		return account;
	}

}
