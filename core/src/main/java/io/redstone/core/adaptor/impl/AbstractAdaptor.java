package io.redstone.core.adaptor.impl;

import io.redstone.core.adaptor.api.Adaptor;

public abstract class AbstractAdaptor implements Adaptor {

	private int adaptorId;
	private String adaptorName;

	public AbstractAdaptor(int adaptorId, String adaptorName) {
		this.adaptorId = adaptorId;
		this.adaptorName = adaptorName;
	}

	@Override
	public int getAdaptorId() {
		return adaptorId;
	}

	@Override
	public String getAdaptorName() {
		return adaptorName;
	}

}
