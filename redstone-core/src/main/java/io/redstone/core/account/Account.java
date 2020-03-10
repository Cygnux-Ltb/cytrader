package io.redstone.core.account;

import org.eclipse.collections.api.set.MutableSet;

import io.mercury.common.collections.MutableSets;
import io.mercury.common.fsm.EnableComponent;

public class Account extends EnableComponent {

	private int accountId;
	private String accountName;
	/**
	 * 真实的经纪商ID
	 */
	private String investorId;
	/**
	 * 账户余额
	 */
	private AccountBalance accountBalance;

	// 备用,数组下标,用于快速访问本账户对于的仓位信息集合
	// private int positionManagerIndex;

	/**
	 * 子账户集合
	 */
	private MutableSet<SubAccount> subAccounts = MutableSets.newUnifiedSet();

	public Account(int accountId, String investorId) {
		this(accountId, investorId, AccountBalance.newInstance(accountId));
	}

	public Account(int accountId, String investorId, AccountBalance accountBalance) {
		this(accountId, null, investorId, accountBalance);
	}

	public Account(int accountId, String accountName, String investorId) {
		this(accountId, accountName, investorId, AccountBalance.newInstance(accountId));
	}

	public Account(int accountId, String accountName, String investorId, AccountBalance accountBalance) {
		super();
		this.accountId = accountId;
		String attachName = "investor[" + investorId + "]";
		if (accountName == null)
			this.accountName = attachName;
		else
			this.accountName = accountName + "-" + attachName;
		this.accountName = accountName;
		this.investorId = investorId;
		this.accountBalance = accountBalance;
	}

	public int accountId() {
		return accountId;
	}

	public String accountName() {
		return accountName;
	}

	public String investorId() {
		return investorId;
	}

	public MutableSet<SubAccount> subAccounts() {
		return subAccounts;
	}

	public AccountBalance accountBalance() {
		return accountBalance;
	}

	public int subAccountCount() {
		return subAccounts.size();
	}

	public void addSubAccount(SubAccount subAccount) {
		subAccounts.add(subAccount);
	}

}
