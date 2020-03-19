package io.redstone.core.adaptor;

public final class AdaptorEvent {

	private int adaptorId;
	private AdaptorEventType type;

	public AdaptorEvent(int adaptorId, AdaptorEventType type) {
		this.adaptorId = adaptorId;
		this.type = type;
	}

	public int getAdaptorId() {
		return adaptorId;
	}

	public AdaptorEventType getType() {
		return type;
	}

	public static enum AdaptorEventType {

		Enable, Disable

	}

}
