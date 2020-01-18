package io.redstone.adaptor.simulator;

import java.util.List;
import java.util.function.Function;

import io.mercury.codec.avro.AvroBytesDeserializer;
import io.mercury.common.param.ParamKeyMap;
import io.mercury.transport.core.api.Receiver;
import io.mercury.transport.socket.SocketReceiver;
import io.mercury.transport.socket.config.SocketConfigurator;
import io.polaris.financial.market.impl.BasicMarketData;
import io.redstone.core.adaptor.impl.InboundAdaptor;
import io.redstone.core.order.impl.OrderReport;
import io.redstone.core.strategy.StrategyScheduler;
import io.redstone.persistence.avro.entity.MarketDataLevel1;
import io.redstone.persistence.avro.entity.Order;

public class SimInboundAdaptor extends InboundAdaptor {

	private Receiver mdReceiver;

	private Receiver tdReceiver;

	private StrategyScheduler scheduler;

	protected SocketConfigurator mdConfigurator;

	protected SocketConfigurator tdConfigurator;

	private Function<MarketDataLevel1, BasicMarketData> marketDataFunction = marketDataLevel1 -> {
		// TODO Auto-generated method stub
		return null;
	};

	private Function<Order, OrderReport> orderFunction = order -> {
		// TODO Auto-generated method stub
		return null;
	};

	private AvroBytesDeserializer<MarketDataLevel1> marketDataDeserializer = new AvroBytesDeserializer<>(
			MarketDataLevel1.class);

	private AvroBytesDeserializer<Order> orderDeserializer1 = new AvroBytesDeserializer<>(Order.class);

	public SimInboundAdaptor(int adaptorId, String adaptorName, ParamKeyMap<SimAdaptorParams> paramMap,
			StrategyScheduler scheduler) {
		super(adaptorId, adaptorName);
		this.scheduler = scheduler;
		this.mdReceiver = new SocketReceiver(mdConfigurator, (bytes) -> {
			List<MarketDataLevel1> marketDatas = marketDataDeserializer.deSerializationMultiple(bytes);
			for (MarketDataLevel1 marketData : marketDatas) {
				this.scheduler.onMarketData(marketDataFunction.apply(marketData));
			}
		});
		this.tdReceiver = new SocketReceiver(tdConfigurator, (bytes) -> {
			List<Order> orders = orderDeserializer1.deSerializationMultiple(bytes);
			for (Order order : orders) {
				this.scheduler.onOrderReport(orderFunction.apply(order));
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
	public String adaptorName() {
		return "SimInboundAdaptor$" + this.hashCode();
	}

	@Override
	public boolean close() {
		mdReceiver.destroy();
		tdReceiver.destroy();
		return false;
	}

}
