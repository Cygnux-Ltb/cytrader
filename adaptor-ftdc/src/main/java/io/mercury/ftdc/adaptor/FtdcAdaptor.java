package io.mercury.ftdc.adaptor;

import static io.mercury.common.thread.ThreadHelper.sleep;
import static io.mercury.common.thread.ThreadHelper.startNewThread;

import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import io.mercury.common.concurrent.queue.MpscArrayBlockingQueue;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.param.map.ImmutableParamMap;
import io.mercury.financial.instrument.Exchange;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.ftdc.adaptor.converter.FtdcDepthMarketDataConverter;
import io.mercury.ftdc.adaptor.converter.FtdcInputOrderActionConverter;
import io.mercury.ftdc.adaptor.converter.FtdcInputOrderConverter;
import io.mercury.ftdc.adaptor.converter.FtdcOrderConverter;
import io.mercury.ftdc.adaptor.converter.FtdcTradeConverter;
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
import io.mercury.redstone.core.order.Order;
import io.mercury.redstone.core.order.specific.ChildOrder;
import io.mercury.redstone.core.order.structure.OrdReport;
import io.mercury.redstone.core.strategy.StrategyScheduler;
import io.mercury.serialization.json.JsonUtil;

public class FtdcAdaptor extends AdaptorBaseImpl {

	private final Logger log = CommonLoggerFactory.getLogger(getClass());

	/**
	 * 转换行情
	 */
	private final Function<FtdcDepthMarketData, BasicMarketData> ftdcDepthMarketDataConverter = new FtdcDepthMarketDataConverter();

	/**
	 * 转换报单回报
	 */
	private final Function<FtdcOrder, OrdReport> ftdcOrderConverter = new FtdcOrderConverter();

	/**
	 * 转换成交回报
	 */
	private final Function<FtdcTrade, OrdReport> ftdcTradeConverter = new FtdcTradeConverter();

	private final FtdcGateway ftdcGateway;

	// TODO 两个int类型可以合并
	private volatile int frontId;
	private volatile int sessionId;

	private volatile boolean isMdAvailable;
	private volatile boolean isTraderAvailable;

	private final StrategyScheduler scheduler;

	public FtdcAdaptor(int adaptorId, String adaptorName, Account account, @Nonnull StrategyScheduler scheduler,
			@Nonnull ImmutableParamMap<FtdcAdaptorParam> params) {
		super(adaptorId, adaptorName, account);
		this.scheduler = scheduler;
		// 创建配置信息
		FtdcConfig ftdcConfig = createFtdcConfig(params);
		// 创建Gateway
		this.ftdcGateway = createFtdcGateway(ftdcConfig);
		this.ftdcInputOrderConverter = new FtdcInputOrderConverter();
		this.ftdcInputOrderActionConverter = new FtdcInputOrderActionConverter();
	}

	/**
	 * 
	 * @param params
	 * @return
	 */
	private FtdcConfig createFtdcConfig(ImmutableParamMap<FtdcAdaptorParam> params) {
		return new FtdcConfig().setTraderAddr(params.getString(FtdcAdaptorParam.CTP_TraderAddr))
				.setMdAddr(params.getString(FtdcAdaptorParam.CTP_MdAddr))
				.setAppId(params.getString(FtdcAdaptorParam.CTP_AppId))
				.setBrokerId(params.getString(FtdcAdaptorParam.CTP_BrokerId))
				.setInvestorId(params.getString(FtdcAdaptorParam.CTP_InvestorId))
				.setAccountId(params.getString(FtdcAdaptorParam.CTP_AccountId))
				.setUserId(params.getString(FtdcAdaptorParam.CTP_UserId))
				.setPassword(params.getString(FtdcAdaptorParam.CTP_Password))
				.setAuthCode(params.getString(FtdcAdaptorParam.CTP_AuthCode))
				.setIpAddr(params.getString(FtdcAdaptorParam.CTP_IpAddr))
				.setMacAddr(params.getString(FtdcAdaptorParam.CTP_MacAddr));
	}

	/**
	 * 
	 * @param ftdcConfig
	 * @return
	 */
	private FtdcGateway createFtdcGateway(FtdcConfig ftdcConfig) {
		return new FtdcGateway("Ftdc-Gateway", ftdcConfig,
				MpscArrayBlockingQueue.autoStartQueue("FtdcGateway-Buffer", 256, ftdcMsg -> {
					switch (ftdcMsg.getRspType()) {
					case RspMdConnect:
						RspMdConnect rspMdConnect = ftdcMsg.getRspMdConnect();
						this.isMdAvailable = rspMdConnect.isAvailable();
						log.info("Queue Processor Handle RspMdConnect, isMdAvailable==[{}]", isMdAvailable);
						AdaptorEvent mdEvent = new AdaptorEvent(adaptorId());
						if (rspMdConnect.isAvailable()) {
							mdEvent.setAdaptorStatus(AdaptorStatus.MdEnable);
						} else {
							mdEvent.setAdaptorStatus(AdaptorStatus.MdDisable);
						}
						scheduler.onAdaptorEvent(mdEvent);
						break;
					case RspTraderConnect:
						RspTraderConnect traderConnect = ftdcMsg.getRspTraderConnect();
						this.isTraderAvailable = traderConnect.isAvailable();
						this.frontId = traderConnect.getFrontID();
						this.sessionId = traderConnect.getSessionID();
						log.info(
								"Queue Processor Handle RspTraderConnect, isTraderAvailable==[{}], frontId==[{}], sessionId==[{}]",
								isTraderAvailable, frontId, sessionId);
						AdaptorEvent traderEvent = new AdaptorEvent(adaptorId());
						if (traderConnect.isAvailable()) {
							traderEvent.setAdaptorStatus(AdaptorStatus.TraderEnable);
						} else {
							traderEvent.setAdaptorStatus(AdaptorStatus.TraderDisable);
						}
						scheduler.onAdaptorEvent(traderEvent);
						break;
					case FtdcDepthMarketData:
						// 行情处理
						BasicMarketData marketData = ftdcDepthMarketDataConverter
								.apply(ftdcMsg.getFtdcDepthMarketData());
						scheduler.onMarketData(marketData);
						break;
					case FtdcOrder:
						// 报单回报处理
						FtdcOrder ftdcOrder = ftdcMsg.getFtdcOrder();
						log.info(
								"Queue Processor Handle FtdcOrder, InstrumentID==[{}], InvestorID==[{}], OrderRef==[{}]",
								ftdcOrder.getInstrumentID(), ftdcOrder.getInvestorID(), ftdcOrder.getOrderRef());
						OrdReport rtnOrder = ftdcOrderConverter.apply(ftdcOrder);
						scheduler.onOrdReport(rtnOrder);
						break;
					case FtdcTrade:
						// 成交回报处理
						FtdcTrade ftdcTrade = ftdcMsg.getFtdcTrade();
						log.info(
								"Queue Processor Handle FtdcTrade, InstrumentID==[{}], InvestorID==[{}], OrderRef==[{}]",
								ftdcTrade.getInstrumentID(), ftdcTrade.getInvestorID(), ftdcTrade.getOrderRef());
						OrdReport rtnTrade = ftdcTradeConverter.apply(ftdcTrade);
						scheduler.onOrdReport(rtnTrade);
						break;
					case FtdcInputOrder:
						// 报单错误处理
						FtdcInputOrder ftdcInputOrder = ftdcMsg.getFtdcInputOrder();
						log.info("Queue Processor Handle FtdcInputOrder, FtdcInputOrder -> {}",
								JsonUtil.toJson(ftdcInputOrder));
						break;
					case FtdcInputOrderAction:
						// 撤单错误处理1
						FtdcInputOrderAction ftdcInputOrderAction = ftdcMsg.getFtdcInputOrderAction();
						log.info("Queue Processor Handle FtdcInputOrderAction, FtdcInputOrderAction -> {}",
								JsonUtil.toJson(ftdcInputOrderAction));
						break;
					case FtdcOrderAction:
						// 撤单错误处理2
						FtdcOrderAction ftdcOrderAction = ftdcMsg.getFtdcOrderAction();
						log.info("Queue Processor Handle FtdcOrderAction, FtdcOrderAction -> {}",
								JsonUtil.toJson(ftdcOrderAction));
						break;
					default:
						break;
					}
				}));
	}

	@Override
	public boolean startup() {
		try {
			ftdcGateway.initAndJoin();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean subscribeMarketData(Instrument... instruments) {
		try {
			if (isMdAvailable) {
				ftdcGateway
						.SubscribeMarketData(Stream.of(instruments).map(Instrument::code).collect(Collectors.toSet()));
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error("subscribeMarketData exception {}", e.getMessage(), e);
			return false;
		}
	}

	private final Function<Order, CThostFtdcInputOrderField> ftdcInputOrderConverter;

	@Override
	public boolean newOredr(ChildOrder order) {
		try {
			CThostFtdcInputOrderField ftdcInputOrder = ftdcInputOrderConverter.apply(order);
			String orderRef = Integer.toString(OrderRefGenerator.next(order.strategyId()));
			/**
			 * 设置OrderRef
			 */
			ftdcInputOrder.setOrderRef(orderRef);
			OrderRefKeeper.put(orderRef, order.ordSysId());
			ftdcGateway.ReqOrderInsert(ftdcInputOrder);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	private final Function<Order, CThostFtdcInputOrderActionField> ftdcInputOrderActionConverter;

	@Override
	public boolean cancelOrder(ChildOrder order) {
		try {
			CThostFtdcInputOrderActionField ftdcInputOrderAction = ftdcInputOrderActionConverter.apply(order);
			String orderRef = OrderRefKeeper.getOrderRef(order.ordSysId());

			ftdcInputOrderAction.setOrderRef(orderRef);
			ftdcGateway.ReqOrderAction(ftdcInputOrderAction);
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
						log.info("FtdcAdaptor :: Ready to sent ReqQryInvestorPosition, Waiting...");
						sleep(1250);
						ftdcGateway.ReqQryOrder(Exchange.SHFE.code());
						log.info("FtdcAdaptor :: Has been sent ReqQryInvestorPosition");
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
						log.info("FtdcAdaptor :: Ready to sent ReqQryInvestorPosition, Waiting...");
						sleep(1250);
						ftdcGateway.ReqQryInvestorPosition();
						log.info("FtdcAdaptor :: Has been sent ReqQryInvestorPosition");
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
						log.info("FtdcAdaptor :: Ready to sent ReqQryTradingAccount, Waiting...");
						sleep(1250);
						ftdcGateway.ReqQryTradingAccount();
						log.info("FtdcAdaptor :: Has been sent ReqQryTradingAccount");
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
