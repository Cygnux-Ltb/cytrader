package io.mercury.redstone.core.adaptor;

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

	/**
	 * 
	 * @author yellow013
	 *
	 */
	public static enum AdaptorStatus {
		MdEnable, MdDisable, TraderEnable, TraderDisable
	}

}
