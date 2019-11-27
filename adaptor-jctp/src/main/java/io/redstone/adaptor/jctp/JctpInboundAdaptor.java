package io.redstone.adaptor.jctp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcOrderActionField;
import io.ffreedom.common.concurrent.queue.MpscArrayBlockingQueue;
import io.ffreedom.common.datetime.Pattern.DatePattern;
import io.ffreedom.common.datetime.Pattern.TimePattern;
import io.ffreedom.common.datetime.TimeZones;
import io.ffreedom.common.functional.ValueTransferer;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.common.param.ParamKeyMap;
import io.ffreedom.jctp.JctpGateway;
import io.ffreedom.jctp.bean.config.JctpUserInfo;
import io.ffreedom.jctp.bean.rsp.RspDepthMarketData;
import io.ffreedom.jctp.bean.rsp.RspMsg;
import io.ffreedom.jctp.bean.rsp.RspOrderAction;
import io.ffreedom.jctp.bean.rsp.RspOrderInsert;
import io.ffreedom.jctp.bean.rsp.RtnOrder;
import io.ffreedom.jctp.bean.rsp.RtnTrade;
import io.polaris.financial.instrument.Instrument;
import io.polaris.financial.market.impl.BasicMarketData;
import io.redstone.adaptor.jctp.exception.OrderRefNotFoundException;
import io.redstone.adaptor.jctp.utils.JctpOrderRefKeeper;
import io.redstone.core.adaptor.impl.InboundAdaptor;
import io.redstone.core.order.impl.OrderReport;
import io.redstone.core.strategy.StrategyScheduler;
import io.redstone.engine.storage.InstrumentKeeper;

public class JctpInboundAdaptor extends InboundAdaptor {

	private final Logger logger = CommonLoggerFactory.getLogger(getClass());

	private final DateTimeFormatter updateTimeformatter = TimePattern.HH_MM_SS.newFormatter();

	private final DateTimeFormatter actionDayformatter = DatePattern.YYYYMMDD.newFormatter();

	private Function<RspDepthMarketData, BasicMarketData> marketDataConverter = (
			RspDepthMarketData depthMarketData) -> {
		LocalDate depthDate = LocalDate.parse(depthMarketData.getActionDay(), actionDayformatter);
		LocalTime depthTime = LocalTime.parse(depthMarketData.getUpdateTime(), updateTimeformatter)
				.plusNanos(depthMarketData.getUpdateMillisec() * 1000_000);
		Instrument instrument = InstrumentKeeper.getInstrument(depthMarketData.getInstrumentID());
		logger.info("Convert depthMarketData -> InstrumentCode==[{}], depthDate==[{}], depthTime==[{}]",
				instrument.getInstrumentCode(), depthDate, depthTime);
		return BasicMarketData.of(instrument, ZonedDateTime.of(depthDate, depthTime, TimeZones.CST))
				.setLastPrice(depthMarketData.getLastPrice()).setVolume(depthMarketData.getVolume())
				.setTurnover(depthMarketData.getTurnover()).setBidPrice1(depthMarketData.getBidPrice1())
				.setBidVolume1(depthMarketData.getBidVolume1()).setAskPrice1(depthMarketData.getAskPrice1())
				.setAskVolume1(depthMarketData.getAskVolume1());
	};

	private ValueTransferer<RtnOrder, OrderReport> rtnOrderTransferer = (from, to) -> {
		// TODO Auto-generated method stub
		return to;
	};

	private ValueTransferer<RtnTrade, OrderReport> rtnTradeTransferer = (from, to) -> {
		// TODO Auto-generated method stub
		return to;
	};

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
				MpscArrayBlockingQueue.autoStartQueue("Gateway-Handle-Queue", 1024, (RspMsg msg) -> {
					switch (msg.getType()) {
					case DepthMarketData:
						BasicMarketData marketData = marketDataConverter.apply(msg.getRspDepthMarketData());
						scheduler.onMarketData(marketData);
						break;
					case RtnOrder:
						RtnOrder ctpRtnOrder = msg.getRtnOrder();
						OrderReport rtnOrder = checkoutCtpOrder(ctpRtnOrder.getOrderRef());
						scheduler.onOrderReport(rtnOrderTransferer.transfer(ctpRtnOrder, rtnOrder));
						break;
					case RtnTrade:
						RtnTrade ctpRtnTrade = msg.getRtnTrade();
						OrderReport rtnTrade = checkoutCtpOrder(ctpRtnTrade.getOrderRef());
						scheduler.onOrderReport(rtnTradeTransferer.transfer(ctpRtnTrade, rtnTrade));
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
