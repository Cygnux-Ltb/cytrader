package io.ffreedom.redstone.core.event;

public interface BusEvent {

	EventChannel getEventChannel();

	EventType getEventType();

	enum EventChannel {
		InBound, Outbound
	}

	interface EventType {
		int getTypeCode();
	}

}
