package io.redstone.core.adaptor.base;

import io.redstone.core.adaptor.api.Adaptor;

public abstract class AbstractAdaptor implements Adaptor {

	private int adaptorId;
	private String adaptorName;

	public AbstractAdaptor(int adaptorId, String adaptorName) {
		this.adaptorId = adaptorId;
		this.adaptorName = adaptorName;
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
