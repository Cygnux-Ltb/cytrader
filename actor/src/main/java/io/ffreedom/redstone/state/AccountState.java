package io.ffreedom.redstone.state;

import java.util.List;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;

import io.ffreedom.redstone.core.account.Account;
import io.ffreedom.redstone.core.account.SubAccount;

public final class AccountState {

	// Map<subAccountId, Account>
	private MutableIntObjectMap<Account> accountMap = IntObjectHashMap.newMap();

	private MutableListMultimap<Integer, SubAccount> subAccountMap = FastListMultimap.newMultimap();

	private final static AccountState INSTANCE = new AccountState();

	private AccountState() {
	}

	public final static Account getAccount(int subAccountId) {
		return INSTANCE.getAccount0(subAccountId);
	}

	private Account getAccount0(int subAccountId) {
		return accountMap.containsKey(subAccountId) ? accountMap.get(subAccountId) : Account.EMPTY;
	}

	public final static List<SubAccount> getSubAccounts(int accountId) {
		return INSTANCE.getSubAccounts0(accountId);
	}

	private List<SubAccount> getSubAccounts0(int accountId) {
		return subAccountMap.get(accountId);
	}

}
