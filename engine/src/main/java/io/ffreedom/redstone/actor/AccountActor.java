package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.ffreedom.common.collect.MutableMaps;
import io.ffreedom.redstone.core.account.Account;

public final class AccountActor {

	// Map<subAccountId, Account>
	private MutableIntObjectMap<Account> subAccountMap = MutableMaps.newIntObjectHashMap();

	// Map<accountId, Account>
	private MutableIntObjectMap<Account> accountMap = MutableMaps.newIntObjectHashMap();

	public final static AccountActor Singleton = new AccountActor();

	private AccountActor() {

	}
	
	public void putAccount() {
		
	}

}
