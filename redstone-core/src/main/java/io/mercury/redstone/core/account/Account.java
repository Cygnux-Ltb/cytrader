package io.mercury.redstone.core.account;

import static io.mercury.common.util.StringUtil.toText;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.set.MutableSet;

import io.mercury.common.collections.MutableSets;
import io.mercury.common.fsm.EnableComponent;
import io.mercury.common.util.Assertor;
import io.mercury.common.util.StringUtil;

public final class Account extends EnableComponent<Account> implements Comparable<Account> {

	/**
	 * 账户ID
	 */
	private final int accountId;

	/**
	 * 经纪商-名称
	 */
	private final String brokerName;

	/**
	 * 经纪商-投资者ID
	 */
	private final String investorId;

	/**
	 * 账户余额
	 */
	private int balance;

	/**
	 * 信用额度
	 */
	private int credit;

	/**
	 * 备注名
	 */
	private String remarkName;

	// 备用, 数组下标, 用于快速访问本账户对应的仓位信息集合
	// private int positionManagerIndex;

	/**
	 * 子账户集合
	 */
	private final MutableSet<SubAccount> subAccounts = MutableSets.newUnifiedSet();

	public Account(int accountId, @Nonnull String brokerName, @Nonnull String investorId) {
		this(accountId,  brokerName, investorId, 0, 0);
	}

	public Account(int accountId, @Nonnull String brokerName, @Nonnull String investorId,
			int balance, int credit) {
		Assertor.nonEmpty(brokerName, "brokerName");
		Assertor.nonEmpty(investorId, "investorId");
		this.accountId = accountId;
		this.brokerName = brokerName;
		this.investorId = investorId;
		this.balance = balance;
		this.credit = credit;
	}

	public int accountId() {
		return accountId;
	}


	public String brokerName() {
		return brokerName;
	}

	public String investorId() {
		return investorId;
	}

	public MutableSet<SubAccount> subAccounts() {
		return subAccounts;
	}

	public Account setBalance(int balance) {
		this.balance = balance;
		return this;
	}

	public int balance() {
		return balance;
	}

	public Account setCredit(int credit) {
		this.credit = credit;
		return this;
	}

	public int credit() {
		return credit;
	}

	public String remarkName() {
		return remarkName;
	}

	public Account setRemarkName(String remarkName) {
		this.remarkName = remarkName;
		return this;
	}

	public int subAccountCount() {
		return subAccounts.size();
	}

	/**
	 * 仅提供给SubAccount调用
	 * 
	 * @param subAccount
	 */
	Account addSubAccount(SubAccount subAccount) {
		subAccounts.add(subAccount);
		return this;
	}

	@Override
	protected Account returnThis() {
		return this;
	}

	public final static class AccountException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6421678546942382394L;

		public AccountException(String message) {
			super(message);
		}

	}

	private static final String str0 = "{\"accountId\" : ";
	private static final String str1 = ", \"accountName\" : ";
	private static final String str2 = ", \"brokerName\" : ";
	private static final String str3 = ", \"investorId\" : ";
	private static final String str4 = ", \"balance\" : ";
	private static final String str5 = ", \"credit\" : ";
	private static final String str6 = ", \"subAccountsCount\" : ";
	private static final String str7 = ", \"isEnabled\" : ";
	private static final String str8 = "}";

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(200);
		builder.append(str0);
		builder.append(accountId);
		builder.append(str1);
		builder.append(str2);
		builder.append(toText(brokerName));
		builder.append(str3);
		builder.append(investorId);
		builder.append(str4);
		builder.append(balance);
		builder.append(str5);
		builder.append(credit);
		builder.append(str6);
		builder.append(subAccounts.size());
		builder.append(str7);
		builder.append(isEnabled());
		builder.append(str8);
		return builder.toString();
	}

	@Override
	public int compareTo(Account o) {
		return this.accountId < o.accountId ? -1 : this.accountId > o.accountId ? 1 : 0;
	}

	public static void main(String[] args) {
		System.out.println(StringUtil.toText(null));
		System.out.println(new Account(1,  "ZSQH", "200500"));
	}

}
