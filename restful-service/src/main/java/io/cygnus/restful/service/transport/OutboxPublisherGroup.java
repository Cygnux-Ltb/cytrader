package io.cygnus.restful.service.transport;

import java.util.List;

import io.cygnus.repository.entity.CygInfoEntity;
import io.cygnus.repository.service.CygInfoService;
import io.mercury.common.collections.group.AbstractGroup;
import io.mercury.transport.api.Publisher;
import io.mercury.transport.rabbitmq.RabbitMqPublisher;
import io.mercury.transport.rabbitmq.configurator.RabbitPublisherCfg;
import io.mercury.transport.rabbitmq.declare.ExchangeDefinition;

public class OutboxPublisherGroup extends AbstractGroup<Integer, Publisher<byte[]>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7651265129922990838L;

	public static final OutboxPublisherGroup INSTANCE = new OutboxPublisherGroup();

	private OutboxPublisherGroup() {
	}

	@Override
	protected synchronized Publisher<byte[]> createMember(Integer cygId) {

		CygInfoService dao = new CygInfoService();

		CygInfoEntity cygInfo = dao.get(cygId);


		RabbitPublisherCfg configurator = RabbitPublisherCfg.configuration(cygMqConfig.getServerMqHost(),
				cygMqConfig.getServerMqPort(), cygMqConfig.getServerMqUsername(), cygMqConfig.getServerMqPassword(),
				ExchangeDefinition.fanout(cygMqConfig.getServerInbox())).build();

		return new RabbitMqPublisher("Cyg-" + cygId + "-RestfulToOutbox", configurator);
	}

}
