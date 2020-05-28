package io.mercury.redstone.core.account;

import static io.mercury.common.util.StringUtil.toText;

import javax.annotation.Nonnull;

import io.mercury.common.fsm.EnableComponent;
import io.mercury.common.util.Assertor;
import io.mercury.common.util.StringUtil;

public class SubAccount extends EnableComponent<SubAccount> {

	private final int subAccountId;
	private final String subAccountName;
	private final Account account;
	/**
	 * 账户余额
	 */
	private int balance;
	/**
	 * 可用信用额
	 */
	private int credit;

	public SubAccount(int subAccountId, @Nonnull Account account) {
		this(subAccountId, null, account, account.balance(), account.credit());
	}

	public SubAccount(int subAccountId, String subAccountName, @Nonnull Account account) {
		this(subAccountId, subAccountName, account, account.balance(), account.credit());
	}

	public SubAccount(int subAccountId, String subAccountName, @Nonnull Account account, int balance, int credit) {
		this.subAccountId = subAccountId;
		this.account = Assertor.nonNull(account, "account");
		this.balance = balance != 0 ? balance : account.balance();
		this.credit = credit != 0 ? credit : account.credit();
		this.subAccountName = StringUtil.nonEmpty(subAccountName) ? subAccountName
				: "account[" + account.accountId() + "]-subAccount[" + subAccountId + "]";
		account.addSubAccount(this);
		AccountKeeper.initialize(this);
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

	private static final String str0 = "{\"subAccountId\" : ";
	private static final String str1 = ", \"subAccountName\" : ";
	private static final String str2 = ", \"account\" : ";
	private static final String str3 = ", \"balance\" : ";
	private static final String str4 = ", \"credit\" : ";
	private static final String str5 = ", \"isEnabled\" : ";
	private static final String str6 = "}";

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(90);
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

	public static void main(String[] args) {
		SubAccount subAccount = new SubAccount(10, new Account(1, "", "2005", 100000, 0));
		System.out.println(subAccount);
	}

}
