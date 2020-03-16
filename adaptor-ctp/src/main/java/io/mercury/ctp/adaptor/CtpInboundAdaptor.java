package io.mercury.ctp.adaptor;

import static io.mercury.polaris.financial.util.PriceUtil.priceToLong4;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcOrderActionField;
import io.mercury.common.concurrent.queue.MpscArrayBlockingQueue;
import io.mercury.common.datetime.Pattern.DatePattern;
import io.mercury.common.datetime.Pattern.TimePattern;
import io.mercury.common.datetime.TimeConst;
import io.mercury.common.datetime.TimeZones;
import io.mercury.common.functional.Converter;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.param.ImmutableParamMap;
import io.mercury.ctp.adaptor.exception.OrderRefNotFoundException;
import io.mercury.ctp.adaptor.utils.CtpOrderRefKeeper;
import io.mercury.ctp.gateway.CtpGateway;
import io.mercury.ctp.gateway.bean.config.CtpConnectionInfo;
import io.mercury.ctp.gateway.bean.rsp.RspDepthMarketData;
import io.mercury.ctp.gateway.bean.rsp.RspOrderAction;
import io.mercury.ctp.gateway.bean.rsp.RspOrderInsert;
import io.mercury.ctp.gateway.bean.rsp.RtnOrder;
import io.mercury.ctp.gateway.bean.rsp.RtnTrade;
import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.instrument.InstrumentKeeper;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.redstone.core.adaptor.base.InboundAdaptor;
import io.redstone.core.order.structure.OrdReport;
import io.redstone.core.strategy.StrategyScheduler;

public class CtpInboundAdaptor extends InboundAdaptor {

	private final Logger logger = CommonLoggerFactory.getLogger(getClass());

	private final DateTimeFormatter updateTimeformatter = TimePattern.HH_MM_SS.newFormatter();

	private final DateTimeFormatter actionDayformatter = DatePattern.YYYYMMDD.newFormatter();

	private Function<RspDepthMarketData, BasicMarketData> marketDataConverter = (
			RspDepthMarketData depthMarketData) -> {

		LocalDate depthDate = LocalDate.parse(depthMarketData.getActionDay(), actionDayformatter);
		LocalTime depthTime = LocalTime.parse(depthMarketData.getUpdateTime(), updateTimeformatter)
				.plusNanos(depthMarketData.getUpdateMillisec() * TimeConst.NANOS_PER_MILLIS);

		Instrument instrument = InstrumentKeeper.getInstrument(depthMarketData.getInstrumentID());
		logger.info("Convert depthMarketData -> InstrumentCode==[{}], depthDate==[{}], depthTime==[{}]",
				instrument.code(), depthDate, depthTime);

		return BasicMarketData
				.with(instrument, ZonedDateTime.of(depthDate, depthTime, TimeZones.CST),
						priceToLong4(depthMarketData.getLastPrice()), depthMarketData.getVolume(),
						priceToLong4(depthMarketData.getTurnover()))
				.setBidPrice1(priceToLong4(depthMarketData.getBidPrice1()))
				.setBidVolume1(depthMarketData.getBidVolume1())
				.setAskPrice1(priceToLong4(depthMarketData.getAskPrice1()))
				.setAskVolume1(depthMarketData.getAskVolume1());
	};

	private Converter<RtnOrder, OrdReport> rtnOrderConverter = (from, to) -> {
		// TODO Auto-generated method stub
		return to;
	};

	private Converter<RtnTrade, OrdReport> rtnTradeConverter = (from, to) -> {
		// TODO Auto-generated method stub
		return to;
	};

	private final CtpGateway gateway;

	public CtpInboundAdaptor(int adaptorId, String adaptorName, StrategyScheduler scheduler,
			ImmutableParamMap<CtpAdaptorParams> paramMap) {
		super(adaptorId, adaptorName);
		// 写入Gateway用户信息
		CtpConnectionInfo userInfo = CtpConnectionInfo.newEmpty()
				.setTraderAddress(paramMap.getString(CtpAdaptorParams.CTP_Trader_Address))
				.setMdAddress(paramMap.getString(CtpAdaptorParams.CTP_Md_Address))
				.setBrokerId(paramMap.getString(CtpAdaptorParams.CTP_BrokerId))
				.setInvestorId(paramMap.getString(CtpAdaptorParams.CTP_InvestorId))
				.setUserId(paramMap.getString(CtpAdaptorParams.CTP_UserId))
				.setAccountId(paramMap.getString(CtpAdaptorParams.CTP_AccountId))
				.setPassword(paramMap.getString(CtpAdaptorParams.CTP_Password));
		// 初始化Gateway

		this.gateway = new CtpGateway("Jctp-Gateway", userInfo,
				MpscArrayBlockingQueue.autoStartQueue("Gateway-Handle-Queue", 1024, msg -> {
					switch (msg.getType()) {
					case DepthMarketData:
						BasicMarketData marketData = marketDataConverter.apply(msg.getRspDepthMarketData());
						scheduler.onMarketData(marketData);
						break;
					case RtnOrder:
						RtnOrder ctpRtnOrder = msg.getRtnOrder();
						OrdReport rtnOrder = checkoutCtpOrder(ctpRtnOrder.getOrderRef());
						scheduler.onOrderReport(rtnOrderConverter.conversion(ctpRtnOrder, rtnOrder));
						break;
					case RtnTrade:
						RtnTrade ctpRtnTrade = msg.getRtnTrade();
						OrdReport rtnTrade = checkoutCtpOrder(ctpRtnTrade.getOrderRef());
						scheduler.onOrderReport(rtnTradeConverter.conversion(ctpRtnTrade, rtnTrade));
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

	private OrdReport checkoutCtpOrder(String orderRef) {
		try {
			long orderSysId = CtpOrderRefKeeper.getOrdSysId(orderRef);
			return new OrdReport().setOrdSysId(orderSysId);
		} catch (OrderRefNotFoundException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	public CtpGateway getGateway() {
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
