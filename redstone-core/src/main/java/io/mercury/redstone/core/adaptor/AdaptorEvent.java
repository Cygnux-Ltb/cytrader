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
	private AdaptorStatus status;

	public AdaptorEvent(int adaptorId, AdaptorStatus status) {
		this.adaptorId = adaptorId;
		this.status = status;
	}

	public int adaptorId() {
		return adaptorId;
	}

	public AdaptorStatus status() {
		return status;
	}

}
