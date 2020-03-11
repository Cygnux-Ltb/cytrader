package io.redstone.engine.actor;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.mercury.common.collections.MutableMaps;
import io.redstone.core.account.InvestorAccount;
import io.redstone.core.account.SubAccount;

public final class AccountActor {

	// Map<subAccountId, Account>
	private MutableIntObjectMap<InvestorAccount> subAccountIdMap = MutableMaps.newIntObjectHashMap();

	// Map<accountId, Account>
	private MutableIntObjectMap<InvestorAccount> accountIdMap = MutableMaps.newIntObjectHashMap();

	public final static AccountActor Singleton = new AccountActor();

	private AccountActor() {
	}

	public void putAccount(InvestorAccount... accounts) {
		for (InvestorAccount account : accounts) {
			for (SubAccount subAccount : account.subAccounts())
				subAccountIdMap.put(subAccount.subAccountId(), account);
			accountIdMap.put(account.accountId(), account);
		}
	}

	public InvestorAccount getAccountBySubAccountId(int subAccountId) {
		return subAccountIdMap.get(subAccountId);
	}

	public InvestorAccount getAccountByAccountId(int accountId) {
		return accountIdMap.get(accountId);
	}

}
