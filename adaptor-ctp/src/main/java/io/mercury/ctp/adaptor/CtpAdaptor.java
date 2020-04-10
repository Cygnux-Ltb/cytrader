package io.mercury.ftdc.adaptor;

import static io.mercury.financial.util.PriceUtil.priceToLong4;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcOrderActionField;
import io.mercury.common.concurrent.queue.MpscArrayBlockingQueue;
import io.mercury.common.datetime.Pattern.DatePattern;
import io.mercury.common.datetime.Pattern.TimePattern;
import io.mercury.common.datetime.TimeConst;
import io.mercury.common.datetime.TimeZone;
import io.mercury.common.functional.Converter;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.param.ImmutableParamMap;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.instrument.InstrumentKeeper;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.ftdc.adaptor.converter.NewOrderConverter;
import io.mercury.ftdc.adaptor.exception.OrderRefNotFoundException;
import io.mercury.ftdc.adaptor.utils.OrderRefGenerate;
import io.mercury.ftdc.adaptor.utils.OrderRefKeeper;
import io.mercury.ftdc.gateway.FtdcGateway;
import io.mercury.ftdc.gateway.bean.config.FtdcConfigInfo;
import io.mercury.ftdc.gateway.bean.rsp.RspDepthMarketData;
import io.mercury.ftdc.gateway.bean.rsp.RspOrderAction;
import io.mercury.ftdc.gateway.bean.rsp.RspOrderInsert;
import io.mercury.ftdc.gateway.bean.rsp.RtnOrder;
import io.mercury.ftdc.gateway.bean.rsp.RtnTrade;
import io.redstone.core.account.Account;
import io.redstone.core.adaptor.base.BaseAdaptor;
import io.redstone.core.order.Order;
import io.redstone.core.order.specific.ChildOrder;
import io.redstone.core.order.structure.OrdReport;
import io.redstone.core.strategy.StrategyScheduler;

public class FtdcAdaptor extends BaseAdaptor {

	private static final Logger log = CommonLoggerFactory.getLogger(FtdcAdaptor.class);

	private final DateTimeFormatter updateTimeformatter = TimePattern.HH_MM_SS.newFormatter();

	private final DateTimeFormatter actionDayformatter = DatePattern.YYYYMMDD.newFormatter();

	private Function<RspDepthMarketData, BasicMarketData> marketDataConverter = (
			RspDepthMarketData depthMarketData) -> {

		LocalDate depthDate = LocalDate.parse(depthMarketData.getActionDay(), actionDayformatter);
		LocalTime depthTime = LocalTime.parse(depthMarketData.getUpdateTime(), updateTimeformatter)
				.plusNanos(depthMarketData.getUpdateMillisec() * TimeConst.NANOS_PER_MILLIS);

		Instrument instrument = InstrumentKeeper.getInstrument(depthMarketData.getInstrumentID());
		log.info("Convert depthMarketData -> InstrumentCode==[{}], depthDate==[{}], depthTime==[{}]", instrument.code(),
				depthDate, depthTime);

		return new BasicMarketData(instrument, ZonedDateTime.of(depthDate, depthTime, TimeZone.CST),
				priceToLong4(depthMarketData.getLastPrice()), depthMarketData.getVolume(),
				priceToLong4(depthMarketData.getTurnover())).setBidPrice1(priceToLong4(depthMarketData.getBidPrice1()))
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

	private int appId;
	private final FtdcGateway gateway;

	public FtdcAdaptor(int adaptorId, String adaptorName, int appId, @Nonnull StrategyScheduler scheduler,
			@Nonnull ImmutableParamMap<FtdcAdaptorParam> paramMap) {
		super(adaptorId, adaptorName);
		this.appId = appId;
		// 写入Gateway用户信息
		FtdcConfigInfo configInfo = new FtdcConfigInfo()
				.setTraderAddr(paramMap.getString(FtdcAdaptorParam.CTP_TraderAddr))
				.setMdAddr(paramMap.getString(FtdcAdaptorParam.CTP_MdAddr))
				.setBrokerId(paramMap.getString(FtdcAdaptorParam.CTP_BrokerId))
				.setInvestorId(paramMap.getString(FtdcAdaptorParam.CTP_InvestorId))
				.setUserId(paramMap.getString(FtdcAdaptorParam.CTP_UserId))
				.setAccountId(paramMap.getString(FtdcAdaptorParam.CTP_AccountId))
				.setPassword(paramMap.getString(FtdcAdaptorParam.CTP_Password));
		// 初始化Gateway
		this.gateway = new FtdcGateway("Ftdc-Gateway", configInfo,
				MpscArrayBlockingQueue.autoStartQueue("Gateway-Handle-Queue", 1024, msg -> {
					switch (msg.type()) {
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
			long orderSysId = OrderRefKeeper.getOrdSysId(orderRef);
			return new OrdReport().setOrdSysId(orderSysId);
		} catch (OrderRefNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean startup() {
		try {
			gateway.initAndJoin();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean subscribeMarketData(Instrument... instruments) {
		try {
			gateway.subscribeMarketData(
					Stream.of(instruments).map(instrument -> instrument.code()).collect(Collectors.toSet()));
			return true;
		} catch (Exception e) {
			log.error("subscribeMarketData exception {}", e.getMessage(), e);
			return false;
		}
	}

	private Function<Order, CThostFtdcInputOrderField> newOrderConverter = new NewOrderConverter();

	@Override
	public boolean newOredr(ChildOrder order) {
		try {
			CThostFtdcInputOrderField ftdcInputOrder = newOrderConverter.apply(order);
			String orderRef = Integer.toString(OrderRefGenerate.next(appId));
			/**
			 * 设置OrderRef
			 */
			ftdcInputOrder.setOrderRef(orderRef);
			OrderRefKeeper.put(orderRef, order.ordSysId());
			gateway.ReqOrderInsert(ftdcInputOrder);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	private Function<Order, CThostFtdcInputOrderActionField> cancelOrderConverter = order -> {
		CThostFtdcInputOrderActionField ftdcInputOrderAction = new CThostFtdcInputOrderActionField();

		return ftdcInputOrderAction;
	};

	@Override
	public boolean cancelOrder(ChildOrder order) {
		try {
			CThostFtdcInputOrderActionField ftdcInputOrderAction = cancelOrderConverter.apply(order);
			String orderRef = OrderRefKeeper.getOrderRef(order.ordSysId());
			ftdcInputOrderAction.setOrderRef(orderRef);
			gateway.ReqOrderAction(ftdcInputOrderAction);
			return true;
		} catch (OrderRefNotFoundException e) {
			log.error(e.getMessage());
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean queryPositions(Account account) {
		try {
			gateway.ReqQryInvestorPosition();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean queryBalance(Account account) {
		try {
			gateway.ReqQryTradingAccount();
			return true;
		} catch (Exception e) {
			log.error("gatewayqueryBalance ", e);
			return false;
		}
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

}
