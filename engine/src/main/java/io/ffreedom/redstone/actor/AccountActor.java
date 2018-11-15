package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;

import io.ffreedom.common.collect.EclipseCollections;
import io.ffreedom.redstone.core.account.Account;
import io.ffreedom.redstone.core.account.SubAccount;

public final class AccountActor {

	// Map<subAccountId, Account>
	private MutableIntObjectMap<Account> accountMap = EclipseCollections.newIntObjectHashMap();

	// Map<accountId, List<subAccount>>
	private MutableListMultimap<Integer, SubAccount> subAccountMap = EclipseCollections.newFastListMultimap();

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

	public final static MutableList<SubAccount> getSubAccounts(int accountId) {
		return INSTANCE.getSubAccounts0(accountId);
	}

	private MutableList<SubAccount> getSubAccounts0(int accountId) {
		return subAccountMap.get(accountId);
	}

}
