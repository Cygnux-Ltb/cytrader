package io.redstone.core.adaptor.base;

import static io.mercury.common.util.StringUtil.isNullOrEmpty;

import io.mercury.common.fsm.EnableComponent;
import io.redstone.core.adaptor.Adaptor;

public abstract class AdaptorBaseImpl extends EnableComponent implements Adaptor {

	private int adaptorId;
	private String adaptorName;

	public AdaptorBaseImpl(int adaptorId, String adaptorName) {
		this.adaptorId = adaptorId;
		this.adaptorName = isNullOrEmpty(adaptorName) ? "Adaptor-Id-" + adaptorId : adaptorName;
	}

	@Override
	public int adaptorId() {
		return adaptorId;
	}

	@Override
	public String adaptorName() {
		return adaptorName;
	}

}
