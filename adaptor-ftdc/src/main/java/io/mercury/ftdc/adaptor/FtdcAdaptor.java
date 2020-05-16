package io.mercury.ftdc.adaptor;

import static io.mercury.common.thread.ThreadHelper.sleep;
import static io.mercury.common.thread.ThreadHelper.startNewThread;
import static io.mercury.financial.instrument.PriceMultiplier.PriceSupporter.priceToLong4;

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
import io.mercury.common.concurrent.queue.MpscArrayBlockingQueue;
import io.mercury.common.datetime.Pattern.DatePattern;
import io.mercury.common.datetime.Pattern.TimePattern;
import io.mercury.common.datetime.TimeConst;
import io.mercury.common.datetime.TimeZone;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.param.map.ImmutableParamMap;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.ftdc.adaptor.converter.CancelOrderConverter;
import io.mercury.ftdc.adaptor.converter.NewOrderConverter;
import io.mercury.ftdc.adaptor.exception.OrderRefNotFoundException;
import io.mercury.ftdc.gateway.FtdcConfig;
import io.mercury.ftdc.gateway.FtdcGateway;
import io.mercury.ftdc.gateway.bean.FtdcDepthMarketData;
import io.mercury.ftdc.gateway.bean.FtdcInputOrder;
import io.mercury.ftdc.gateway.bean.FtdcInputOrderAction;
import io.mercury.ftdc.gateway.bean.FtdcOrder;
import io.mercury.ftdc.gateway.bean.FtdcOrderAction;
import io.mercury.ftdc.gateway.bean.FtdcTrade;
import io.mercury.ftdc.gateway.bean.RspMdConnect;
import io.mercury.ftdc.gateway.bean.RspTraderConnect;
import io.mercury.redstone.core.account.Account;
import io.mercury.redstone.core.adaptor.AdaptorEvent;
import io.mercury.redstone.core.adaptor.base.AdaptorBaseImpl;
import io.mercury.redstone.core.keeper.InstrumentKeeper;
import io.mercury.redstone.core.order.OrdSysIdAllocator;
import io.mercury.redstone.core.order.Order;
import io.mercury.redstone.core.order.specific.ChildOrder;
import io.mercury.redstone.core.order.structure.OrdReport;
import io.mercury.redstone.core.strategy.StrategyScheduler;

public class FtdcAdaptor extends AdaptorBaseImpl {

	private static final Logger log = CommonLoggerFactory.getLogger(FtdcAdaptor.class);

	private final DateTimeFormatter updateTimeformatter = TimePattern.HH_MM_SS.newFormatter();

	private final DateTimeFormatter actionDayformatter = DatePattern.YYYYMMDD.newFormatter();

	private Function<FtdcDepthMarketData, BasicMarketData> marketDataConverter = depthMarketData -> {

		LocalDate depthDate = LocalDate.parse(depthMarketData.getActionDay(), actionDayformatter);
		LocalTime depthTime = LocalTime.parse(depthMarketData.getUpdateTime(), updateTimeformatter)
				.plusNanos(depthMarketData.getUpdateMillisec() * TimeConst.NANOS_PER_MILLIS);

		Instrument instrument = InstrumentKeeper.getInstrument(depthMarketData.getInstrumentID());
		log.info("Convert depthMarketData -> InstrumentCode==[{}], depthDate==[{}], depthTime==[{}]", instrument.code(),
				depthDate, depthTime);

		return new BasicMarketData(instrument, ZonedDateTime.of(depthDate, depthTime, TimeZone.CST),
				// TODO 修改价格转换模式
				priceToLong4(depthMarketData.getLastPrice()), depthMarketData.getVolume(),
				priceToLong4(depthMarketData.getTurnover())).setBidPrice1(priceToLong4(depthMarketData.getBidPrice1()))
						.setBidVolume1(depthMarketData.getBidVolume1())
						.setAskPrice1(priceToLong4(depthMarketData.getAskPrice1()))
						.setAskVolume1(depthMarketData.getAskVolume1());
	};

	// TODO 转换报单回报
	private Function<FtdcOrder, OrdReport> rtnOrderConverter = ftdcOrder -> {
		OrdReport ordReport = findCtpOrder(ftdcOrder.getOrderRef());

		ftdcOrder.getOrderRef();
		ftdcOrder.getVolumeTotal();
		ftdcOrder.getOrderStatus();

		return ordReport;
	};

	// TODO 转换报单回报
	private Function<FtdcTrade, OrdReport> rtnTradeConverter = ftdcTrade -> {
		OrdReport ordReport = findCtpOrder(ftdcTrade.getOrderRef());
		return ordReport;
	};

	private OrdReport findCtpOrder(String orderRef) {
		try {
			long ordSysId = OrderRefKeeper.getOrdSysId(orderRef);
			if (ordSysId == 0L) {
				// 处理其他来源的订单
				long thirdOrdSysId = OrdSysIdAllocator.allocateFromThird();
				log.info("Handle third order, allocate ");
				return new OrdReport(thirdOrdSysId);
			} else
				return new OrdReport(ordSysId);
		} catch (OrderRefNotFoundException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	private final FtdcGateway gateway;

	// TODO 两个int类型可以合并
	private volatile int frontId;
	private volatile int sessionId;

	private volatile boolean isMdAvailable;
	private volatile boolean isTraderAvailable;

	public FtdcAdaptor(int adaptorId, String adaptorName, Account account, @Nonnull StrategyScheduler scheduler,
			@Nonnull ImmutableParamMap<FtdcAdaptorParam> paramMap) {
		super(adaptorId, adaptorName, account);
		// 写入Gateway用户信息
		FtdcConfig configInfo = new FtdcConfig().setTraderAddr(paramMap.getString(FtdcAdaptorParam.CTP_TraderAddr))
				.setMdAddr(paramMap.getString(FtdcAdaptorParam.CTP_MdAddr))
				.setBrokerId(paramMap.getString(FtdcAdaptorParam.CTP_BrokerId))
				.setInvestorId(paramMap.getString(FtdcAdaptorParam.CTP_InvestorId))
				.setUserId(paramMap.getString(FtdcAdaptorParam.CTP_UserId))
				.setAccountId(paramMap.getString(FtdcAdaptorParam.CTP_AccountId))
				.setPassword(paramMap.getString(FtdcAdaptorParam.CTP_Password));

		// 初始化Gateway
		this.gateway = new FtdcGateway("Ftdc-Gateway", configInfo,
				MpscArrayBlockingQueue.autoStartQueue("Gateway-Handle-Queue", 256, ftdcMsg -> {
					switch (ftdcMsg.getRspType()) {
					case RspMdConnect:
						RspMdConnect rspMdConnect = ftdcMsg.getRspMdConnect();
						this.isMdAvailable = rspMdConnect.isAvailable();
						AdaptorEvent mdEvent = new AdaptorEvent(adaptorId);
						if (rspMdConnect.isAvailable()) {
							mdEvent.setAdaptorStatus(AdaptorStatus.MdEnable);
						} else {
							mdEvent.setAdaptorStatus(AdaptorStatus.MdDisable);
						}
						scheduler.onAdaptorEvent(mdEvent);
						break;
					case RspTraderConnect:
						RspTraderConnect traderConnect = ftdcMsg.getRspTraderConnect();
						this.frontId = traderConnect.getFrontID();
						this.sessionId = traderConnect.getSessionID();
						this.isTraderAvailable = traderConnect.isAvailable();
						AdaptorEvent traderEvent = new AdaptorEvent(adaptorId);
						if (traderConnect.isAvailable()) {
							traderEvent.setAdaptorStatus(AdaptorStatus.TraderDisable);
						} else {
							traderEvent.setAdaptorStatus(AdaptorStatus.TraderEnable);
						}
						scheduler.onAdaptorEvent(traderEvent);
						break;
					case FtdcDepthMarketData:
						// 行情处理
						BasicMarketData marketData = marketDataConverter.apply(ftdcMsg.getFtdcDepthMarketData());
						scheduler.onMarketData(marketData);
						break;
					case FtdcOrder:
						// 报单回报处理
						FtdcOrder ftdcOrder = ftdcMsg.getFtdcOrder();
						OrdReport rtnOrder = rtnOrderConverter.apply(ftdcOrder);
						scheduler.onOrdReport(rtnOrder);
						break;
					case FtdcTrade:
						// 成交回报处理
						FtdcTrade ftdcTrade = ftdcMsg.getFtdcTrade();
						OrdReport rtnTrade = rtnTradeConverter.apply(ftdcTrade);
						scheduler.onOrdReport(rtnTrade);
						break;
					case FtdcInputOrder:
						// 报单错误处理
						FtdcInputOrder ftdcInputOrder = ftdcMsg.getFtdcInputOrder();
						log.warn("");
						break;
					case FtdcInputOrderAction:
						// 撤单错误处理1
						FtdcInputOrderAction ftdcInputOrderAction = ftdcMsg.getFtdcInputOrderAction();
						log.warn("");
						break;
					case FtdcOrderAction:
						// 撤单错误处理2
						FtdcOrderAction errRtnOrderInsert = ftdcMsg.getFtdcOrderAction();
						log.warn("");
						break;
					default:
						break;
					}
				}));
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
			if (isMdAvailable) {
				gateway.SubscribeMarketData(Stream.of(instruments).map(Instrument::code).collect(Collectors.toSet()));
				return true;
			} else {
				return false;
			}
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
			String orderRef = Integer.toString(OrderRefGenerator.next(order.strategyId()));
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

	private Function<Order, CThostFtdcInputOrderActionField> cancelOrderConverter = new CancelOrderConverter();

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

	private final Object mutex = new Object();

	@Override
	public boolean queryOrder(Account account) {
		try {
			if (isTraderAvailable) {
				startNewThread(() -> {
					synchronized (mutex) {
						log.info("Ready to sent ReqQryInvestorPosition");
						sleep(1250);
						// TODO 写入ExchangeId
						gateway.ReqQryOrder("SHFE");
						log.info("Has been sent ReqQryInvestorPosition");
					}
				}, "QueryOrder-SubThread");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error("FtdcAdaptor :: queryOrder exception -> {}", e.getMessage(), e);
			return false;
		}
	}

	@Override
	public boolean queryPositions(Account account) {
		try {
			if (isTraderAvailable) {
				startNewThread(() -> {
					synchronized (mutex) {
						log.info("Ready to sent ReqQryInvestorPosition");
						sleep(1250);
						gateway.ReqQryInvestorPosition();
						log.info("Has been sent ReqQryInvestorPosition");
					}
				}, "QueryPositions-SubThread");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error("FtdcAdaptor :: queryPositions exception -> {}", e.getMessage(), e);
			return false;
		}
	}

	@Override
	public boolean queryBalance(Account account) {
		try {
			if (isTraderAvailable) {
				startNewThread(() -> {
					synchronized (mutex) {
						log.info("Ready to sent ReqQryTradingAccount");
						sleep(1250);
						gateway.ReqQryTradingAccount();
						log.info("Has been sent ReqQryTradingAccount");
					}
				}, "QueryBalance-SubThread");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error("FtdcAdaptor :: queryBalance exception -> {}", e.getMessage(), e);
			return false;
		}
	}

	@Override
	public void close() throws IOException {
		// TODO close adaptor
	}

}
