package io.ffreedom.redstone.storage;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.map.primitive.MutableIntBooleanMap;
import org.eclipse.collections.api.map.primitive.MutableIntIntMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.map.mutable.primitive.IntBooleanHashMap;

import io.ffreedom.common.collect.ECollections;
import io.ffreedom.redstone.core.account.Account;
import io.ffreedom.redstone.core.account.SubAccount;

@NotThreadSafe
public final class AccountKeeper {

	// 存储account的交易状态,以accountId索引
	private MutableIntBooleanMap accountTradableMap = new IntBooleanHashMap();

	// 存储subAccount的交易状态,以subAccountId索引
	private MutableIntBooleanMap subAccountTradableMap = new IntBooleanHashMap();

	// 存储account信息,一对一关系,以subAccountId索引
	private MutableIntObjectMap<Account> accountMap = ECollections.newIntObjectHashMap();

	// 存储subAccount信息,一对多关系,以accountId索引
	private MutableIntObjectMap<MutableSet<SubAccount>> subAccountMap = ECollections.newIntObjectHashMap();

	// 存储accountId信息,一对一关系,以subAccountId索引
	private MutableIntIntMap accountIdMap = ECollections.newIntIntHashMap();

	private final static AccountKeeper InnerInstance = new AccountKeeper();

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
			InnerInstance.accountMap.put(subAccountId, account);
			InnerInstance.accountIdMap.put(subAccountId, accountId);
			getSubAccounts(accountId).add(subAccount);
		}
	}

	public static void setAccountNotTradable(int accountId) {
		InnerInstance.accountTradableMap.put(accountId, false);
	}

	public static void setAccountTradable(int accountId) {
		InnerInstance.accountTradableMap.put(accountId, true);
	}

	public static boolean isAccountTradable(int accountId) {
		return InnerInstance.accountTradableMap.get(accountId);
	}

	public static void setSubAccountNotTradable(int subAccountId) {
		InnerInstance.subAccountTradableMap.put(subAccountId, false);
	}

	public static void setSubAccountTradable(int subAccountId) {
		InnerInstance.subAccountTradableMap.put(subAccountId, true);
	}

	public static boolean isSubAccountTradable(int subAccountId) {
		return InnerInstance.subAccountTradableMap.get(subAccountId);
	}

	public static int getAccountId(int subAccountId) {
		int accountId = InnerInstance.accountIdMap.get(subAccountId);
		return accountId > 0 ? accountId : -1;
	}

	public static Account getAccount(int subAccountId) {
		Account account = InnerInstance.accountMap.get(subAccountId);
		return account != null ? account : Account.EMPTY;
	}

	public static MutableSet<SubAccount> getSubAccounts(int accountId) {
		MutableSet<SubAccount> subAccountSet = InnerInstance.subAccountMap.get(accountId);
		if (subAccountSet == null) {
			subAccountSet = ECollections.newUnifiedSet();
			InnerInstance.subAccountMap.put(accountId, subAccountSet);
		}
		return subAccountSet;
	}

}
