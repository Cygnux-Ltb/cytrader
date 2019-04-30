package io.ffreedom.redstone.adaptor.jctp;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcOrderActionField;
import io.ffreedom.common.functional.BiConverter;
import io.ffreedom.common.functional.Converter;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.common.param.ParamKeyMap;
import io.ffreedom.common.queue.impl.ArrayBlockingMPSCQueue;
import io.ffreedom.jctp.JctpGateway;
import io.ffreedom.jctp.bean.config.JctpUserInfo;
import io.ffreedom.jctp.bean.rsp.RspDepthMarketData;
import io.ffreedom.jctp.bean.rsp.RspMsg;
import io.ffreedom.jctp.bean.rsp.RspOrderAction;
import io.ffreedom.jctp.bean.rsp.RspOrderInsert;
import io.ffreedom.jctp.bean.rsp.RtnOrder;
import io.ffreedom.jctp.bean.rsp.RtnTrade;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.adaptor.jctp.converter.inbound.CtpInboundMarketDataConverter;
import io.ffreedom.redstone.adaptor.jctp.converter.inbound.CtpInboundRtnOrderBiConverter;
import io.ffreedom.redstone.adaptor.jctp.converter.inbound.CtpInboundRtnTradeBiConverter;
import io.ffreedom.redstone.adaptor.jctp.exception.OrderRefNotFoundException;
import io.ffreedom.redstone.adaptor.jctp.utils.JctpOrderRefKeeper;
import io.ffreedom.redstone.core.adaptor.impl.InboundAdaptor;
import io.ffreedom.redstone.core.order.impl.OrderReport;
import io.ffreedom.redstone.core.strategy.StrategyScheduler;

public class JctpInboundAdaptor extends InboundAdaptor {

	private final Logger logger = CommonLoggerFactory.getLogger(getClass());

	private Converter<RspDepthMarketData, BasicMarketData> marketDataConverter = new CtpInboundMarketDataConverter();

	private BiConverter<RtnOrder, OrderReport> rtnOrderConverter = new CtpInboundRtnOrderBiConverter();

	private BiConverter<RtnTrade, OrderReport> rtnTradeConverter = new CtpInboundRtnTradeBiConverter();

	private final JctpGateway gateway;

	public JctpInboundAdaptor(int adaptorId, String adaptorName, StrategyScheduler scheduler,
			ParamKeyMap<JctpAdaptorParams> paramMap) {
		super(adaptorId, adaptorName);
		// 写入Gateway用户信息
		JctpUserInfo userInfo = JctpUserInfo.newEmpty()
				.setTraderAddress(paramMap.getString(JctpAdaptorParams.CTP_Trader_Address))
				.setMdAddress(paramMap.getString(JctpAdaptorParams.CTP_Md_Address))
				.setBrokerId(paramMap.getString(JctpAdaptorParams.CTP_BrokerId))
				.setInvestorId(paramMap.getString(JctpAdaptorParams.CTP_InvestorId))
				.setUserId(paramMap.getString(JctpAdaptorParams.CTP_UserId))
				.setAccountId(paramMap.getString(JctpAdaptorParams.CTP_AccountId))
				.setPassword(paramMap.getString(JctpAdaptorParams.CTP_Password));
		// 初始化Gateway
		this.gateway = new JctpGateway("Jctp-Gateway", userInfo,
				ArrayBlockingMPSCQueue.autoStartQueue("Gateway-Handle-Queue", 1024, (RspMsg msg) -> {
					switch (msg.getType()) {
					case DepthMarketData:
						BasicMarketData marketData = marketDataConverter.convert(msg.getRspDepthMarketData());
						scheduler.onMarketData(marketData);
						break;
					case RtnOrder:
						RtnOrder ctpRtnOrder = msg.getRtnOrder();
						OrderReport rtnOrder = checkoutCtpOrder(ctpRtnOrder.getOrderRef());
						scheduler.onOrderReport(rtnOrderConverter.convertTo(ctpRtnOrder, rtnOrder));
						break;
					case RtnTrade:
						RtnTrade ctpRtnTrade = msg.getRtnTrade();
						OrderReport rtnTrade = checkoutCtpOrder(ctpRtnTrade.getOrderRef());
						scheduler.onOrderReport(rtnTradeConverter.convertTo(ctpRtnTrade, rtnTrade));
						break;
					case RspOrderInsert:
						RspOrderInsert rspOrderInsert = msg.getRspOrderInsert();
						break;
					case RspOrderAction:
						RspOrderAction rspOrderAction = msg.getRspOrderAction();
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
	}

	private OrderReport checkoutCtpOrder(String orderRef) {
		try {
			long orderSysId = JctpOrderRefKeeper.getOrdSysId(orderRef);
			return new OrderReport().setOrdSysId(orderSysId);
		} catch (OrderRefNotFoundException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public JctpGateway getJctpGeteway() {
		return gateway;
	}

	@Override
	public boolean activate() {
		try {
			gateway.initAndJoin();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean close() {
		return true;
	}

	public static void main(String[] args) {

	}

}
