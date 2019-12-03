package io.redstone.adaptor.simulator;

import java.util.stream.Collectors;

import io.mercury.common.param.ParamKeyMap;
import io.mercury.persistence.avro.serializable.AvroBytesSerializer;
import io.mercury.transport.core.api.Sender;
import io.mercury.transport.socket.SocketSender;
import io.mercury.transport.socket.config.SocketConfigurator;
import io.redstone.core.account.Account;
import io.redstone.core.adaptor.dto.SubscribeMarketData;
import io.redstone.core.adaptor.impl.OutboundAdaptor;
import io.redstone.core.order.api.Order;
import io.redstone.core.order.enums.OrdStatus;
import io.redstone.core.order.impl.ChildOrder;
import io.redstone.engine.storage.OrderKeeper;
import io.redstone.persistence.avro.entity.MarketDataSubscribe;

public class SimOutboundAdaptor extends OutboundAdaptor {

	private Sender<byte[]> mdSender;
	private Sender<byte[]> tdSender;

	private ParamKeyMap<SimAdaptorParams> paramMap;

	private AvroBytesSerializer serializer = new AvroBytesSerializer();

	public SimOutboundAdaptor(int adaptorId, String adaptorName, ParamKeyMap<SimAdaptorParams> paramMap) {
		super(adaptorId, adaptorName);
		this.paramMap = paramMap;
		SocketConfigurator mdConfigurator = SocketConfigurator.builder()
				.host(paramMap.getString(SimAdaptorParams.MdHost))
				.port(paramMap.getInteger(SimAdaptorParams.MdPort)).build();
		SocketConfigurator tdConfigurator = SocketConfigurator.builder()
				.host(paramMap.getString(SimAdaptorParams.TdHost))
				.port(paramMap.getInteger(SimAdaptorParams.TdPort)).build();
		this.mdSender = new SocketSender(mdConfigurator);
		this.tdSender = new SocketSender(tdConfigurator);

	}

	@Override
	public String adaptorName() {
		return "SimOutboundAdaptor$" + this.hashCode();
	}

	public boolean newOredr(ChildOrder order) {
		io.redstone.persistence.avro.entity.Order simOrder = io.redstone.persistence.avro.entity.Order.newBuilder()
				.setOrderRef(Long.valueOf(order.ordSysId()).intValue())
				.setInstrumentId(order.instrument().code())
				.setLimitPrice(order.ordPrice().offerPrice())
				.setVolumeTotalOriginal(Double.valueOf(order.ordQty().offerQty()).intValue())
				.setOrderStatus(OrdStatus.PendingNew.code()).setDirection(order.ordSide().code()).build();
		byte[] byteMsg = serializer.serialization(simOrder);
		tdSender.send(byteMsg);
		return true;
	}

	public boolean cancelOrder(ChildOrder order) {
		Order cancelOrder = OrderKeeper.getOrder(order.ordSysId());
		io.redstone.persistence.avro.entity.Order simOrder = io.redstone.persistence.avro.entity.Order.newBuilder()
				.setOrderRef(Long.valueOf(order.ordSysId()).intValue())
				.setInstrumentId(cancelOrder.instrument().code())
				.setLimitPrice(order.ordPrice().offerPrice())
				.setVolumeTotalOriginal(Double.valueOf(order.ordQty().offerQty()).intValue())
				.setOrderStatus(OrdStatus.PendingCancel.code()).setDirection(cancelOrder.ordSide().code()).build();
		byte[] byteMsg = serializer.serialization(simOrder);
		tdSender.send(byteMsg);
		return true;
	}

	@Override
	public boolean subscribeMarketData(SubscribeMarketData subscribe) {
		MarketDataSubscribe simSubscribe = MarketDataSubscribe.newBuilder().setUniqueId(Integer.valueOf(1))
				.setStartTradingDay(paramMap.getDate(SimAdaptorParams.StartTradingDay).toString())
				.setEndTradingDay(paramMap.getDate(SimAdaptorParams.EndTradingDay).toString())
				.setInstrumentIdList(subscribe.getInstrumentSet().stream()
						.map(instrument -> instrument.code()).collect(Collectors.toList()))
				.build();
		byte[] byteMsg = serializer.serialization(simSubscribe);
		mdSender.send(byteMsg);
		return true;
	}

	@Override
	public boolean close() {
		mdSender.destroy();
		tdSender.destroy();
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

}
