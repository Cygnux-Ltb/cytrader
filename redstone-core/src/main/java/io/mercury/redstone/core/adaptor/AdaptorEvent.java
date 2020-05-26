package io.mercury.redstone.core.adaptor;

import io.mercury.redstone.core.adaptor.Adaptor.AdaptorStatus;

public final class AdaptorEvent {

	private int adaptorId;
	
	private AdaptorStatus adaptorStatus;

	public AdaptorEvent(int adaptorId) {
		this.adaptorId = adaptorId;
	}

	public int adaptorId() {
		return adaptorId;
	}

	public AdaptorStatus adaptorStatus() {
		return adaptorStatus;
	}

	public AdaptorEvent setAdaptorId(int adaptorId) {
		this.adaptorId = adaptorId;
		return this;
	}

	public AdaptorEvent setAdaptorStatus(AdaptorStatus adaptorStatus) {
		this.adaptorStatus = adaptorStatus;
		return this;
	}

}
