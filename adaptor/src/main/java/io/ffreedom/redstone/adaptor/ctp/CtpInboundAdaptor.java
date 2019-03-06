package io.ffreedom.redstone.adaptor.ctp;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import io.ffreedom.common.charset.Charsets;
import io.ffreedom.common.functional.BeanSetter;
import io.ffreedom.common.functional.Converter;
import io.ffreedom.common.param.ParamMap;
import io.ffreedom.persistence.json.serializable.JsonSerializationUtil;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.adaptor.base.AdaptorParams;
import io.ffreedom.redstone.adaptor.ctp.converter.inbound.CtpInboundMarketDataConverter;
import io.ffreedom.redstone.adaptor.ctp.dto.inbound.CtpInboundMarketData;
import io.ffreedom.redstone.adaptor.ctp.dto.inbound.CtpInboundMsg;
import io.ffreedom.redstone.adaptor.ctp.dto.inbound.CtpInboundRtnOrder;
import io.ffreedom.redstone.adaptor.ctp.dto.inbound.CtpInboundRtnTrade;
import io.ffreedom.redstone.adaptor.ctp.setter.CtpInboundRtnOrderSetter;
import io.ffreedom.redstone.adaptor.ctp.setter.CtpInboundRtnTradeSetter;
import io.ffreedom.redstone.core.adaptor.InboundAdaptor;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.order.storage.OrderKeeper;
import io.ffreedom.redstone.core.strategy.StrategyScheduler;
import io.ffreedom.transport.core.role.Receiver;
import io.ffreedom.transport.rabbitmq.RabbitMqReceiver;
import io.ffreedom.transport.rabbitmq.config.RmqReceiverConfigurator;

public class CtpInboundAdaptor implements InboundAdaptor {

	private Receiver inboundReceiver;

	private Converter<CThostFtdcDepthMarketDataField, BasicMarketData> marketDataConverter = new CtpInboundMarketDataConverter();

	private BeanSetter<CtpInboundRtnOrder, Order> rtnOrderSetter = new CtpInboundRtnOrderSetter();

	private BeanSetter<CtpInboundRtnTrade, Order> rtnTradeSetter = new CtpInboundRtnTradeSetter();

	public CtpInboundAdaptor(StrategyScheduler scheduler, ParamMap<AdaptorParams> paramMap) {
		RmqReceiverConfigurator configurator = RmqReceiverConfigurator.configuration()
				.setConnectionParam(paramMap.getString(AdaptorParams.CTP_MQ_HOST),
						paramMap.getInteger(AdaptorParams.CTP_MQ_PORT))
				.setUserParam(paramMap.getString(AdaptorParams.CTP_MQ_USERNAME),
						paramMap.getString(AdaptorParams.CTP_MQ_PASSWORD))
				.setReceiveQueue(paramMap.getString(AdaptorParams.CTP_QNAME_INBOUND)).setAutomaticRecovery(true);

		inboundReceiver = new RabbitMqReceiver("CTP_INBOUND_QUEUE", configurator, (bytes) -> {
			// Subscriber callback function
			CtpInboundMsg msg = JsonSerializationUtil.jsonToObj(new String(bytes, Charsets.UTF8), CtpInboundMsg.class);
			switch (msg.getTitle()) {
			case MarketData:
				CtpInboundMarketData ctpMarketData = JsonSerializationUtil.jsonToObj(msg.getContent(),
						CtpInboundMarketData.class);
				BasicMarketData marketData = marketDataConverter.convert(ctpMarketData);
				scheduler.onMarketData(marketData);
				break;
			case RtnOrder:
				CtpInboundRtnOrder ctpRtnOrder = JsonSerializationUtil.jsonToObj(msg.getContent(),
						CtpInboundRtnOrder.class);
				Order rtnOrder = checkoutCtpOrder(ctpRtnOrder.getOrderRef());
				rtnOrderSetter.setBean(ctpRtnOrder, rtnOrder);
				scheduler.onOrder(rtnOrder);
				break;
			case RtnTrade:
				CtpInboundRtnTrade ctpRtnTrade = JsonSerializationUtil.jsonToObj(msg.getContent(),
						CtpInboundRtnTrade.class);
				Order rtnTrade = checkoutCtpOrder(ctpRtnTrade.getOrderRef());
				rtnTradeSetter.setBean(ctpRtnTrade, rtnTrade);
				scheduler.onOrder(rtnTrade);
				break;
			case Error:

				break;
			default:
				break;
			}
		});
		init();
	}

	private Order checkoutCtpOrder(Integer orderRef) {
		try {
			Long orderSysId = CtpOrderRefLogger.INSTANCE.getOrdSysId(orderRef);
			return OrderKeeper.getOrder(orderSysId);
		} catch (CtpOrderRefNotFoundException e) {
			// TODO Log
			throw new RuntimeException(e);
		}
	}

	@Override
	public void init() {

	}

	@Override
	public String getAdaptorName() {
		return null;
	}

	@Override
	public boolean activate() {
		inboundReceiver.receive();
		return false;
	}

	@Override
	public int getAdaptorId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean close() {
		inboundReceiver.destroy();
		return true;
	}

}
