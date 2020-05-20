package io.mercury.redstone.core.account;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.set.MutableSet;

import io.mercury.common.collections.MutableSets;
import io.mercury.common.fsm.EnableComponent;
import io.mercury.common.util.Assertor;
import io.mercury.common.util.StringUtil;

public class Account extends EnableComponent<Account> {

	private final int accountId;
	private final String accountName;

	/**
	 * 真实的经纪商ID
	 */
	private final String investorId;

	/**
	 * 账户余额
	 */
	private int balance;

	/**
	 * 可用信用额
	 */
	private int credit;

	// TODO 备用, 数组下标, 用于快速访问本账户对应的仓位信息集合
	// private int positionManagerIndex;

	/**
	 * 子账户集合
	 */
	private final MutableSet<SubAccount> subAccounts = MutableSets.newUnifiedSet();

	public Account(int accountId, @Nonnull String investorId) {
		this(accountId, null, investorId, 0, 0);
	}

	public Account(int accountId, String accountName, @Nonnull String investorId) {
		this(accountId, accountName, investorId, 0, 0);
	}

	public Account(int accountId, @Nonnull String investorId, int balance, int credit) {
		this(accountId, null, investorId, balance, credit);
	}

	public Account(int accountId, String accountName, @Nonnull String investorId, int balance, int credit) {
		this.accountId = accountId;
		this.investorId = Assertor.nonEmpty(investorId, "investorId");
		this.balance = balance;
		this.credit = credit;
		this.accountName = StringUtil.nonEmpty(accountName) ? accountName : "use-investorId[" + investorId + "]";
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

	public final static class AccountNotFoundException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6421678546942382394L;

		public AccountNotFoundException(String message) {
			super(message);
		}

	}

	private static final String str0 = "{\"accountId\" : ";
	private static final String str1 = ", \"accountName\" : ";
	private static final String str2 = ", \"investorId\" : ";
	private static final String str3 = ", \"balance\" : ";
	private static final String str4 = ", \"credit\" : ";
	private static final String str5 = ", \"subAccountsCount\" : ";
	private static final String str6 = ", \"isEnabled\" : ";
	private static final String str7 = "}";

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
		builder.append(credit);
		builder.append(str5);
		builder.append(subAccounts.size());
		builder.append(str6);
		builder.append(isEnabled());
		builder.append(str7);

		return builder.toString();
	}

	public static void main(String[] args) {
		System.out.println(StringUtil.toText(null));
		System.out.println(new Account(1, "2005"));
	}

}
