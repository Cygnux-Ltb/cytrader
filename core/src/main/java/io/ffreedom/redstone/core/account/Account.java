package io.ffreedom.redstone.core.account;

import org.eclipse.collections.api.set.MutableSet;

import io.ffreedom.common.collect.MutableSets;
import io.ffreedom.common.fsm.EnableComponent;
import io.ffreedom.redstone.core.balance.Balance;

public class Account extends EnableComponent {

	private int accountId;
	private String accountName;
	private String investorId;
	private Broker broker;
	private Balance balance;
	/**
	 * 备用,数组下标,用于快速访问本账户对于的仓位信息集合
	 */
	// private int positionManagerIndex;
	private MutableSet<SubAccount> subAccounts = MutableSets.newUnifiedSet();

	public Account(int accountId, String accountName, String investorId, Broker broker) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.investorId = investorId;
		this.broker = broker;
		this.balance = Balance.create(accountId);
	}

	public int getAccountId() {
		return accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getInvestorId() {
		return investorId;
	}

	public Broker getBroker() {
		return broker;
	}

	public MutableSet<SubAccount> getSubAccounts() {
		return subAccounts;
	}

	public Balance getBalance() {
		return balance;
	}

	public int getSubAccountCount() {
		return subAccounts.size();
	}

	public void addSubAccount(SubAccount subAccount) {
		subAccounts.add(subAccount);
	}

}
