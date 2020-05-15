package io.mercury.redstone.core.keeper;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.io.Dumper;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.util.Assertor;
import io.mercury.redstone.core.account.Account;
import io.mercury.redstone.core.account.AccountNotFoundException;
import io.mercury.redstone.core.account.SubAccount;

/**
 * 
 * 用于全局管理Account
 * 
 * @author yellow013
 *
 */
@NotThreadSafe
public final class AccountKeeper implements Dumper<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6883109944757142986L;

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(AccountKeeper.class);

	/**
	 * 存储Account信息, 一对一关系,以accountId索引
	 */
	private static final MutableIntObjectMap<Account> AccountMap = MutableMaps.newIntObjectHashMap();

	/**
	 * 存储SubAccount信息, 一对一关系,以subAccountId索引
	 */
	private static final MutableIntObjectMap<SubAccount> SubAccountMap = MutableMaps.newIntObjectHashMap();

	/**
	 * 存储Account信息, 一对一关系,以subAccountId索引
	 */
	private static final MutableIntObjectMap<Account> AccountMapBySubAccountId = MutableMaps.newIntObjectHashMap();

	private static final String KeeperName = "AccountKeeper";

	private AccountKeeper() {
	}

	public static void putAccount(@Nonnull Account... accounts) {
		Assertor.requiredLength(accounts, 1, "accounts");
		for (Account account : accounts) {
			for (SubAccount subAccount : account.subAccounts()) {
				SubAccountMap.put(subAccount.subAccountId(), subAccount);
				AccountMapBySubAccountId.put(subAccount.subAccountId(), account);
				log.info("{} :: Put account, accountId==[{}], subAccountId==[{}]", KeeperName, account.accountId(),
						subAccount.subAccountId());
			}
			AccountMap.put(account.accountId(), account);
		}
	}

	public static void putSubAccount(@Nonnull SubAccount... subAccounts) {
		Assertor.requiredLength(subAccounts, 1, "subAccounts");
		for (SubAccount subAccount : subAccounts) {
			SubAccountMap.put(subAccount.subAccountId(), subAccount);
			Account account = subAccount.account();
			AccountMapBySubAccountId.put(subAccount.subAccountId(), account);
			AccountMap.put(account.accountId(), account);
			log.info("{} :: Put subAccount, subAccountId==[{}], accountId==[{}]", KeeperName, subAccount.subAccountId(),
					account.accountId());
		}
	}

	@Nonnull
	public static Account getAccountById(int accountId) throws AccountNotFoundException {
		Account account = AccountMap.get(accountId);
		if (account == null)
			throw new AccountNotFoundException("Account not found : accountId[" + accountId + "] no mapping object");
		return account;
	}

	public static Account setAccountNotTradable(int accountId) {
		return getAccountById(accountId).disable();
	}

	public static Account setAccountTradable(int accountId) {
		return getAccountById(accountId).enable();
	}

	public static boolean isAccountTradable(int accountId) {
		return getAccountById(accountId).isEnabled();
	}

	@Nonnull
	public static Account getAccountBySubAccountId(int subAccountId) throws AccountNotFoundException {
		Account account = AccountMapBySubAccountId.get(subAccountId);
		if (account == null)
			throw new AccountNotFoundException(
					"Account not found : subAccountId[" + subAccountId + "] no mapping object");
		return account;
	}

	@Nonnull
	public static SubAccount getSubAccount(int subAccountId) throws AccountNotFoundException {
		SubAccount subAccount = SubAccountMap.get(subAccountId);
		if (subAccount == null)
			throw new AccountNotFoundException(
					"SubAccount not found : subAccountId[" + subAccountId + "] no mapping object");
		return subAccount;
	}

	public static SubAccount setSubAccountNotTradable(int subAccountId) {
		return getSubAccount(subAccountId).disable();
	}

	public static SubAccount setSubAccountTradable(int subAccountId) {
		return getSubAccount(subAccountId).enable();
	}

	public static boolean isSubAccountTradable(int subAccountId) {
		return getSubAccount(subAccountId).isEnabled();
	}

	@Override
	public String dump() {
		// TODO Auto-generated method stub
		return null;
	}

}
