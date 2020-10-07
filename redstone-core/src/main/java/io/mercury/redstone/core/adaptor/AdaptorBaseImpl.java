package io.mercury.redstone.core.adaptor;

import static io.mercury.common.collections.ImmutableLists.newImmutableList;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.list.ImmutableList;

import io.mercury.common.fsm.EnableComponent;
import io.mercury.common.util.Assertor;
import io.mercury.financial.instrument.InstrumentManager;
import io.mercury.financial.market.api.MarketData;
import io.mercury.redstone.core.EventScheduler;
import io.mercury.redstone.core.account.Account;
import io.mercury.redstone.core.account.AccountKeeper;

public abstract class AdaptorBaseImpl<M extends MarketData> extends EnableComponent<Adaptor> implements Adaptor {

	private final int adaptorId;
	private final String adaptorName;
	private final ImmutableList<Account> accounts;

	protected final EventScheduler<M> scheduler;

	protected AdaptorBaseImpl(int adaptorId, @Nonnull String adaptorName, @Nonnull EventScheduler<M> scheduler,
			@Nonnull Account... accounts) {
		Assertor.requiredLength(accounts, 1, "accounts");
		this.adaptorId = adaptorId;
		this.adaptorName = adaptorName;
		this.scheduler = scheduler;
		this.accounts = newImmutableList(accounts);
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
	public ImmutableList<Account> accounts() {
		return accounts;
	}

	@Override
	protected Adaptor returnThis() {
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
