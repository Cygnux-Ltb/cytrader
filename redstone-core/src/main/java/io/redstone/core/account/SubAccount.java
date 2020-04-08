package io.redstone.core.account;

import javax.annotation.Nonnull;

import io.mercury.common.fsm.EnableComponent;
import io.mercury.common.util.Assertor;

public class SubAccount extends EnableComponent {

	private final int subAccountId;
	private final String subAccountName;
	private final Balance balance;
	private final Account account;

	public SubAccount(int subAccountId, @Nonnull Account account) {
		this(subAccountId, null, account, account.balance());
	}

	public SubAccount(int subAccountId, String subAccountName, @Nonnull Account account) {
		this(subAccountId, subAccountName, account, account.balance());
	}

	public SubAccount(int subAccountId, String subAccountName, @Nonnull Account account,
			Balance balance) {
		this.subAccountId = subAccountId;
		this.account = Assertor.nonNull(account, "[account]");
		this.balance = Assertor.nonNull(balance, "[balance]");
		String attachName = "account[" + account.accountName() + "]-subAccount[" + subAccountId + "]";
		this.subAccountName = subAccountName == null ? attachName : subAccountName + "-" + attachName;
		account.addSubAccount(this);
	}

	public int subAccountId() {
		return subAccountId;
	}

	public String subAccountName() {
		return subAccountName;
	}

	public Balance balance() {
		return balance;
	}

	public Account account() {
		return account;
	}

}
