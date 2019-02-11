package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.set.MutableSet;

import io.ffreedom.common.collect.ECollections;
import io.ffreedom.redstone.core.account.Account;
import io.ffreedom.redstone.core.account.SubAccount;

public final class AccountActor {

	// Map<subAccountId, Account>
	private MutableIntObjectMap<Account> accountMap = ECollections.newIntObjectHashMap();

	// Map<accountId, List<subAccount>>
	private MutableIntObjectMap<MutableSet<SubAccount>> subAccountMap = ECollections.newIntObjectHashMap();

	private final static AccountActor INSTANCE = new AccountActor();

	private AccountActor() {
	}

	public final static Account getAccount(int subAccountId) {
		return INSTANCE.getAccount0(subAccountId);
	}

	private Account getAccount0(int subAccountId) {
		Account account = accountMap.get(subAccountId);
		return account != null ? account : Account.EMPTY;
	}

	public final static MutableSet<SubAccount> getSubAccounts(int accountId) {
		return INSTANCE.getSubAccounts0(accountId);
	}

	private MutableSet<SubAccount> getSubAccounts0(int accountId) {
		MutableSet<SubAccount> subAccountSet = subAccountMap.get(accountId);
		if (subAccountSet == null) {
			subAccountSet = ECollections.newUnifiedSet();
			subAccountMap.put(accountId, subAccountSet);
		}
		return subAccountSet;
	}

}
