package io.ffreedom.redstone.adaptor.ctp;

import java.util.Collection;

import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.functional.Converter;
import io.ffreedom.common.param.ParamMap;
import io.ffreedom.persistence.json.serializable.JsonSerializationUtil;
import io.ffreedom.redstone.adaptor.base.AdaptorParams;
import io.ffreedom.redstone.adaptor.ctp.converter.outbound.CtpOutboundCancelOrderConverter;
import io.ffreedom.redstone.adaptor.ctp.converter.outbound.CtpOutboundNewOrderConverter;
import io.ffreedom.redstone.adaptor.ctp.dto.outbound.CtpOutboundCancelOrder;
import io.ffreedom.redstone.adaptor.ctp.dto.outbound.CtpOutboundMsg;
import io.ffreedom.redstone.adaptor.ctp.dto.outbound.CtpOutboundNewOrder;
import io.ffreedom.redstone.adaptor.ctp.dto.outbound.CtpOutboundTitle;
import io.ffreedom.redstone.core.adaptor.OutboundAdaptor;
import io.ffreedom.redstone.core.adaptor.dto.QueryPositions;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.transport.base.role.Publisher;
import io.ffreedom.transport.rabbitmq.RabbitMqPublisher;
import io.ffreedom.transport.rabbitmq.config.RmqPublisherConfigurator;

public class CtpOutboundAdaptor implements OutboundAdaptor {

	private Publisher<byte[]> outboundPublisher;

	private Converter<Order, CtpOutboundNewOrder> newOrderConverter = new CtpOutboundNewOrderConverter();

	private Converter<Order, CtpOutboundCancelOrder> cancelOrderConverter = new CtpOutboundCancelOrderConverter();

	public CtpOutboundAdaptor(ParamMap<AdaptorParams> paramMap) {
		RmqPublisherConfigurator configurator = RmqPublisherConfigurator.configuration()
				.setConnectionParam(paramMap.getString(AdaptorParams.CTP_MQ_HOST),
						paramMap.getInteger(AdaptorParams.CTP_MQ_PORT))
				.setUserParam(paramMap.getString(AdaptorParams.CTP_MQ_USERNAME),
						paramMap.getString(AdaptorParams.CTP_MQ_PASSWORD))
				.setModeDirect(paramMap.getString(AdaptorParams.CTP_QNAME_OUTBOUND)).setAutomaticRecovery(true);

		outboundPublisher = new RabbitMqPublisher("CTP_OUTBOUND", configurator);
		init();
	}

	@Override
	public void init() {

	}

	@Override
	public String getAdaptorName() {
		return "Ctp_Outbound_Adaptor";
	}

	@Override
	public int getAdaptorId() {
		return 0;
	}

	@Override
	public boolean close() {
		outboundPublisher.destroy();
		return false;
	}

	@Override
	public boolean newOredr(Order order) {
		CtpOutboundNewOrder ctpNewOrder = newOrderConverter.convert(order);
		CtpOrderRefLogger.INSTANCE.put(ctpNewOrder.getOrderRef(), order.getOrdSysId());
		sendMsg(new CtpOutboundMsg(CtpOutboundTitle.NewOrder, JsonSerializationUtil.objToJson(ctpNewOrder)));
		return true;
	}

	@Override
	public boolean cancelOrder(Order order) {
		try {
			Integer orderRef = CtpOrderRefLogger.INSTANCE.getOrderRef(order.getOrdSysId());
			CtpOutboundCancelOrder ctpCancelOrder = cancelOrderConverter.convert(order);
			ctpCancelOrder.setOrderRef(orderRef);
			sendMsg(new CtpOutboundMsg(CtpOutboundTitle.CancelOrder, JsonSerializationUtil.objToJson(ctpCancelOrder)));
			return true;
		} catch (CtpOrderRefNotFoundException e) {
			// TODO log
			throw new RuntimeException(e);
		}
	}

	private void sendMsg(CtpOutboundMsg msg) {
		outboundPublisher.publish(JsonSerializationUtil.objToJson(msg).getBytes(Charsets.UTF8));
	}

	@Override
	public Collection<Order> queryPositions(QueryPositions queryPositions) {
		// TODO Auto-generated method stub
		return null;
	}

}
