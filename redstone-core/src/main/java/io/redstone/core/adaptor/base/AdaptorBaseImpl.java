package io.redstone.core.adaptor.base;

import static io.mercury.common.util.StringUtil.isNullOrEmpty;

import javax.annotation.Nonnull;

import io.mercury.common.fsm.EnableComponent;
import io.mercury.common.util.Assertor;
import io.redstone.core.account.Account;
import io.redstone.core.adaptor.Adaptor;

public abstract class AdaptorBaseImpl extends EnableComponent implements Adaptor {

	private int adaptorId;
	private Account account;

	private String adaptorName;

	public AdaptorBaseImpl(int adaptorId, String adaptorName, @Nonnull Account account) {
		this.adaptorId = adaptorId;
		this.account = Assertor.nonNull(account, "account");
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
	public Account account() {
		return account;
	}

}
