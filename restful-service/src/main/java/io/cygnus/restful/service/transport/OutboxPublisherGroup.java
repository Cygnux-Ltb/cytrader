package io.cygnus.restful.service.transport;

import java.util.List;

import io.cygnus.db.dao.CygInfoDao;
import io.cygnus.service.entity.CygMqConfig;
import io.mercury.common.collections.group.AbstractGroup;
import io.mercury.transport.api.Publisher;
import io.mercury.transport.rabbitmq.RabbitMqPublisher;
import io.mercury.transport.rabbitmq.configurator.RmqPublisherConfigurator;
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

		CygInfoDao dao = new CygInfoDao();

		List<CygMqConfig> cygMqConfigs = dao.getCygMqConfigById(cygId);

		if (cygMqConfigs.isEmpty() || cygMqConfigs.size() > 1) {
			throw new IllegalArgumentException(
					"Query MqConfig for CygId(" + cygId + ") is null or more than one record.");
		}

		CygMqConfig cygMqConfig = cygMqConfigs.get(0);

		RmqPublisherConfigurator configurator = RmqPublisherConfigurator.configuration(cygMqConfig.getServerMqHost(),
				cygMqConfig.getServerMqPort(), cygMqConfig.getServerMqUsername(), cygMqConfig.getServerMqPassword(),
				ExchangeDefinition.fanout(cygMqConfig.getServerInbox())).build();

		return new RabbitMqPublisher("Cyg-" + cygId + "-RestfulToOutbox", configurator);
	}

}
