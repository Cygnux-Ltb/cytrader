package io.ffreedom.redstone.core.account;

import io.ffreedom.common.fsm.Enable;

public class SubAccount implements Enable {

	private int subAccountId;
	private Account account;
	private boolean isEnable = false;

	public SubAccount(Account account, int subAccountId) {
		super();
		this.account = account;
		this.subAccountId = subAccountId;
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

	public Account getAccount() {
		return account;
	}

	public int getSubAccountId() {
		return subAccountId;
	}

}
