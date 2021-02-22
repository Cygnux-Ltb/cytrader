package io.cygnus.restful.service.transport;

import java.util.List;

import io.cygnus.db.dao.CygInfoDao;
import io.cygnus.service.entity.CygMqConfig;
import io.mercury.common.group.AbstractGroup;
import io.mercury.transport.core.api.Publisher;
import io.mercury.transport.rabbitmq.RabbitMqPublisher;
import io.mercury.transport.rabbitmq.configurator.RmqPublisherConfigurator;
import io.mercury.transport.rabbitmq.declare.ExchangeRelationship;

public class OutboxPublisherGroup extends AbstractGroup<Integer, Publisher<byte[]>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7651265129922990838L;

	public static final OutboxPublisherGroup INSTANCE = new OutboxPublisherGroup();

	private OutboxPublisherGroup() {
	}

	@Override
	protected synchronized Publisher<byte[]> createMember(Integer thadId) {

		CygInfoDao dao = new CygInfoDao();

		List<CygMqConfig> thadMqConfigs = dao.getCygMqConfigById(thadId);

		if (thadMqConfigs.isEmpty() || thadMqConfigs.size() > 1) {
			throw new IllegalArgumentException(
					"Query MqConfig for ThadId(" + thadId + ") is null or more than one record.");
		}

		CygMqConfig thadMqConfig = thadMqConfigs.get(0);

		RmqPublisherConfigurator configurator = RmqPublisherConfigurator.configuration(thadMqConfig.getServerMqHost(),
				thadMqConfig.getServerMqPort(), thadMqConfig.getServerMqUsername(), thadMqConfig.getServerMqPassword(),
				ExchangeRelationship.fanout(thadMqConfig.getServerInbox())).build();

		return new RabbitMqPublisher("Thad-" + thadId + "-RestfulToOutbox", configurator);
	}

}
