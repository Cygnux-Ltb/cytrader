package io.mercury.redstone.core.adaptor;

import io.mercury.redstone.core.adaptor.Adaptor.AdaptorStatus;

public final class AdaptorEvent {

	/**
	 * adaptorId
	 */
	private int adaptorId;

	/**
	 * adaptorStatus
	 */
	private AdaptorStatus adaptorStatus;

	public AdaptorEvent(int adaptorId, AdaptorStatus adaptorStatus) {
		this.adaptorId = adaptorId;
		this.adaptorStatus = adaptorStatus;
	}

	public int adaptorId() {
		return adaptorId;
	}

	public AdaptorStatus adaptorStatus() {
		return adaptorStatus;
	}

}
