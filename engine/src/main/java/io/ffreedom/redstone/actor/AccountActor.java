package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.set.MutableSet;

import io.ffreedom.common.collect.MutableMaps;
import io.ffreedom.common.collect.MutableSets;
import io.ffreedom.redstone.core.account.Account;
import io.ffreedom.redstone.core.account.SubAccount;

public final class AccountActor {

	// Map<subAccountId, Account>
	private MutableIntObjectMap<Account> accountMap = MutableMaps.newIntObjectHashMap();

	// Map<accountId, List<subAccount>>
	private MutableIntObjectMap<MutableSet<SubAccount>> subAccountMap = MutableMaps.newIntObjectHashMap();

	public final static AccountActor Singleton = new AccountActor();

	private AccountActor() {
	}

	public Account getAccount(int subAccountId) {
		Account account = accountMap.get(subAccountId);
		return account != null ? account : Account.EMPTY;
	}

	public MutableSet<SubAccount> getSubAccounts(int accountId) {
		MutableSet<SubAccount> subAccountSet = subAccountMap.get(accountId);
		if (subAccountSet == null) {
			subAccountSet = MutableSets.newUnifiedSet();
			subAccountMap.put(accountId, subAccountSet);
		}
		return subAccountSet;
	}

}
