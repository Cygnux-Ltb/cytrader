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
	private Balance balance;

	// 备用,数组下标,用于快速访问本账户对应的仓位信息集合
	// private int positionManagerIndex;

	/**
	 * 子账户集合
	 */
	private MutableSet<SubAccount> subAccounts = MutableSets.newUnifiedSet();

	public Account(int accountId, String investorId) {
		this(accountId, investorId, new Balance());
	}

	public Account(int accountId, String investorId, Balance balance) {
		this(accountId, null, investorId, balance);
	}

	public Account(int accountId, String accountName, String investorId) {
		this(accountId, accountName, investorId, new Balance());
	}

	public Account(int accountId, String accountName, String investorId, Balance balance) {
		super();
		this.accountId = accountId;
		String suffixName = "investorId[" + investorId + "]";
		this.accountName = accountName == null ? suffixName : accountName + "-" + suffixName;
		this.accountName = accountName;
		this.investorId = investorId;
		this.balance = balance;
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

	public Balance balance() {
		return balance;
	}

	public int subAccountCount() {
		return subAccounts.size();
	}

	public void addSubAccount(SubAccount subAccount) {
		subAccounts.add(subAccount);
	}

}
