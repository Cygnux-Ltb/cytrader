package io.redstone.adaptor.simulator;

import java.io.IOException;
import java.util.stream.Collectors;

import io.mercury.common.param.ImmutableParamMap;
import io.mercury.transport.core.api.Sender;
import io.mercury.transport.socket.SocketSender;
import io.mercury.transport.socket.configurator.SocketConfigurator;
import io.redstone.core.account.InvestorAccount;
import io.redstone.core.adaptor.base.OutboundAdaptor;
import io.redstone.core.adaptor.dto.SubscribeMarketData;
import io.redstone.core.order.api.Order;
import io.redstone.core.order.enums.OrdStatus;
import io.redstone.core.order.impl.ChildOrder;
import io.redstone.core.order.storage.OrderKeeper;
import io.redstone.persistence.avro.entity.MarketDataSubscribe;

public class SimOutboundAdaptor extends OutboundAdaptor {

	private Sender<byte[]> mdSender;
	private Sender<byte[]> tdSender;

	private ImmutableParamMap<SimAdaptorParams> paramMap;

	public SimOutboundAdaptor(int adaptorId, String adaptorName, ImmutableParamMap<SimAdaptorParams> paramMap) {
		super(adaptorId, adaptorName);
		this.paramMap = paramMap;
		SocketConfigurator mdConfigurator = SocketConfigurator.builder()
				.host(paramMap.getString(SimAdaptorParams.MdHost)).port(paramMap.getInt(SimAdaptorParams.MdPort))
				.build();
		SocketConfigurator tdConfigurator = SocketConfigurator.builder()
				.host(paramMap.getString(SimAdaptorParams.TdHost)).port(paramMap.getInt(SimAdaptorParams.TdPort))
				.build();
		this.mdSender = new SocketSender(mdConfigurator);
		this.tdSender = new SocketSender(tdConfigurator);

	}

	@Override
	public String adaptorName() {
		return "SimOutboundAdaptor$" + this.hashCode();
	}

	public boolean newOredr(ChildOrder order) {
		io.redstone.persistence.avro.entity.Order simOrder = io.redstone.persistence.avro.entity.Order.newBuilder()
				.setOrderRef(Long.valueOf(order.ordSysId()).intValue()).setInstrumentId(order.instrument().code())
				.setLimitPrice(order.ordPrice().offerPrice())
				.setVolumeTotalOriginal(Double.valueOf(order.ordQty().offerQty()).intValue())
				.setOrderStatus(OrdStatus.PendingNew.code()).setDirection(order.ordSide().code()).build();
		byte[] byteMsg;
		try {
			byteMsg = simOrder.toByteBuffer().array();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		tdSender.send(byteMsg);
		return true;
	}

	public boolean cancelOrder(ChildOrder order) {
		Order cancelOrder = OrderKeeper.getOrder(order.ordSysId());
		io.redstone.persistence.avro.entity.Order simOrder = io.redstone.persistence.avro.entity.Order.newBuilder()
				.setOrderRef(Long.valueOf(order.ordSysId()).intValue()).setInstrumentId(cancelOrder.instrument().code())
				.setLimitPrice(order.ordPrice().offerPrice())
				.setVolumeTotalOriginal(Double.valueOf(order.ordQty().offerQty()).intValue())
				.setOrderStatus(OrdStatus.PendingCancel.code()).setDirection(cancelOrder.ordSide().code()).build();
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
	public boolean subscribeMarketData(SubscribeMarketData subscribe) {
		MarketDataSubscribe simSubscribe = MarketDataSubscribe.newBuilder().setUniqueId(Integer.valueOf(1))
				.setStartTradingDay(paramMap.getString(SimAdaptorParams.TradingDayStart))
				.setEndTradingDay(paramMap.getString(SimAdaptorParams.TradingDayEnd))
				.setInstrumentIdList(subscribe.getInstrumentSet().stream().map(instrument -> instrument.code())
						.collect(Collectors.toList()))
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
	public boolean close() {
		mdSender.destroy();
		tdSender.destroy();
		return false;
	}

	@Override
	public boolean queryPositions(InvestorAccount account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean queryBalance(InvestorAccount account) {
		// TODO Auto-generated method stub
		return false;
	}

}
