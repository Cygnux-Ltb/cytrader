package io.cygnus.console.transport;

import io.mercury.common.collections.group.IntGroup;
import io.mercury.transport.api.Publisher;

public class OutboxPublisherGroup extends IntGroup<Publisher<String, String>> {

	/**
	 * 
	 */
	public static final OutboxPublisherGroup GROUP_INSTANCE = new OutboxPublisherGroup();

	// TODO ERROR
	private OutboxPublisherGroup() {
		super(() -> null);
	}

}
