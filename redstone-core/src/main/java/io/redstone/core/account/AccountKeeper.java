package io.redstone.core.account;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.util.Assertor;

/**
 * 
 * 用于全局管理Account的状态
 * 
 * @author yellow013
 *
 */
@NotThreadSafe
public final class AccountKeeper {

	// private static final Logger log =
	// CommonLoggerFactory.getLogger(AccountKeeper.class);

	// 存储Account信息, 一对一关系,以accountId索引
	private MutableIntObjectMap<Account> accountMap = MutableMaps.newIntObjectHashMap();

	// 存储SubAccount信息, 一对一关系,以subAccountId索引
	private MutableIntObjectMap<SubAccount> subAccountMap = MutableMaps.newIntObjectHashMap();

	// 存储Account信息, 一对一关系,以subAccountId索引
	private MutableIntObjectMap<Account> accountMapBySubAccountId = MutableMaps.newIntObjectHashMap();

	private final static AccountKeeper Singleton = new AccountKeeper();

	private AccountKeeper() {
	}

	public static void putAccount(@Nonnull Account... accounts) {
		Assertor.requiredLength(accounts, 1, "accounts");
		for (Account account : accounts) {
			for (SubAccount subAccount : account.subAccounts()) {
				Singleton.subAccountMap.put(subAccount.subAccountId(), subAccount);
				Singleton.accountMapBySubAccountId.put(subAccount.subAccountId(), account);
			}
			Singleton.accountMap.put(account.accountId(), account);
		}
	}

	public static void putAccount(@Nonnull SubAccount... subAccounts) {
		Assertor.requiredLength(subAccounts, 1, "subAccounts");
		for (SubAccount subAccount : subAccounts) {
			Singleton.subAccountMap.put(subAccount.subAccountId(), subAccount);
			Account account = subAccount.account();
			Singleton.accountMapBySubAccountId.put(subAccount.subAccountId(), account);
			Singleton.accountMap.put(account.accountId(), account);
		}
	}

	@Nonnull
	public static Account getAccountById(int accountId) throws AccountNotFoundException {
		Account account = Singleton.accountMap.get(accountId);
		if (account == null)
			throw new AccountNotFoundException(
					"Account not found : accountId[" + accountId + "] no mapping object");
		return account;
	}

	public static void setAccountNotTradable(int accountId) {
		getAccountById(accountId).disable();
	}

	public static void setAccountTradable(int accountId) {
		getAccountById(accountId).enable();
	}

	public static boolean isAccountTradable(int accountId) {
		return getAccountById(accountId).isEnabled();
	}

	@Nonnull
	public static Account getAccountBySubAccountId(int subAccountId) throws AccountNotFoundException {
		Account account = Singleton.accountMapBySubAccountId.get(subAccountId);
		if (account == null)
			throw new AccountNotFoundException(
					"Account not found : subAccountId[" + subAccountId + "] no mapping object");
		return account;
	}

	@Nonnull
	public static SubAccount getSubAccount(int subAccountId) throws AccountNotFoundException {
		SubAccount subAccount = Singleton.subAccountMap.get(subAccountId);
		if (subAccount == null)
			throw new AccountNotFoundException(
					"SubAccount not found : subAccountId[" + subAccountId + "] no mapping object");
		return subAccount;
	}

	public static void setSubAccountNotTradable(int subAccountId) {
		getSubAccount(subAccountId).disable();
	}

	public static void setSubAccountTradable(int subAccountId) {
		getSubAccount(subAccountId).enable();
	}

	public static boolean isSubAccountTradable(int subAccountId) {
		return getSubAccount(subAccountId).isEnabled();
	}

}
