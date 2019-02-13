package io.ffreedom.redstone.keeper;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.map.primitive.MutableIntIntMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.set.MutableSet;

import io.ffreedom.common.collect.ECollections;
import io.ffreedom.redstone.core.account.Account;
import io.ffreedom.redstone.core.account.SubAccount;

@NotThreadSafe
public final class AccountKeeper {

	// 存储account信息,一对一关系,以subAccountId索引
	private MutableIntObjectMap<Account> accountMap = ECollections.newIntObjectHashMap();

	// 存储subAccount信息,一对多关系,以accountId索引
	private MutableIntObjectMap<MutableSet<SubAccount>> subAccountMap = ECollections.newIntObjectHashMap();

	// 存储accountId信息,一对一关系,以subAccountId索引
	private MutableIntIntMap accountIdMap = ECollections.newIntIntHashMap();

	private final static AccountKeeper INSTANCE = new AccountKeeper();

	private AccountKeeper() {
	}

	public static void put(SubAccount... subAccounts) {
		if (subAccounts == null)
			return;
		for (int i = 0; i < subAccounts.length; i++) {
			SubAccount subAccount = subAccounts[i];
			Account account = subAccount.getAccount();
			int subAccountId = subAccount.getSubAccountId();
			int accountId = account.getAccountId();
			INSTANCE.accountMap.put(subAccountId, account);
			INSTANCE.accountIdMap.put(subAccountId, accountId);
			getSubAccounts(accountId).add(subAccount);
		}
	}

	public static int getAccountId(int subAccountId) {
		int accountId = INSTANCE.accountIdMap.get(subAccountId);
		return accountId > 0 ? accountId : -1;
	}

	public static Account getAccount(int subAccountId) {
		Account account = INSTANCE.accountMap.get(subAccountId);
		return account != null ? account : Account.EMPTY;
	}

	public static MutableSet<SubAccount> getSubAccounts(int accountId) {
		MutableSet<SubAccount> subAccountSet = INSTANCE.subAccountMap.get(accountId);
		if (subAccountSet == null) {
			subAccountSet = ECollections.newUnifiedSet();
			INSTANCE.subAccountMap.put(accountId, subAccountSet);
		}
		return subAccountSet;
	}

}
