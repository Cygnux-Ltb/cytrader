package io.redstone.core.account;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.map.primitive.MutableIntBooleanMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntBooleanHashMap;

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

	// 存储account的交易状态,以accountId索引
	private MutableIntBooleanMap accountTradableMap = new IntBooleanHashMap();

	// 存储subAccount的交易状态,以subAccountId索引
	private MutableIntBooleanMap subAccountTradableMap = new IntBooleanHashMap();

	// 存储InvestorAccount信息,一对一关系,以accountId索引
	private MutableIntObjectMap<InvestorAccount> accountMap = MutableMaps.newIntObjectHashMap();

	// 存储SubAccount信息,一对一关系,以subAccountId索引
	private MutableIntObjectMap<SubAccount> subAccountMap = MutableMaps.newIntObjectHashMap();

	// 存储InvestorAccount信息,一对一关系,以subAccountId索引
	private MutableIntObjectMap<InvestorAccount> accountMapBySubAccountId = MutableMaps.newIntObjectHashMap();

	private final static AccountKeeper InnerInstance = new AccountKeeper();

	private AccountKeeper() {
	}

	public static void putAccount(@Nonnull InvestorAccount... accounts) {
		Assertor.requiredLength(accounts, 1, "accounts");
		for (InvestorAccount account : accounts) {
			for (SubAccount subAccount : account.subAccounts()) {
				InnerInstance.subAccountMap.put(subAccount.subAccountId(), subAccount);
				InnerInstance.accountMapBySubAccountId.put(subAccount.subAccountId(), account);
			}
			InnerInstance.accountMap.put(account.accountId(), account);
		}
	}

	public static void putAccount(@Nonnull SubAccount... subAccounts) {
		Assertor.requiredLength(subAccounts, 1, "subAccounts");
		for (SubAccount subAccount : subAccounts) {
			InnerInstance.subAccountMap.put(subAccount.subAccountId(), subAccount);
			InvestorAccount investorAccount = subAccount.investorAccount();
			InnerInstance.accountMapBySubAccountId.put(subAccount.subAccountId(), investorAccount);
			InnerInstance.accountMap.put(investorAccount.accountId(), investorAccount);
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

	@CheckForNull
	public static InvestorAccount getInvestorAccountById(int accountId) {
		return InnerInstance.accountMap.get(accountId);
	}

	@CheckForNull
	public static InvestorAccount getInvestorAccountBySubAccountId(int subAccountId) {
		return InnerInstance.accountMapBySubAccountId.get(subAccountId);
	}

	@CheckForNull
	public static SubAccount getSubAccount(int subAccountId) {
		return InnerInstance.subAccountMap.get(subAccountId);
	}

}
