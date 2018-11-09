package io.ffreedom.redstone.adaptor.sim;

import java.util.ArrayList;
import java.util.Collection;

import io.ffreedom.common.param.ParamMap;
import io.ffreedom.persistence.avro.entity.MarketDataSubscribe;
import io.ffreedom.persistence.avro.serializable.AvroBytesSerializer;
import io.ffreedom.redstone.actor.OrderActor;
import io.ffreedom.redstone.adaptor.base.AdaptorParams;
import io.ffreedom.redstone.adaptor.sim.dto.SimReqSubscribeMarketData;
import io.ffreedom.redstone.core.adaptor.OutboundAdaptor;
import io.ffreedom.redstone.core.adaptor.req.ReqQueryBalance;
import io.ffreedom.redstone.core.adaptor.req.ReqQueryPositions;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.order.enums.OrdStatus;
import io.ffreedom.transport.core.role.Sender;
import io.ffreedom.transport.socket.SocketSender;
import io.ffreedom.transport.socket.config.SocketConfigurator;

public class SimOutboundAdaptor extends BaseSimAdaptor
		implements OutboundAdaptor<SimReqSubscribeMarketData, ReqQueryPositions, ReqQueryBalance> {

	private Sender<byte[]> mdSender;
	private Sender<byte[]> tdSender;

	private AvroBytesSerializer serializer = new AvroBytesSerializer();

	public SimOutboundAdaptor(ParamMap<AdaptorParams> paramMap) {
		super(paramMap);
	}

	@Override
	public void init() {
		SocketConfigurator mdConfigurator = SocketConfigurator.builder()
				.setHost(paramMap.getString(AdaptorParams.SIM_MD_HOST))
				.setPort(paramMap.getInteger(AdaptorParams.SIM_MD_PORT)).build();
		SocketConfigurator tdConfigurator = SocketConfigurator.builder()
				.setHost(paramMap.getString(AdaptorParams.SIM_TD_HOST))
				.setPort(paramMap.getInteger(AdaptorParams.SIM_TD_PORT)).build();
		this.mdSender = new SocketSender(mdConfigurator);
		this.tdSender = new SocketSender(tdConfigurator);
	}

	@Override
	public String getAdaptorName() {
		return "SimOutboundAdaptor$" + this.hashCode();
	}

	public boolean newOredr(Order order) {
		io.ffreedom.persistence.avro.entity.Order simOrder = io.ffreedom.persistence.avro.entity.Order.newBuilder()
				.setOrderRef(new Long(order.getOrdSysId()).intValue())
				.setInstrumentId(order.getInstrument().getInstrumentCode())
				.setLimitPrice(order.getOrdQtyPrice().getOfferPrice())
				.setVolumeTotalOriginal(new Double(order.getOrdQtyPrice().getTotalQty()).intValue())
				.setOrderStatus(OrdStatus.PendingNew.getCode()).setDirection(order.getOrdSide().getCode()).build();
		byte[] byteMsg = serializer.serialization(simOrder);
		tdSender.sent(byteMsg);
		return true;
	}

	public boolean cancelOrder(Order order) {
		Order cancelOrder = OrderActor.getOrder(order.getOrdSysId());
		io.ffreedom.persistence.avro.entity.Order simOrder = io.ffreedom.persistence.avro.entity.Order.newBuilder()
				.setOrderRef(new Long(order.getOrdSysId()).intValue())
				.setInstrumentId(cancelOrder.getInstrument().getInstrumentCode())
				.setLimitPrice(cancelOrder.getOrdQtyPrice().getOfferPrice())
				.setVolumeTotalOriginal(new Double(cancelOrder.getOrdQtyPrice().getTotalQty()).intValue())
				.setOrderStatus(OrdStatus.PendingCancel.getCode()).setDirection(cancelOrder.getOrdSide().getCode())
				.build();
		byte[] byteMsg = serializer.serialization(simOrder);
		tdSender.sent(byteMsg);
		return true;
	}

	@Override
	public boolean subscribeMarketData(SimReqSubscribeMarketData subscribe) {
		MarketDataSubscribe simSubscribe = MarketDataSubscribe.newBuilder()
				.setUniqueId(Integer.valueOf(subscribe.getInvestorId()))
				.setStartTradingDay(subscribe.getStartTradingDay()).setEndTradingDay(subscribe.getEndTradingDay())
				.setInstrumentIdList(new ArrayList<>(subscribe.getInstrumentIdList())).build();
		byte[] byteMsg = serializer.serialization(simSubscribe);
		mdSender.sent(byteMsg);
		return true;
	}

	@Override
	public Collection<Order> queryPositions(ReqQueryPositions queryPositions) {

		return null;
	}

	@Override
	public boolean close() {
		mdSender.destroy();
		tdSender.destroy();
		return false;
	}

	@Override
	public Collection<Order> queryBalance(ReqQueryBalance reqQueryPositions) {
		// TODO Auto-generated method stub
		return null;
	}

}
