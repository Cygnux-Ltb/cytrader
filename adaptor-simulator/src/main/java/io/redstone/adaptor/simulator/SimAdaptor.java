package io.redstone.adaptor.simulator;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.mercury.common.param.map.ImmutableParamMap;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.serialization.avro.AvroBinaryDeserializer;
import io.mercury.transport.core.api.Receiver;
import io.mercury.transport.core.api.Sender;
import io.mercury.transport.socket.SocketReceiver;
import io.mercury.transport.socket.SocketSender;
import io.mercury.transport.socket.configurator.SocketConfigurator;
import io.redstone.core.account.Account;
import io.redstone.core.adaptor.base.AdaptorBaseImpl;
import io.redstone.core.order.OrderKeeper;
import io.redstone.core.order.enums.OrdStatus;
import io.redstone.core.order.specific.ChildOrder;
import io.redstone.core.order.structure.OrdReport;
import io.redstone.core.strategy.StrategyScheduler;
import io.redstone.persistence.avro.entity.MarketDataLevel1;
import io.redstone.persistence.avro.entity.MarketDataSubscribe;
import io.redstone.persistence.avro.entity.Order;

public class SimAdaptor extends AdaptorBaseImpl {

	private Receiver mdReceiver;
	private Receiver tdReceiver;

	private Sender<byte[]> mdSender;
	private Sender<byte[]> tdSender;

	private StrategyScheduler scheduler;

	protected ImmutableParamMap<SimAdaptorParam> paramMap;

	protected SocketConfigurator mdConfigurator;

	protected SocketConfigurator tdConfigurator;

	private Function<MarketDataLevel1, BasicMarketData> marketDataFunction = marketDataLevel1 -> {
		// TODO Auto-generated method stub
		return null;
	};

	private Function<Order, OrdReport> orderFunction = order -> {
		// TODO Auto-generated method stub
		return null;
	};

	private AvroBinaryDeserializer<MarketDataLevel1> marketDataDeserializer = new AvroBinaryDeserializer<>(
			MarketDataLevel1.class);

	private AvroBinaryDeserializer<Order> orderDeserializer1 = new AvroBinaryDeserializer<>(Order.class);

	public SimAdaptor(int adaptorId, String adaptorName, ImmutableParamMap<SimAdaptorParam> paramMap,
			StrategyScheduler scheduler) {
		super(adaptorId, adaptorName);

		this.paramMap = paramMap;
		this.scheduler = scheduler;

		SocketConfigurator mdConfigurator = SocketConfigurator.builder()
				.host(paramMap.getString(SimAdaptorParam.MdHost)).port(paramMap.getInt(SimAdaptorParam.MdPort)).build();
		SocketConfigurator tdConfigurator = SocketConfigurator.builder()
				.host(paramMap.getString(SimAdaptorParam.TdHost)).port(paramMap.getInt(SimAdaptorParam.TdPort)).build();

		this.mdReceiver = new SocketReceiver(mdConfigurator, (bytes) -> {
			List<MarketDataLevel1> marketDatas = marketDataDeserializer.deserializationMultiple(bytes);
			for (MarketDataLevel1 marketData : marketDatas) {
				this.scheduler.onMarketData(marketDataFunction.apply(marketData));
			}
		});
		this.tdReceiver = new SocketReceiver(tdConfigurator, (bytes) -> {
			List<Order> orders = orderDeserializer1.deserializationMultiple(bytes);
			for (Order order : orders) {
				this.scheduler.onOrderReport(orderFunction.apply(order));
			}
		});

		this.mdSender = new SocketSender(mdConfigurator);
		this.tdSender = new SocketSender(tdConfigurator);
	}

	@Override
	public boolean startup() {
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
				.setStartTradingDay(paramMap.getString(SimAdaptorParam.TradingDayStart))
				.setEndTradingDay(paramMap.getString(SimAdaptorParam.TradingDayEnd))
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
	public boolean newOredr(ChildOrder order) {
		io.redstone.persistence.avro.entity.Order simOrder = io.redstone.persistence.avro.entity.Order.newBuilder()
				.setOrderRef(Long.valueOf(order.ordSysId()).intValue()).setInstrumentId(order.instrument().code())
				.setLimitPrice(order.ordPrice().offerPrice())
				.setVolumeTotalOriginal(Double.valueOf(order.ordQty().offerQty()).intValue())
				.setOrderStatus(OrdStatus.PendingNew.code()).setDirection(order.trdDirection().code()).build();
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
	public boolean cancelOrder(ChildOrder order) {
		io.redstone.core.order.Order cancelOrder = OrderKeeper.getOrder(order.ordSysId());
		io.redstone.persistence.avro.entity.Order simOrder = io.redstone.persistence.avro.entity.Order.newBuilder()
				.setOrderRef(Long.valueOf(order.ordSysId()).intValue()).setInstrumentId(cancelOrder.instrument().code())
				.setLimitPrice(order.ordPrice().offerPrice())
				.setVolumeTotalOriginal(Double.valueOf(order.ordQty().offerQty()).intValue())
				.setOrderStatus(OrdStatus.PendingCancel.code()).setDirection(cancelOrder.trdDirection().code()).build();
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
	public boolean queryOrder(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean queryPositions(Account account) {
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
