package io.ffreedom.redstone.adaptor.ctp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcOrderField;
import ctp.thostapi.CThostFtdcTradeField;
import io.ffreedom.common.functional.BeanSetter;
import io.ffreedom.common.functional.Converter;
import io.ffreedom.common.param.ParamMap;
import io.ffreedom.common.queue.impl.ArrayBlockingMPSCQueue;
import io.ffreedom.jctp.bean.rsp.RspMsg;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.adaptor.base.AdaptorParams;
import io.ffreedom.redstone.adaptor.ctp.converter.inbound.CtpInboundMarketDataConverter;
import io.ffreedom.redstone.adaptor.ctp.setter.CtpInboundRtnOrderSetter;
import io.ffreedom.redstone.adaptor.ctp.setter.CtpInboundRtnTradeSetter;
import io.ffreedom.redstone.core.adaptor.InboundAdaptor;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.order.storage.OrderKeeper;
import io.ffreedom.redstone.core.strategy.StrategyScheduler;
import io.ffreedom.transport.core.role.Receiver;
import io.ffreedom.transport.rabbitmq.config.RmqReceiverConfigurator;

public class CtpInboundAdaptor implements InboundAdaptor {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private Receiver inboundReceiver;

	private Converter<CThostFtdcDepthMarketDataField, BasicMarketData> marketDataConverter = new CtpInboundMarketDataConverter();

	private BeanSetter<CThostFtdcOrderField, Order> rtnOrderSetter = new CtpInboundRtnOrderSetter();

	private BeanSetter<CThostFtdcTradeField, Order> rtnTradeSetter = new CtpInboundRtnTradeSetter();

	public CtpInboundAdaptor(StrategyScheduler scheduler, ParamMap<AdaptorParams> paramMap) {
		RmqReceiverConfigurator configurator = RmqReceiverConfigurator.configuration()
				.setConnectionParam(paramMap.getString(AdaptorParams.CTP_MQ_HOST),
						paramMap.getInteger(AdaptorParams.CTP_MQ_PORT))
				.setUserParam(paramMap.getString(AdaptorParams.CTP_MQ_USERNAME),
						paramMap.getString(AdaptorParams.CTP_MQ_PASSWORD))
				.setReceiveQueue(paramMap.getString(AdaptorParams.CTP_QNAME_INBOUND)).setAutomaticRecovery(true);

		ArrayBlockingMPSCQueue.autoRunQueue("Simnow-Handle-Queue", 1024, (RspMsg msg) -> {
			// Subscriber callback function
			switch (msg.getType()) {
			case DepthMarketData:
				BasicMarketData marketData = marketDataConverter.convert(msg.getDepthMarketData());
				scheduler.onMarketData(marketData);
				break;
			case RtnOrder:
				CThostFtdcOrderField ctpRtnOrder = msg.getRtnOrder();
				Order rtnOrder = checkoutCtpOrder(ctpRtnOrder.getOrderRef());
				rtnOrderSetter.setBean(ctpRtnOrder, rtnOrder);
				scheduler.onOrder(rtnOrder);
				break;
			case RtnTrade:
				CThostFtdcTradeField ctpRtnTrade = msg.getRtnTrade();
				Order rtnTrade = checkoutCtpOrder(ctpRtnTrade.getOrderRef());
				rtnTradeSetter.setBean(ctpRtnTrade, rtnTrade);
				scheduler.onOrder(rtnTrade);
				break;

			default:
				break;
			}
		});
		init();
	}

	private Order checkoutCtpOrder(String orderRef) {
		try {
			Long orderSysId = CtpOrderRefLogger.getOrdSysId(orderRef);
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
		try {
			inboundReceiver.receive();
			return true;
		} catch (Exception e) {
			return false;
		}
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
