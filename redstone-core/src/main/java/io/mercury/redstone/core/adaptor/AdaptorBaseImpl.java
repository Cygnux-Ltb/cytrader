package io.mercury.redstone.core.adaptor;

import static io.mercury.common.collections.MutableLists.newFastList;

import java.util.List;

import javax.annotation.Nonnull;

import io.mercury.common.fsm.EnableComponent;
import io.mercury.common.util.Assertor;
import io.mercury.financial.instrument.InstrumentManager;
import io.mercury.redstone.core.account.Account;
import io.mercury.redstone.core.account.AccountKeeper;

public abstract class AdaptorBaseImpl extends EnableComponent<Adaptor> implements Adaptor {

	private final int adaptorId;
	private final String adaptorName;
	private final Account account;
	private final List<Account> accounts;

	protected AdaptorBaseImpl(int adaptorId, @Nonnull String adaptorName, @Nonnull Account... accounts) {
		Assertor.requiredLength(accounts, 1, "accounts");
		this.adaptorId = adaptorId;
		this.adaptorName = adaptorName;
		this.account = accounts[0];
		this.accounts = newFastList(accounts);
		AdaptorKeeper.putAdaptor(this);
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
	public Account account() {
		return account;
	}

	@Override
	public List<Account> accounts() {
		return accounts;
	}

	@Override
	protected Adaptor returnThis() {
		return this;
	}

	@Override
	public boolean startup() {
		if (!AccountKeeper.isInitialized())
			throw new IllegalStateException("Account Keeper uninitialized");
		if (!InstrumentManager.isInitialized())
			throw new IllegalStateException("Instrument Manager uninitialized");
		return startup0();
	}

	protected abstract boolean startup0();

}
