package io.mercury.ftdc.adaptor;

import static io.mercury.common.thread.Threads.sleep;
import static io.mercury.common.thread.Threads.startNewThread;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import io.mercury.common.concurrent.queue.MpscArrayBlockingQueue;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.param.ImmutableParams;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.ftdc.adaptor.converter.FromFtdcDepthMarketData;
import io.mercury.ftdc.adaptor.converter.FromFtdcOrder;
import io.mercury.ftdc.adaptor.converter.FromFtdcTrade;
import io.mercury.ftdc.adaptor.converter.ToFtdcInputOrder;
import io.mercury.ftdc.adaptor.converter.ToFtdcInputOrderAction;
import io.mercury.ftdc.adaptor.exception.OrderRefNotFoundException;
import io.mercury.ftdc.gateway.FtdcConfig;
import io.mercury.ftdc.gateway.FtdcGateway;
import io.mercury.ftdc.gateway.bean.FtdcInputOrder;
import io.mercury.ftdc.gateway.bean.FtdcInputOrderAction;
import io.mercury.ftdc.gateway.bean.FtdcMdConnect;
import io.mercury.ftdc.gateway.bean.FtdcOrder;
import io.mercury.ftdc.gateway.bean.FtdcOrderAction;
import io.mercury.ftdc.gateway.bean.FtdcTrade;
import io.mercury.ftdc.gateway.bean.FtdcTraderConnect;
import io.mercury.redstone.core.EventScheduler;
import io.mercury.redstone.core.account.Account;
import io.mercury.redstone.core.adaptor.AdaptorBaseImpl;
import io.mercury.redstone.core.adaptor.AdaptorEvent;
import io.mercury.redstone.core.adaptor.AdaptorEvent.AdaptorStatus;
import io.mercury.redstone.core.adaptor.Command;
import io.mercury.redstone.core.order.ActualChildOrder;
import io.mercury.redstone.core.order.structure.OrdReport;
import io.mercury.serialization.json.JsonUtil;

public class FtdcAdaptor extends AdaptorBaseImpl<BasicMarketData> {

	private static final Logger log = CommonLoggerFactory.getLogger(FtdcAdaptor.class);

	/**
	 * 转换行情
	 */
	private final FromFtdcDepthMarketData fromFtdcDepthMarketData = new FromFtdcDepthMarketData();

	/**
	 * 转换报单回报
	 */
	private final FromFtdcOrder fromFtdcOrder = new FromFtdcOrder();

	/**
	 * 转换成交回报
	 */
	private final FromFtdcTrade fromFtdcTrade = new FromFtdcTrade();

	/**
	 * 
	 */
	private final FtdcGateway gateway;

	// TODO 两个int类型可以合并
	private volatile int frontId;
	private volatile int sessionId;

	private volatile boolean isMdAvailable;
	private volatile boolean isTraderAvailable;

	public FtdcAdaptor(int adaptorId, @Nonnull Account account, @Nonnull EventScheduler<BasicMarketData> scheduler,
			@Nonnull ImmutableParams<FtdcAdaptorParamKey> paramMap) {
		super(adaptorId, "FtdcAdaptor-Broker[ " + account.brokerName() + "]-InvestorId[" + account.investorId() + "]",
				scheduler, account);
		// 创建配置信息
		FtdcConfig ftdcConfig = createFtdcConfig(paramMap);
		// 创建Gateway
		this.gateway = createFtdcGateway(ftdcConfig);
		this.toFtdcInputOrder = new ToFtdcInputOrder();
		this.toFtdcInputOrderAction = new ToFtdcInputOrderAction();
	}

	/**
	 * 
	 * @param params
	 * @return
	 */
	private FtdcConfig createFtdcConfig(ImmutableParams<FtdcAdaptorParamKey> paramMap) {
		return new FtdcConfig().setTraderAddr(paramMap.getString(FtdcAdaptorParamKey.TraderAddr))
				.setMdAddr(paramMap.getString(FtdcAdaptorParamKey.MdAddr))
				.setAppId(paramMap.getString(FtdcAdaptorParamKey.AppId))
				.setBrokerId(paramMap.getString(FtdcAdaptorParamKey.BrokerId))
				.setInvestorId(paramMap.getString(FtdcAdaptorParamKey.InvestorId))
				.setAccountId(paramMap.getString(FtdcAdaptorParamKey.AccountId))
				.setUserId(paramMap.getString(FtdcAdaptorParamKey.UserId))
				.setPassword(paramMap.getString(FtdcAdaptorParamKey.Password))
				.setAuthCode(paramMap.getString(FtdcAdaptorParamKey.AuthCode))
				.setIpAddr(paramMap.getString(FtdcAdaptorParamKey.IpAddr))
				.setMacAddr(paramMap.getString(FtdcAdaptorParamKey.MacAddr))
				.setCurrencyId(paramMap.getString(FtdcAdaptorParamKey.CurrencyId));
	}

	/**
	 * 
	 * @param ftdcConfig
	 * @return
	 */
	private FtdcGateway createFtdcGateway(FtdcConfig config) {
		return new FtdcGateway("Ftdc-Gateway", config,
				MpscArrayBlockingQueue.autoStartQueue("FtdcGateway-Buffer", 256, ftdcRspMsg -> {
					switch (ftdcRspMsg.getRspType()) {
					case FtdcMdConnect:
						FtdcMdConnect mdConnect = ftdcRspMsg.getFtdcMdConnect();
						this.isMdAvailable = mdConnect.isAvailable();
						log.info("Swap Queue Processor Handle FtdcMdConnect, isMdAvailable==[{}]", isMdAvailable);
						AdaptorEvent mdEvent;
						if (mdConnect.isAvailable())
							mdEvent = new AdaptorEvent(adaptorId(), AdaptorStatus.MdEnable);
						else
							mdEvent = new AdaptorEvent(adaptorId(), AdaptorStatus.MdDisable);
						scheduler.onAdaptorEvent(mdEvent);
						break;
					case FtdcTraderConnect:
						FtdcTraderConnect traderConnect = ftdcRspMsg.getFtdcTraderConnect();
						this.isTraderAvailable = traderConnect.isAvailable();
						this.frontId = traderConnect.getFrontID();
						this.sessionId = traderConnect.getSessionID();
						log.info(
								"Swap Queue Processor Handle FtdcTraderConnect, isTraderAvailable==[{}], frontId==[{}], sessionId==[{}]",
								isTraderAvailable, frontId, sessionId);
						AdaptorEvent traderEvent;
						if (traderConnect.isAvailable())
							traderEvent = new AdaptorEvent(adaptorId(), AdaptorStatus.TraderEnable);
						else
							traderEvent = new AdaptorEvent(adaptorId(), AdaptorStatus.TraderDisable);
						scheduler.onAdaptorEvent(traderEvent);
						break;
					case FtdcDepthMarketData:
						// 行情处理
						BasicMarketData marketData = fromFtdcDepthMarketData.apply(ftdcRspMsg.getFtdcDepthMarketData());
						scheduler.onMarketData(marketData);
						break;
					case FtdcOrder:
						// 报单回报处理
						FtdcOrder ftdcOrder = ftdcRspMsg.getFtdcOrder();
						log.info(
								"Swap Queue Processor Handle FtdcOrder, InstrumentID==[{}], InvestorID==[{}], OrderRef==[{}]",
								ftdcOrder.getInstrumentID(), ftdcOrder.getInvestorID(), ftdcOrder.getOrderRef());
						OrdReport ordReport = fromFtdcOrder.apply(ftdcOrder);
						scheduler.onOrdReport(ordReport);
						break;
					case FtdcTrade:
						// TODO 成交回报处理
						FtdcTrade ftdcTrade = ftdcRspMsg.getFtdcTrade();
						log.info(
								"Swap Queue Processor Handle FtdcTrade, InstrumentID==[{}], InvestorID==[{}], OrderRef==[{}]",
								ftdcTrade.getInstrumentID(), ftdcTrade.getInvestorID(), ftdcTrade.getOrderRef());
						OrdReport trdReport = fromFtdcTrade.apply(ftdcTrade);
						scheduler.onOrdReport(trdReport);
						break;
					case FtdcInputOrder:
						// TODO 报单错误处理
						FtdcInputOrder ftdcInputOrder = ftdcRspMsg.getFtdcInputOrder();
						log.info("Swap Queue Processor Handle FtdcInputOrder, FtdcInputOrder -> {}",
								JsonUtil.toJson(ftdcInputOrder));
						break;
					case FtdcInputOrderAction:
						// TODO 撤单错误处理1
						FtdcInputOrderAction ftdcInputOrderAction = ftdcRspMsg.getFtdcInputOrderAction();
						log.info("Swap Queue Processor Handle FtdcInputOrderAction, FtdcInputOrderAction -> {}",
								JsonUtil.toJson(ftdcInputOrderAction));
						break;
					case FtdcOrderAction:
						// TODO 撤单错误处理2
						FtdcOrderAction ftdcOrderAction = ftdcRspMsg.getFtdcOrderAction();
						log.info("Swap Queue Processor Handle FtdcOrderAction, FtdcOrderAction -> {}",
								JsonUtil.toJson(ftdcOrderAction));
						break;
					default:
						break;
					}
				}));
	}

	@Override
	protected boolean startup0() {
		try {
			gateway.initAndJoin();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 订阅行情实现
	 */
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
			log.error("ftdcGateway.SubscribeMarketData exception -> {}", e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 订单转换为FTDC新订单
	 */
	private final ToFtdcInputOrder toFtdcInputOrder;

	@Override
	public boolean newOredr(Account account, ActualChildOrder order) {
		try {
			CThostFtdcInputOrderField ftdcInputOrder = toFtdcInputOrder.apply(order);
			String orderRef = Integer.toString(OrderRefGenerator.next(order.strategyId()));
			/**
			 * 设置OrderRef
			 */
			ftdcInputOrder.setOrderRef(orderRef);
			OrderRefKeeper.put(orderRef, order.uniqueId());
			gateway.ReqOrderInsert(ftdcInputOrder);
			return true;
		} catch (Exception e) {
			log.error("ftdcGateway.ReqOrderInsert exception -> {}", e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 订单转换为FTDC撤单
	 */
	private final ToFtdcInputOrderAction toFtdcInputOrderAction;

	@Override
	public boolean cancelOrder(Account account, ActualChildOrder order) {
		try {
			CThostFtdcInputOrderActionField ftdcInputOrderAction = toFtdcInputOrderAction.apply(order);
			String orderRef = OrderRefKeeper.getOrderRef(order.uniqueId());

			ftdcInputOrderAction.setOrderRef(orderRef);
			ftdcInputOrderAction.setOrderActionRef(OrderRefGenerator.next(order.strategyId()));
			gateway.ReqOrderAction(ftdcInputOrderAction);
			return true;
		} catch (OrderRefNotFoundException e) {
			log.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			log.error("ftdcGateway.ReqOrderAction exception -> {}", e.getMessage(), e);
			return false;
		}
	}

	private final Object mutex = new Object();

	@Override
	public boolean queryOrder(Account account, @Nonnull Instrument instrument) {
		try {
			if (isTraderAvailable) {
				startNewThread(() -> {
					synchronized (mutex) {
						log.info("FtdcAdaptor :: Ready to sent ReqQryInvestorPosition, Waiting...");
						sleep(1250);
						gateway.ReqQryOrder(instrument.symbol().exchange().code());
						log.info("FtdcAdaptor :: Has been sent ReqQryInvestorPosition");
					}
				}, "QueryOrder-SubThread");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error("ftdcGateway.ReqQryOrder exception -> {}", e.getMessage(), e);
			return false;
		}
	}

	@Override
	public boolean queryPositions(Account account, @Nonnull Instrument instrument) {
		try {
			if (isTraderAvailable) {
				startNewThread(() -> {
					synchronized (mutex) {
						log.info("FtdcAdaptor :: Ready to sent ReqQryInvestorPosition, Waiting...");
						sleep(1250);
						gateway.ReqQryInvestorPosition(instrument.symbol().exchange().code(), instrument.code());
						log.info("FtdcAdaptor :: Has been sent ReqQryInvestorPosition");
					}
				}, "QueryPositions-SubThread");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error("ftdcGateway.ReqQryInvestorPosition exception -> {}", e.getMessage(), e);
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
						gateway.ReqQryTradingAccount();
						log.info("FtdcAdaptor :: Has been sent ReqQryTradingAccount");
					}
				}, "QueryBalance-SubThread");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error("ftdcGateway.ReqQryTradingAccount exception -> {}", e.getMessage(), e);
			return false;
		}
	}

	@Override
	public void close() throws IOException {
		// TODO close adaptor
	}

	@Override
	public boolean sendCommand(Command command) {
		// TODO Auto-generated method stub
		return false;
	}

}
