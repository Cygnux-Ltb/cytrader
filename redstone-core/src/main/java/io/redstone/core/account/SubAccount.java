package io.redstone.core.account;

import javax.annotation.Nonnull;

import io.mercury.common.fsm.EnableComponent;
import io.mercury.common.util.Assertor;

public class SubAccount extends EnableComponent {

	private final int subAccountId;
	private final String subAccountName;
	private final Balance balance;
	private final InvestorAccount investorAccount;

	public SubAccount(int subAccountId, @Nonnull InvestorAccount investorAccount) {
		this(subAccountId, null, investorAccount);
	}

	public SubAccount(int subAccountId, String subAccountName, @Nonnull InvestorAccount investorAccount) {
		this(subAccountId, subAccountName, investorAccount, investorAccount.balance());
	}

	public SubAccount(int subAccountId, String subAccountName, @Nonnull InvestorAccount investorAccount,
			Balance balance) {
		this.subAccountId = subAccountId;
		this.investorAccount = Assertor.nonNull(investorAccount, "[realAccount]");
		this.balance = Assertor.nonNull(balance, "[balance]");
		String attachName = "account[" + investorAccount.accountName() + "]-subAccountId[" + subAccountId + "]";
		this.subAccountName = subAccountName == null ? attachName : subAccountName + "-" + attachName;
		investorAccount.addSubAccount(this);
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

	public InvestorAccount investorAccount() {
		return investorAccount;
	}

}
