package io.mercury.redstone.core.account;

import static io.mercury.common.util.StringUtil.toText;

import javax.annotation.Nonnull;

import io.mercury.common.fsm.EnableComponent;
import io.mercury.common.util.Assertor;

public final class SubAccount extends EnableComponent<SubAccount> implements Comparable<SubAccount> {

	public static final int MaxSubAccountId = 899;

	public static final int ExternalSubAccountId = 900;

	/**
	 * 子账户ID
	 */
	private final int subAccountId;

	/**
	 * 子账户名称
	 */
	private final String subAccountName;

	/**
	 * 多属账户
	 */
	private final Account account;

	/**
	 * 账户余额
	 */
	private int balance;
	/**
	 * 信用额度
	 */
	private int credit;

	public SubAccount(int subAccountId, @Nonnull Account account) {
		this(subAccountId, account, account.balance(), account.credit());
	}

	public SubAccount(int subAccountId, @Nonnull Account account, int balance, int credit) {
		this.subAccountId = subAccountId;
		this.account = Assertor.nonNull(account, "account");
		this.balance = balance;
		this.credit = credit;
		this.subAccountName = "SA-Account[" + account.accountName() + "]-SubAccount[" + subAccountId + "]";
		account.addSubAccount(this);
	}

	public int subAccountId() {
		return subAccountId;
	}

	public String subAccountName() {
		return subAccountName;
	}

	public int balance() {
		return balance;
	}

	public int credit() {
		return credit;
	}

	public Account account() {
		return account;
	}

	@Override
	protected SubAccount returnThis() {
		return this;
	}

	public static class SubAccountException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8903289183998546839L;

		public SubAccountException(String message) {
			super(message);
		}

	}

	private static final String str0 = "{\"subAccountId\" : ";
	private static final String str1 = ", \"subAccountName\" : ";
	private static final String str2 = ", \"account\" : ";
	private static final String str3 = ", \"balance\" : ";
	private static final String str4 = ", \"credit\" : ";
	private static final String str5 = ", \"isEnabled\" : ";
	private static final String str6 = "}";

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(300);
		builder.append(str0);
		builder.append(subAccountId);
		builder.append(str1);
		builder.append(toText(subAccountName));
		builder.append(str2);
		builder.append(account);
		builder.append(str3);
		builder.append(balance);
		builder.append(str4);
		builder.append(credit);
		builder.append(str5);
		builder.append(isEnabled());
		builder.append(str6);
		return builder.toString();
	}

	@Override
	public int compareTo(SubAccount o) {
		return this.subAccountId < o.subAccountId ? -1 : this.subAccountId > o.subAccountId ? 1 : 0;
	}

	public static void main(String[] args) {
		SubAccount subAccount = new SubAccount(10, new Account(1, "Test-A", "HYQH", "200500", 100000, 0));
		System.out.println(subAccount);
	}

}
