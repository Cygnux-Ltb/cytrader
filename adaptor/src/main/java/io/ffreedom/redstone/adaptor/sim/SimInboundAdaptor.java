package io.ffreedom.redstone.adaptor.sim;

import java.util.List;

import io.ffreedom.common.functional.Converter;
import io.ffreedom.common.param.ParamMap;
import io.ffreedom.persistence.avro.entity.MarketDataLevel1;
import io.ffreedom.persistence.avro.serializable.AvroBytesDeserializer;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.adaptor.base.AdaptorParams;
import io.ffreedom.redstone.adaptor.sim.converter.MarketDataConverter;
import io.ffreedom.redstone.adaptor.sim.converter.OrderConverter;
import io.ffreedom.redstone.core.adaptor.InboundAdaptor;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.strategy.StrategyScheduler;
import io.ffreedom.transport.core.role.Receiver;
import io.ffreedom.transport.socket.SocketReceiver;

public class SimInboundAdaptor extends InboundAdaptor {

	private Receiver mdReceiver;

	private Receiver tdReceiver;

	private StrategyScheduler scheduler;

	private Converter<MarketDataLevel1, BasicMarketData> marketDataConverter = new MarketDataConverter();

	private Converter<io.ffreedom.persistence.avro.entity.Order, Order> orderConverter = new OrderConverter();

	private AvroBytesDeserializer<io.ffreedom.persistence.avro.entity.MarketDataLevel1> marketDataDeserializer = new AvroBytesDeserializer<>(
			MarketDataLevel1.class);

	private AvroBytesDeserializer<io.ffreedom.persistence.avro.entity.Order> orderDeserializer1 = new AvroBytesDeserializer<>(
			io.ffreedom.persistence.avro.entity.Order.class);

	public SimInboundAdaptor(ParamMap<AdaptorParams> paramMap, StrategyScheduler scheduler) {
		super(paramMap);
		this.scheduler = scheduler;
	}

	@Override
	public void init() {
		this.mdReceiver = new SocketReceiver(mdConfigurator, (bytes) -> {
			List<MarketDataLevel1> marketDatas = marketDataDeserializer.deSerializationMultiple(bytes);
			for (MarketDataLevel1 marketData : marketDatas) {
				scheduler.onMarketData(marketDataConverter.convert(marketData));
			}
		});
		this.tdReceiver = new SocketReceiver(tdConfigurator, (bytes) -> {
			List<io.ffreedom.persistence.avro.entity.Order> orders = orderDeserializer1.deSerializationMultiple(bytes);
			for (io.ffreedom.persistence.avro.entity.Order order : orders) {
				scheduler.onInboundOrder(orderConverter.convert(order));
			}
		});
	}

	@Override
	public boolean activate() {
		mdReceiver.receive();
		tdReceiver.receive();
		return mdReceiver.isConnected() && tdReceiver.isConnected();
	}

	@Override
	public String getAdaptorName() {
		return "SimInboundAdaptor$" + this.hashCode();
	}

	@Override
	public boolean close() {
		mdReceiver.destroy();
		tdReceiver.destroy();
		return false;
	}

}
