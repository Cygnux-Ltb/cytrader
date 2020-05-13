package io.redstone.core.account;

import javax.annotation.Nonnull;

import io.mercury.common.fsm.EnableComponent;
import io.mercury.common.util.Assertor;
import io.mercury.common.util.StringUtil;

public class SubAccount extends EnableComponent<SubAccount> {

	private final int subAccountId;
	private final String subAccountName;
	private final Balance balance;
	private final Account account;

	public SubAccount(int subAccountId, @Nonnull Account account) {
		this(subAccountId, null, account, account.balance());
	}

	public SubAccount(int subAccountId, @Nonnull Account account, @Nonnull Balance balance) {
		this(subAccountId, null, account, account.balance());
	}

	public SubAccount(int subAccountId, String subAccountName, @Nonnull Account account) {
		this(subAccountId, subAccountName, account, account.balance());
	}

	public SubAccount(int subAccountId, String subAccountName, @Nonnull Account account, @Nonnull Balance balance) {
		this.subAccountId = subAccountId;
		this.account = Assertor.nonNull(account, "account");
		this.balance = Assertor.nonNull(balance, "balance");
		this.subAccountName = StringUtil.nonEmpty(subAccountName) ? subAccountName
				: "account[" + account.accountId() + "]-subAccount[" + subAccountId + "]";
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

	@Override
	protected SubAccount returnThis() {
		return this;
	}

	private static final String str0 = "{\"subAccountId\" : ";
	private static final String str1 = ", \"subAccountName\" : ";
	private static final String str2 = ", \"balance\" : ";
	private static final String str3 = ", \"account\" : ";
	private static final String str4 = "}";

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(90);
		builder.append(str0);
		builder.append(subAccountId);
		builder.append(str1);
		builder.append(StringUtil.toText(subAccountName));
		builder.append(str2);
		builder.append(balance);
		builder.append(str3);
		builder.append(account);
		builder.append(str4);
		return builder.toString();
	}

	public static void main(String[] args) {
		SubAccount subAccount = new SubAccount(10, new Account(1, "2005"), new Balance(100000).setCredit(10000));
		System.out.println(subAccount);
	}

}
