package io.redstone.engine.actor;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.mercury.common.collections.MutableMaps;
import io.redstone.core.account.Account;
import io.redstone.core.account.SubAccount;

public final class AccountActor {

	// Map<subAccountId, Account>
	private MutableIntObjectMap<Account> subAccountIdMap = MutableMaps.newIntObjectHashMap();

	// Map<accountId, Account>
	private MutableIntObjectMap<Account> accountIdMap = MutableMaps.newIntObjectHashMap();

	public final static AccountActor Singleton = new AccountActor();

	private AccountActor() {
	}

	public void putAccount(Account... accounts) {
		for (Account account : accounts) {
			for (SubAccount subAccount : account.subAccounts())
				subAccountIdMap.put(subAccount.subAccountId(), account);
			accountIdMap.put(account.accountId(), account);
		}
	}

	public Account getAccountBySubAccountId(int subAccountId) {
		return subAccountIdMap.get(subAccountId);
	}

	public Account getAccountByAccountId(int accountId) {
		return accountIdMap.get(accountId);
	}

}
