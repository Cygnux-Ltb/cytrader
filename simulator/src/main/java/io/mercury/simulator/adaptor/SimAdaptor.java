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
import io.mercury.redstone.core.adaptor.Command;
import io.mercury.redstone.core.order.ActualChildOrder;
import io.mercury.redstone.core.order.Order;
import io.mercury.redstone.core.order.OrderKeeper;
import io.mercury.redstone.core.order.enums.OrdStatus;
import io.mercury.redstone.core.order.structure.OrdReport;
import io.mercury.redstone.core.strategy.StrategyScheduler;
import io.mercury.serialization.avro.AvroBinaryDeserializer;
import io.mercury.simulator.persistence.avro.entity.ExOrder;
import io.mercury.simulator.persistence.avro.entity.MarketDataLevel1;
import io.mercury.simulator.persistence.avro.entity.MarketDataSubscribe;
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

	private Function<ExOrder, OrdReport> orderFunction = order -> {
		return null;
	};

	private AvroBinaryDeserializer<MarketDataLevel1> marketDataDeserializer = new AvroBinaryDeserializer<>(
			MarketDataLevel1.class);

	private AvroBinaryDeserializer<ExOrder> orderDeserializer = new AvroBinaryDeserializer<>(ExOrder.class);

	public SimAdaptor(int adaptorId, Account account, ImmutableParamMap<SimAdaptorParamKey> paramMap,
			StrategyScheduler<BasicMarketData> scheduler) {
		super(adaptorId, account);
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
			List<ExOrder> orders = orderDeserializer.deserializationMultiple(bytes);
			for (ExOrder order : orders) {
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
	public boolean newOredr(Account account, ActualChildOrder order) {
		ExOrder simOrder = ExOrder.newBuilder().setOrderRef(Long.valueOf(order.uniqueId()).intValue())
				.setInstrumentId(order.instrument().code()).setLimitPrice(order.price().offerPrice())
				.setVolumeTotalOriginal(Double.valueOf(order.qty().offerQty()).intValue())
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
	public boolean cancelOrder(Account account, ActualChildOrder order) {
		Order cancelOrder = OrderKeeper.getOrder(order.uniqueId());
		ExOrder simOrder = ExOrder.newBuilder().setOrderRef(Long.valueOf(order.uniqueId()).intValue())
				.setInstrumentId(cancelOrder.instrument().code()).setLimitPrice(order.price().offerPrice())
				.setVolumeTotalOriginal(Double.valueOf(order.qty().offerQty()).intValue())
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

	@Override
	public List<Account> accounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean sendCommand(Command command) {
		// TODO Auto-generated method stub
		return false;
	}

}
