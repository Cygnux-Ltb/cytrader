package io.mercury.redstone.core.adaptor;

import static io.mercury.common.collections.MutableLists.newFastList;

import java.util.List;

import javax.annotation.Nonnull;

import io.mercury.common.fsm.EnableComponent;
import io.mercury.common.util.Assertor;
import io.mercury.financial.instrument.InstrumentManager;
import io.mercury.financial.market.api.MarketData;
import io.mercury.redstone.core.EventScheduler;
import io.mercury.redstone.core.account.Account;
import io.mercury.redstone.core.account.AccountKeeper;

public abstract class AdaptorBaseImpl<M extends MarketData> extends EnableComponent<Adaptor<M>> implements Adaptor<M> {

	private final int adaptorId;
	private final String adaptorName;
	private final Account account;
	private final List<Account> accounts;

	protected final EventScheduler<M> scheduler;

	protected AdaptorBaseImpl(int adaptorId, @Nonnull String adaptorName, @Nonnull EventScheduler<M> scheduler,
			@Nonnull Account... accounts) {
		Assertor.requiredLength(accounts, 1, "accounts");
		this.adaptorId = adaptorId;
		this.adaptorName = adaptorName;
		this.scheduler = scheduler;
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
	protected Adaptor<M> returnThis() {
		return this;
	}

	@Override
	public boolean startup() throws IllegalStateException {
		if (!AccountKeeper.isInitialized())
			throw new IllegalStateException("Account Keeper uninitialized");
		if (!InstrumentManager.isInitialized())
			throw new IllegalStateException("Instrument Manager uninitialized");
		return startup0();
	}

	protected abstract boolean startup0();

}
