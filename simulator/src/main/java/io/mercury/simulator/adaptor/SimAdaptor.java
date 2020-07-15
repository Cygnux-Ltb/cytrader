package io.mercury.simulator.adaptor;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.mercury.common.param.map.ImmutableParamMap;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.redstone.core.account.Account;
import io.mercury.redstone.core.adaptor.AdaptorBaseImpl;
import io.mercury.redstone.core.order.ActChildOrder;
import io.mercury.redstone.core.order.OrderKeeper;
import io.mercury.redstone.core.order.enums.OrdStatus;
import io.mercury.redstone.core.order.structure.OrdReport;
import io.mercury.redstone.core.strategy.StrategyScheduler;
import io.mercury.serialization.avro.AvroBinaryDeserializer;
import io.mercury.simulator.persistence.avro.entity.MarketDataLevel1;
import io.mercury.simulator.persistence.avro.entity.MarketDataSubscribe;
import io.mercury.simulator.persistence.avro.entity.Order;
import io.mercury.transport.core.api.Receiver;
import io.mercury.transport.core.api.Sender;
import io.mercury.transport.socket.SocketReceiver;
import io.mercury.transport.socket.SocketSender;
import io.mercury.transport.socket.configurator.SocketConfigurator;

public class SimAdaptor extends AdaptorBaseImpl {

	private Receiver mdReceiver;
	private Receiver tdReceiver;

	private Sender<byte[]> mdSender;
	private Sender<byte[]> tdSender;

	private StrategyScheduler<BasicMarketData> scheduler;

	protected ImmutableParamMap<SimAdaptorParamKey> paramMap;

	protected SocketConfigurator mdConfigurator;

	protected SocketConfigurator tdConfigurator;

	private Function<MarketDataLevel1, BasicMarketData> marketDataFunction = marketDataLevel1 -> {
		return null;
	};

	private Function<Order, OrdReport> orderFunction = order -> {
		return null;
	};

	private AvroBinaryDeserializer<MarketDataLevel1> marketDataDeserializer = new AvroBinaryDeserializer<>(
			MarketDataLevel1.class);

	private AvroBinaryDeserializer<Order> orderDeserializer = new AvroBinaryDeserializer<>(Order.class);

	public SimAdaptor(int adaptorId, String adaptorName, Account account,
			ImmutableParamMap<SimAdaptorParamKey> paramMap, StrategyScheduler<BasicMarketData> scheduler) {
		super(adaptorId, adaptorName, account);
		this.paramMap = paramMap;
		this.scheduler = scheduler;
		SocketConfigurator mdConfigurator = SocketConfigurator.builder()
				.host(paramMap.getString(SimAdaptorParamKey.MdHost)).port(paramMap.getInt(SimAdaptorParamKey.MdPort))
				.build();
		SocketConfigurator tdConfigurator = SocketConfigurator.builder()
				.host(paramMap.getString(SimAdaptorParamKey.TdHost)).port(paramMap.getInt(SimAdaptorParamKey.TdPort))
				.build();
		this.mdReceiver = new SocketReceiver(mdConfigurator, (bytes) -> {
			List<MarketDataLevel1> marketDatas = marketDataDeserializer.deserializationMultiple(bytes);
			for (MarketDataLevel1 marketData : marketDatas) {
				this.scheduler.onMarketData(marketDataFunction.apply(marketData));
			}
		});
		this.tdReceiver = new SocketReceiver(tdConfigurator, (bytes) -> {
			List<Order> orders = orderDeserializer.deserializationMultiple(bytes);
			for (Order order : orders) {
				this.scheduler.onOrdReport(orderFunction.apply(order));
			}
		});

		this.mdSender = new SocketSender(mdConfigurator);
		this.tdSender = new SocketSender(tdConfigurator);
	}

	@Override
	public boolean innerStartup() {
		mdReceiver.receive();
		tdReceiver.receive();
		return mdReceiver.isConnected() && tdReceiver.isConnected();
	}

	@Override
	public String adaptorName() {
		return "SimInboundAdaptor$" + this.hashCode();
	}

	@Override
	public boolean subscribeMarketData(Instrument... instruments) {

		MarketDataSubscribe simSubscribe = MarketDataSubscribe.newBuilder().setUniqueId(Integer.valueOf(1))
				.setStartTradingDay(paramMap.getString(SimAdaptorParamKey.TradingDayStart))
				.setEndTradingDay(paramMap.getString(SimAdaptorParamKey.TradingDayEnd))
				.setInstrumentIdList(
						Stream.of(instruments).map(instrument -> instrument.code()).collect(Collectors.toList()))
				.build();
		byte[] byteMsg;
		try {
			byteMsg = simSubscribe.toByteBuffer().array();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		mdSender.send(byteMsg);
		return true;
	}

	@Override
	public boolean newOredr(ActChildOrder order) {
		io.mercury.simulator.persistence.avro.entity.Order simOrder = io.mercury.simulator.persistence.avro.entity.Order
				.newBuilder().setOrderRef(Long.valueOf(order.ordSysId()).intValue())
				.setInstrumentId(order.instrument().code()).setLimitPrice(order.ordPrice().offerPrice())
				.setVolumeTotalOriginal(Double.valueOf(order.ordQty().offerQty()).intValue())
				.setOrderStatus(OrdStatus.PendingNew.code()).setDirection(order.direction().code()).build();
		byte[] byteMsg;
		try {
			byteMsg = simOrder.toByteBuffer().array();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		tdSender.send(byteMsg);
		return true;
	}

	@Override
	public boolean cancelOrder(ActChildOrder order) {
		io.mercury.redstone.core.order.Order cancelOrder = OrderKeeper.getOrder(order.ordSysId());
		io.mercury.simulator.persistence.avro.entity.Order simOrder = io.mercury.simulator.persistence.avro.entity.Order
				.newBuilder().setOrderRef(Long.valueOf(order.ordSysId()).intValue())
				.setInstrumentId(cancelOrder.instrument().code()).setLimitPrice(order.ordPrice().offerPrice())
				.setVolumeTotalOriginal(Double.valueOf(order.ordQty().offerQty()).intValue())
				.setOrderStatus(OrdStatus.PendingCancel.code()).setDirection(cancelOrder.direction().code()).build();
		byte[] byteMsg;
		try {
			byteMsg = simOrder.toByteBuffer().array();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		tdSender.send(byteMsg);
		return true;
	}

	@Override
	public boolean queryOrder(Account account, Instrument instrument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean queryPositions(Account account, Instrument instrument) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean queryBalance(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close() throws IOException {
		mdSender.destroy();
		tdSender.destroy();
		mdReceiver.destroy();
		tdReceiver.destroy();
	}

}
