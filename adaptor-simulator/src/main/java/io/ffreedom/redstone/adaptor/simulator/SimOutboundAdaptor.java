package io.ffreedom.redstone.adaptor.simulator;

import java.util.stream.Collectors;

import io.ffreedom.common.param.ParamMap;
import io.ffreedom.persistence.avro.entity.MarketDataSubscribe;
import io.ffreedom.persistence.avro.serializable.AvroBytesSerializer;
import io.ffreedom.redstone.adaptor.simulator.dto.SimSubscribeMarketData;
import io.ffreedom.redstone.core.account.Account;
import io.ffreedom.redstone.core.adaptor.OutboundAdaptor;
import io.ffreedom.redstone.core.adaptor.dto.ReplyBalance;
import io.ffreedom.redstone.core.adaptor.dto.ReplyPositions;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.redstone.core.order.storage.OrderKeeper;
import io.ffreedom.transport.core.role.Sender;
import io.ffreedom.transport.socket.SocketSender;
import io.ffreedom.transport.socket.config.SocketConfigurator;

public class SimOutboundAdaptor extends OutboundAdaptor<SimSubscribeMarketData, ReplyPositions, ReplyBalance> {

	private Sender<byte[]> mdSender;
	private Sender<byte[]> tdSender;

	private ParamMap<SimAdaptorParams> paramMap;

	private AvroBytesSerializer serializer = new AvroBytesSerializer();

	public SimOutboundAdaptor(int adaptorId, String adaptorName, ParamMap<SimAdaptorParams> paramMap) {
		super(adaptorId, adaptorName);
	}

	@Override
	public void init() {
		SocketConfigurator mdConfigurator = SocketConfigurator.builder()
				.setHost(paramMap.getString(SimAdaptorParams.SIM_MD_HOST))
				.setPort(paramMap.getInteger(SimAdaptorParams.SIM_MD_PORT)).build();
		SocketConfigurator tdConfigurator = SocketConfigurator.builder()
				.setHost(paramMap.getString(SimAdaptorParams.SIM_TD_HOST))
				.setPort(paramMap.getInteger(SimAdaptorParams.SIM_TD_PORT)).build();
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
	public boolean subscribeMarketData(SimSubscribeMarketData subscribe) {
		MarketDataSubscribe simSubscribe = MarketDataSubscribe.newBuilder()
				.setUniqueId(Integer.valueOf(subscribe.getInvestorId()))
				.setStartTradingDay(subscribe.getStartTradingDay()).setEndTradingDay(subscribe.getEndTradingDay())
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
	public ReplyPositions queryPositions(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReplyBalance queryBalance(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

}
