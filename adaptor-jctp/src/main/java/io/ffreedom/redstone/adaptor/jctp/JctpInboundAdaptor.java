package io.ffreedom.redstone.adaptor.jctp;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcOrderActionField;
import ctp.thostapi.CThostFtdcOrderField;
import ctp.thostapi.CThostFtdcTradeField;
import io.ffreedom.common.functional.BeanSetter;
import io.ffreedom.common.functional.Converter;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.common.param.ParamMap;
import io.ffreedom.common.queue.impl.ArrayBlockingMPSCQueue;
import io.ffreedom.jctp.JctpGateway;
import io.ffreedom.jctp.bean.CtpUserInfo;
import io.ffreedom.jctp.bean.rsp.RspMsg;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.adaptor.jctp.converter.inbound.CtpInboundMarketDataConverter;
import io.ffreedom.redstone.adaptor.jctp.exception.OrderRefNotFoundException;
import io.ffreedom.redstone.adaptor.jctp.setter.CtpInboundRtnOrderSetter;
import io.ffreedom.redstone.adaptor.jctp.setter.CtpInboundRtnTradeSetter;
import io.ffreedom.redstone.adaptor.jctp.utils.JctpOrderRefKeeper;
import io.ffreedom.redstone.core.adaptor.InboundAdaptor;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.order.storage.OrderKeeper;
import io.ffreedom.redstone.core.strategy.StrategyScheduler;

public class JctpInboundAdaptor extends InboundAdaptor {

	private static final Logger logger = CommonLoggerFactory.getLogger(JctpInboundAdaptor.class);

	private Converter<CThostFtdcDepthMarketDataField, BasicMarketData> marketDataConverter = new CtpInboundMarketDataConverter();

	private BeanSetter<CThostFtdcOrderField, Order> rtnOrderSetter = new CtpInboundRtnOrderSetter();

	private BeanSetter<CThostFtdcTradeField, Order> rtnTradeSetter = new CtpInboundRtnTradeSetter();

	private final JctpGateway gateway;

	public JctpInboundAdaptor(int adaptorId, String adaptorName, StrategyScheduler scheduler,
			ParamMap<JctpAdaptorParams> paramMap) {
		super(adaptorId, adaptorName);
		// 写入Gateway用户信息
		CtpUserInfo userInfo = CtpUserInfo.newEmpty()
				.setTraderAddress(paramMap.getString(JctpAdaptorParams.CTP_Trader_Address))
				.setMdAddress(paramMap.getString(JctpAdaptorParams.CTP_Md_Address))
				.setBrokerId(paramMap.getString(JctpAdaptorParams.CTP_BrokerId))
				.setInvestorId(paramMap.getString(JctpAdaptorParams.CTP_InvestorId))
				.setUserId(paramMap.getString(JctpAdaptorParams.CTP_UserId))
				.setAccountId(paramMap.getString(JctpAdaptorParams.CTP_AccountId))
				.setPassword(paramMap.getString(JctpAdaptorParams.CTP_Password));
		// 初始化Gateway
		this.gateway = new JctpGateway("Ctp-Gateway", userInfo,
				ArrayBlockingMPSCQueue.autoRunQueue("Gateway-Handle-Queue", 1024, (RspMsg msg) -> {
					switch (msg.getType()) {
					case DepthMarketData:
						BasicMarketData marketData = marketDataConverter.convert(msg.getDepthMarketData());
						scheduler.onMarketData(marketData);
						break;
					case RtnOrder:
						CThostFtdcOrderField ctpRtnOrder = msg.getRtnOrder();
						Order rtnOrder = checkoutCtpOrder(ctpRtnOrder.getOrderRef());
						rtnOrderSetter.setBean(ctpRtnOrder, rtnOrder);
						scheduler.onInboundOrder(rtnOrder);
						break;
					case RtnTrade:
						CThostFtdcTradeField ctpRtnTrade = msg.getRtnTrade();
						Order rtnTrade = checkoutCtpOrder(ctpRtnTrade.getOrderRef());
						rtnTradeSetter.setBean(ctpRtnTrade, rtnTrade);
						scheduler.onInboundOrder(rtnTrade);
						break;
					case RspOrderInsert:
						CThostFtdcInputOrderField rspOrderInsert = msg.getRspOrderInsert();
						break;
					case RspOrderAction:
						CThostFtdcInputOrderActionField rspOrderAction = msg.getRspOrderAction();
						break;
					case ErrRtnOrderInsert:
						CThostFtdcInputOrderField errRtnOrderInsert = msg.getErrRtnOrderInsert();
						break;
					case ErrRtnOrderAction:
						CThostFtdcOrderActionField errRtnOrderAction = msg.getErrRtnOrderAction();
						break;
					default:
						break;
					}
				}));
		init();
	}

	private Order checkoutCtpOrder(String orderRef) {
		try {
			Long orderSysId = JctpOrderRefKeeper.getOrdSysId(orderRef);
			return OrderKeeper.getOrder(orderSysId);
		} catch (OrderRefNotFoundException e) {
			logger.error(e.getMessage(), e);
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

	public JctpGateway getJctpGeteway() {
		return gateway;
	}

	@Override
	public boolean activate() {
		try {
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
		return true;
	}

	public static void main(String[] args) {
		logger.debug("dsfsdfsd");
	}

}
