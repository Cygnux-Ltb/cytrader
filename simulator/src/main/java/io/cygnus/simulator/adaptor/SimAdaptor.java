package io.cygnus.simulator.adaptor;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

import io.cygnus.simulator.persistence.avro.entity.MarketDataLevel1;
import io.cygnus.simulator.persistence.avro.entity.MarketDataSubscribe;
import io.cygnus.simulator.persistence.avro.entity.SimOrder;
import io.horizon.structure.account.Account;
import io.horizon.structure.adaptor.AdaptorBaseImpl;
import io.horizon.structure.adaptor.Command;
import io.horizon.structure.event.InboundScheduler;
import io.horizon.structure.market.data.impl.BasicMarketData;
import io.horizon.structure.market.instrument.Instrument;
import io.horizon.structure.order.OrdReport;
import io.horizon.structure.order.Order;
import io.horizon.structure.order.OrderBookKeeper;
import io.horizon.structure.order.actual.ChildOrder;
import io.horizon.structure.order.enums.OrdStatus;
import io.mercury.common.param.Params;
import io.mercury.serialization.avro.AvroBinaryDeserializer;
import io.mercury.transport.core.api.Receiver;
import io.mercury.transport.core.api.Sender;
import io.mercury.transport.socket.SocketReceiver;
import io.mercury.transport.socket.SocketSender;
import io.mercury.transport.socket.configurator.SocketConfigurator;

public class SimAdaptor extends AdaptorBaseImpl<BasicMarketData> {

	private Receiver mdReceiver;
	private Receiver tdReceiver;

	private Sender<byte[]> mdSender;
	private Sender<byte[]> tdSender;

	private InboundScheduler<BasicMarketData> scheduler;

	protected Params<SimAdaptorParamKey> params;

	protected SocketConfigurator mdConfigurator;

	protected SocketConfigurator tdConfigurator;

	private Function<MarketDataLevel1, BasicMarketData> marketDataFunction = marketDataLevel1 -> {
		return null;
	};

	private Function<SimOrder, OrdReport> orderFunction = order -> {
		return null;
	};

	private AvroBinaryDeserializer<MarketDataLevel1> marketDataDeserializer = new AvroBinaryDeserializer<>(
			MarketDataLevel1.class);

	private AvroBinaryDeserializer<SimOrder> orderDeserializer = new AvroBinaryDeserializer<>(SimOrder.class);

	public SimAdaptor(int adaptorId, @Nonnull Account account, @Nonnull Params<SimAdaptorParamKey> params,
			InboundScheduler<BasicMarketData> scheduler) {
		super(adaptorId, "SimulatorAdaptor[" + adaptorId + "]", scheduler, account);
		this.params = params;
		this.scheduler = scheduler;
		SocketConfigurator mdConfigurator = SocketConfigurator.builder()
				.host(params.getString(SimAdaptorParamKey.MdHost)).port(params.getInt(SimAdaptorParamKey.MdPort))
				.build();
		SocketConfigurator tdConfigurator = SocketConfigurator.builder()
				.host(params.getString(SimAdaptorParamKey.TdHost)).port(params.getInt(SimAdaptorParamKey.TdPort))
				.build();
		this.mdReceiver = new SocketReceiver(mdConfigurator, (bytes) -> {
			List<MarketDataLevel1> marketDatas = marketDataDeserializer.deserializationMultiple(bytes);
			for (MarketDataLevel1 marketData : marketDatas) {
				this.scheduler.onMarketData(marketDataFunction.apply(marketData));
			}
		});
		this.tdReceiver = new SocketReceiver(tdConfigurator, (bytes) -> {
			List<SimOrder> orders = orderDeserializer.deserializationMultiple(bytes);
			for (SimOrder order : orders) {
				this.scheduler.onOrdReport(orderFunction.apply(order));
			}
		});

		this.mdSender = new SocketSender(mdConfigurator);
		this.tdSender = new SocketSender(tdConfigurator);
	}

	@Override
	public boolean startup0() {
		mdReceiver.receive();
		tdReceiver.receive();
		return mdReceiver.isConnected() && tdReceiver.isConnected();
	}

	@Override
	public String getAdaptorName() {
		return "SimAdaptor$" + this.hashCode();
	}

	@Override
	public boolean subscribeMarketData(Instrument... instruments) {

		MarketDataSubscribe simSubscribe = MarketDataSubscribe.newBuilder().setUniqueId(Integer.valueOf(1))
				.setStartTradingDay(params.getString(SimAdaptorParamKey.TradingDayStart))
				.setEndTradingDay(params.getString(SimAdaptorParamKey.TradingDayEnd)).setInstrumentIdList(Stream
						.of(instruments).map(instrument -> instrument.instrumentCode()).collect(Collectors.toList()))
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
	public boolean newOredr(Account account, ChildOrder order) {
		SimOrder simOrder = SimOrder.newBuilder().setOrderRef(Long.valueOf(order.getOrdId()).intValue())
				.setInstrumentId(order.getInstrument().instrumentCode()).setLimitPrice(order.getPrice().offerPrice())
				.setVolumeTotalOriginal(Double.valueOf(order.getQty().offerQty()).intValue())
				.setOrderStatus(OrdStatus.PendingNew.code()).setDirection(order.getDirection().code()).build();
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
	public boolean cancelOrder(Account account, ChildOrder order) {
		Order cancelOrder = OrderBookKeeper.getOrder(order.getOrdId());
		SimOrder simOrder = SimOrder.newBuilder().setOrderRef(Long.valueOf(order.getOrdId()).intValue())
				.setInstrumentId(cancelOrder.getInstrument().instrumentCode()).setLimitPrice(order.getPrice().offerPrice())
				.setVolumeTotalOriginal(Double.valueOf(order.getQty().offerQty()).intValue())
				.setOrderStatus(OrdStatus.PendingCancel.code()).setDirection(cancelOrder.getDirection().code()).build();
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
		return false;
	}

	@Override
	public boolean queryPositions(Account account, Instrument instrument) {
		return false;
	}

	@Override
	public boolean queryBalance(Account account) {
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
	public boolean sendCommand(Command command) {
		return false;
	}

}
