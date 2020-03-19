package io.mercury.ctp.adaptor;

import static io.mercury.polaris.financial.util.PriceUtil.priceToLong4;

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
import io.mercury.common.datetime.TimeZones;
import io.mercury.common.functional.Converter;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.param.ImmutableParamMap;
import io.mercury.ctp.adaptor.exception.OrderRefNotFoundException;
import io.mercury.ctp.adaptor.utils.CtpOrderRefGenerate;
import io.mercury.ctp.adaptor.utils.CtpOrderRefKeeper;
import io.mercury.ctp.gateway.CtpGateway;
import io.mercury.ctp.gateway.bean.config.CtpConfigInfo;
import io.mercury.ctp.gateway.bean.rsp.RspDepthMarketData;
import io.mercury.ctp.gateway.bean.rsp.RspOrderAction;
import io.mercury.ctp.gateway.bean.rsp.RspOrderInsert;
import io.mercury.ctp.gateway.bean.rsp.RtnOrder;
import io.mercury.ctp.gateway.bean.rsp.RtnTrade;
import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.instrument.InstrumentKeeper;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.redstone.core.account.Account;
import io.redstone.core.adaptor.base.BaseAdaptor;
import io.redstone.core.order.Order;
import io.redstone.core.order.impl.ChildOrder;
import io.redstone.core.order.structure.OrdReport;
import io.redstone.core.strategy.StrategyScheduler;

public class CtpAdaptor extends BaseAdaptor {

	private static final Logger log = CommonLoggerFactory.getLogger(CtpAdaptor.class);

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

		return new BasicMarketData(instrument, ZonedDateTime.of(depthDate, depthTime, TimeZones.CST),
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
	private final CtpGateway gateway;

	public CtpAdaptor(int adaptorId, String adaptorName, int appId, @Nonnull StrategyScheduler scheduler,
			@Nonnull ImmutableParamMap<CtpAdaptorParam> paramMap) {
		super(adaptorId, adaptorName);

		// 写入Gateway用户信息
		CtpConfigInfo configInfo = new CtpConfigInfo()
				.setTraderAddress(paramMap.getString(CtpAdaptorParam.CTP_Trader_Address))
				.setMdAddress(paramMap.getString(CtpAdaptorParam.CTP_Md_Address))
				.setBrokerId(paramMap.getString(CtpAdaptorParam.CTP_BrokerId))
				.setInvestorId(paramMap.getString(CtpAdaptorParam.CTP_InvestorId))
				.setUserId(paramMap.getString(CtpAdaptorParam.CTP_UserId))
				.setAccountId(paramMap.getString(CtpAdaptorParam.CTP_AccountId))
				.setPassword(paramMap.getString(CtpAdaptorParam.CTP_Password));
		this.appId = appId;
		// 初始化Gateway
		this.gateway = new CtpGateway("CTP-Gateway", configInfo,
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
			long orderSysId = CtpOrderRefKeeper.getOrdSysId(orderRef);
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

	private Function<Order, CThostFtdcInputOrderField> newOrderFunction = order -> {
		int orderRef = CtpOrderRefGenerate.next(appId);
		char direction;
		switch (order.ordSide().direction()) {
		case Long:
			direction = 0;
			break;
		case Short:
			direction = 1;
			break;
		default:
			throw new RuntimeException(order.ordSide() + " does not exist.");
		}
		CThostFtdcInputOrderField inputOrderField = new CThostFtdcInputOrderField();
		inputOrderField.setInstrumentID(order.instrument().code());
		inputOrderField.setOrderRef(Integer.toString(orderRef));
		inputOrderField.setDirection(direction);
		inputOrderField.setLimitPrice(order.ordPrice().offerPrice());
		inputOrderField.setVolumeTotalOriginal(Double.valueOf(order.ordQty().offerQty()).intValue());
		return inputOrderField;
	};

	@Override
	public boolean newOredr(ChildOrder order) {
		try {
			CThostFtdcInputOrderField ctpNewOrder = newOrderFunction.apply(order);
			CtpOrderRefKeeper.put(ctpNewOrder.getOrderRef(), order.ordSysId());
			gateway.newOrder(ctpNewOrder);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	private Function<Order, CThostFtdcInputOrderActionField> cancelOrderFunction = order -> {

		CThostFtdcInputOrderActionField inputOrderActionField = new CThostFtdcInputOrderActionField();
		return inputOrderActionField;

	};

	@Override
	public boolean cancelOrder(ChildOrder order) {
		try {
			CThostFtdcInputOrderActionField ctpCancelOrder = cancelOrderFunction.apply(order);
			String orderRef = CtpOrderRefKeeper.getOrderRef(order.ordSysId());
			ctpCancelOrder.setOrderRef(orderRef);
			gateway.cancelOrder(ctpCancelOrder);
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
			gateway.qureyPosition();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean queryBalance(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub

	}

}
