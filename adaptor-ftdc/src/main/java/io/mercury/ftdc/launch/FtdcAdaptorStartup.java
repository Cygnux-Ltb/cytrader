package io.mercury.ftdc.launch;

import com.rabbitmq.client.MessageProperties;

import io.mercury.common.util.Assertor;
import io.mercury.transport.rabbitmq.configurator.RmqConnection;
import io.mercury.transport.rabbitmq.configurator.RmqPublisherConfigurator;
import io.mercury.transport.rabbitmq.declare.ExchangeAndBinding;

public final class FtdcAdaptorStartup {

	public static void main(String[] args) {
		Assertor.requiredLength(args, 4, "input args");

		RmqConnection connection = RmqConnection.configuration(args[0], Integer.parseInt(args[1]), args[2], args[3])
				.build();

		ExchangeAndBinding directExchange = ExchangeAndBinding.fanout("test");

		RmqPublisherConfigurator build = RmqPublisherConfigurator.configuration(connection, directExchange)
				.setMsgPropsSupplier(() -> MessageProperties.PERSISTENT_BASIC.builder()
						.correlationId(Long.toString(System.currentTimeMillis())).build())
				.build();

		System.out.println(build);

	}

}
