package io.ffreedom.redstone.adaptor.simulator;

import java.util.stream.Collectors;

import io.ffreedom.common.param.ParamKeyMap;
import io.ffreedom.persistence.avro.entity.MarketDataSubscribe;
import io.ffreedom.persistence.avro.serializable.AvroBytesSerializer;
import io.ffreedom.redstone.core.account.Account;
import io.ffreedom.redstone.core.adaptor.OutboundAdaptor;
import io.ffreedom.redstone.core.adaptor.dto.SubscribeMarketData;
import io.ffreedom.redstone.core.order.api.Order;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.storage.OrderKeeper;
import io.ffreedom.transport.core.role.Sender;
import io.ffreedom.transport.socket.SocketSender;
import io.ffreedom.transport.socket.config.SocketConfigurator;

public class SimOutboundAdaptor extends OutboundAdaptor {

	private Sender<byte[]> mdSender;
	private Sender<byte[]> tdSender;

	private ParamKeyMap<SimAdaptorParams> paramMap;

	private AvroBytesSerializer serializer = new AvroBytesSerializer();

	public SimOutboundAdaptor(int adaptorId, String adaptorName, ParamKeyMap<SimAdaptorParams> paramMap) {
		super(adaptorId, adaptorName);
		SocketConfigurator mdConfigurator = SocketConfigurator.builder()
				.setHost(paramMap.getString(SimAdaptorParams.MdHost))
				.setPort(paramMap.getInteger(SimAdaptorParams.MdPort)).build();
		SocketConfigurator tdConfigurator = SocketConfigurator.builder()
				.setHost(paramMap.getString(SimAdaptorParams.TdHost))
				.setPort(paramMap.getInteger(SimAdaptorParams.TdPort)).build();
		this.mdSender = new SocketSender(mdConfigurator);
		this.tdSender = new SocketSender(tdConfigurator);

	}

	@Override
	public String getAdaptorName() {
		return "SimOutboundAdaptor$" + this.hashCode();
	}

	public boolean newOredr(Order order) {
		io.ffreedom.persistence.avro.entity.Order simOrder = io.ffreedom.persistence.avro.entity.Order.newBuilder()
				.setOrderRef(Long.valueOf(order.getOrdSysId()).intValue())
				.setInstrumentId(order.getInstrument().getInstrumentCode())
				.setLimitPrice(order.getQtyPrice().getOfferPrice())
				.setVolumeTotalOriginal(Double.valueOf(order.getQtyPrice().getOfferQty()).intValue())
				.setOrderStatus(OrdStatus.PendingNew.code()).setDirection(order.getSide().code()).build();
		byte[] byteMsg = serializer.serialization(simOrder);
		tdSender.sent(byteMsg);
		return true;
	}

	public boolean cancelOrder(Order order) {
		Order cancelOrder = OrderKeeper.getOrder(order.getOrdSysId());
		io.ffreedom.persistence.avro.entity.Order simOrder = io.ffreedom.persistence.avro.entity.Order.newBuilder()
				.setOrderRef(Long.valueOf(order.getOrdSysId()).intValue())
				.setInstrumentId(cancelOrder.getInstrument().getInstrumentCode())
				.setLimitPrice(cancelOrder.getQtyPrice().getOfferPrice())
				.setVolumeTotalOriginal(Double.valueOf(cancelOrder.getQtyPrice().getOfferQty()).intValue())
				.setOrderStatus(OrdStatus.PendingCancel.code()).setDirection(cancelOrder.getSide().code()).build();
		byte[] byteMsg = serializer.serialization(simOrder);
		tdSender.sent(byteMsg);
		return true;
	}

	@Override
	public boolean subscribeMarketData(SubscribeMarketData subscribe) {
		MarketDataSubscribe simSubscribe = MarketDataSubscribe.newBuilder().setUniqueId(Integer.valueOf(1))
				.setStartTradingDay(paramMap.getDate(SimAdaptorParams.StartTradingDay).toString())
				.setEndTradingDay(paramMap.getDate(SimAdaptorParams.EndTradingDay).toString())
				.setInstrumentIdList(subscribe.getInstrumentSet().stream()
						.map(instrument -> instrument.getInstrumentCode()).collect(Collectors.toList()))
				.build();
		byte[] byteMsg = serializer.serialization(simSubscribe);
		mdSender.sent(byteMsg);
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
