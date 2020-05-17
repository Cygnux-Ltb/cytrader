package io.mercury.redstone.core.account;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.set.MutableSet;

import io.mercury.common.collections.MutableSets;
import io.mercury.common.fsm.EnableComponent;
import io.mercury.common.util.StringUtil;

public class Account extends EnableComponent<Account> {

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

	// TODO 备用, 数组下标, 用于快速访问本账户对应的仓位信息集合
	// private int positionManagerIndex;

	/**
	 * 子账户集合
	 */
	private MutableSet<SubAccount> subAccounts = MutableSets.newUnifiedSet();

	public Account(int accountId, String investorId) {
		this(accountId, investorId, new Balance(0));
	}

	public Account(int accountId, String investorId, Balance balance) {
		this(accountId, null, investorId, balance);
	}

	public Account(int accountId, String accountName, String investorId) {
		this(accountId, accountName, investorId, new Balance(0));
	}

	public Account(int accountId, String accountName, @Nonnull String investorId, @Nonnull Balance balance) {
		this.accountId = accountId;
		this.accountName = StringUtil.nonEmpty(accountName) ? accountName : "use-investorId[" + investorId + "]";
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

	@Override
	protected Account returnThis() {
		return this;
	}

	private static final String str0 = "{\"accountId\" : ";
	private static final String str1 = ", \"accountName\" : ";
	private static final String str2 = ", \"investorId\" : ";
	private static final String str3 = ", \"balance\" : ";
	private static final String str4 = ", \"subAccountsCount\" : ";
	private static final String str5 = "}";

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(90);
		builder.append(str0);
		builder.append(accountId);
		builder.append(str1);
		builder.append(StringUtil.toText(accountName));
		builder.append(str2);
		builder.append(investorId);
		builder.append(str3);
		builder.append(balance);
		builder.append(str4);
		builder.append(subAccounts.size());
		builder.append(str5);
		return builder.toString();
	}

	public static void main(String[] args) {
		System.out.println(StringUtil.toText(null));
		System.out.println(new Account(1, "2005"));
	}

}
