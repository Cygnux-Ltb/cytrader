package io.redstone.core.adaptor.base;

import static io.mercury.common.util.StringUtil.isNullOrEmpty;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;

import io.mercury.common.collections.ImmutableMaps;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.fsm.EnableComponent;
import io.mercury.common.util.Assertor;
import io.redstone.core.account.Account;
import io.redstone.core.adaptor.Adaptor;

public abstract class AdaptorBaseImpl extends EnableComponent implements Adaptor {

	private ImmutableIntObjectMap<Account> accounts;

	private int adaptorId;
	private String adaptorName;

	public AdaptorBaseImpl(int adaptorId, String adaptorName, @Nonnull Account... accounts) {
		Assertor.requiredLength(accounts, 1, "accounts");
		MutableIntObjectMap<Account> accountMap = MutableMaps.newIntObjectHashMap();
		for(Account account : accounts)
			accountMap.put(account.accountId(), account);
		this.accounts = ImmutableMaps.IntObjectMapFactory().withAll(accountMap);
		this.adaptorId = adaptorId;
		this.adaptorName = isNullOrEmpty(adaptorName) ? "Adaptor-" + adaptorId : adaptorName;
	}

	@Override
	public int adaptorId() {
		return adaptorId;
	}

	@Override
	public String adaptorName() {
		return adaptorName;
	}

	@Override
	public ImmutableIntObjectMap<Account> accounts() {
		return accounts;
	}

}
