package io.redstone.core.adaptor.base;

import static io.mercury.common.util.StringUtil.isNullOrEmpty;

import io.redstone.core.adaptor.api.Adaptor;

public abstract class BaseAdaptor implements Adaptor {

	private int adaptorId;
	private String adaptorName;

	public BaseAdaptor(int adaptorId, String adaptorName) {
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
