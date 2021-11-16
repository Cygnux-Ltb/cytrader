package io.cygnus.restful.service.transport;

import io.mercury.common.collections.group.AbstractGroup;
import io.mercury.transport.api.Publisher;

public class OutboxPublisherGroup extends AbstractGroup<Integer, Publisher<byte[]>> {

	/**
	 * 
	 */

	public static final OutboxPublisherGroup INSTANCE = new OutboxPublisherGroup();

	private OutboxPublisherGroup() {
		super(() -> null);
	}

	protected synchronized Publisher<byte[]> createMember(Integer cygId) {

//		CygInfoService dao = new CygInfoService();
//
//		CygInfoEntity cygInfo = dao.get(cygId);
//
//		RabbitPublisherCfg configurator = RabbitPublisherCfg.configuration(cygMqConfig.getServerMqHost(),
//				cygMqConfig.getServerMqPort(), cygMqConfig.getServerMqUsername(), cygMqConfig.getServerMqPassword(),
//				ExchangeDefinition.fanout(cygMqConfig.getServerInbox())).build();
//
//		return new RabbitMqPublisher("Cyg-" + cygId + "-RestfulToOutbox", configurator);

		return null;
	}

}
