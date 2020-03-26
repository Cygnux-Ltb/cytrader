package io.mercury.ctp.adaptor;

import static io.mercury.financial.util.PriceUtil.priceToLong4;

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
import ctp.thostapi.thosttraderapiConstants;
import io.mercury.common.concurrent.queue.MpscArrayBlockingQueue;
import io.mercury.common.datetime.Pattern.DatePattern;
import io.mercury.common.datetime.Pattern.TimePattern;
import io.mercury.common.datetime.TimeConst;
import io.mercury.common.datetime.TimeZone;
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
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.instrument.InstrumentKeeper;
import io.mercury.financial.market.impl.BasicMarketData;
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

	private Function<Order, CThostFtdcInputOrderField> newOrderConverter = order -> {
		int orderRef = CtpOrderRefGenerate.next(appId);
		Instrument instrument = order.instrument();
		CThostFtdcInputOrderField ftdcInputOrder = new CThostFtdcInputOrderField();
		/**
		 * 设置交易所ID
		 */
		ftdcInputOrder.setExchangeID(instrument.symbol().exchange().code());
		/**
		 * 设置交易标的
		 */
		ftdcInputOrder.setInstrumentID(instrument.code());
		/**
		 * 设置OrderRef
		 */
		ftdcInputOrder.setOrderRef(Integer.toString(orderRef));
		/**
		 * /////////////////////////////////////////////////////////////////////////
		 * ///TFtdcOrderPriceTypeType是一个报单价格条件类型
		 * /////////////////////////////////////////////////////////////////////////
		 * ///任意价<br>
		 * #define THOST_FTDC_OPT_AnyPrice '1'<br>
		 * ///限价<br>
		 * #define THOST_FTDC_OPT_LimitPrice '2'<br>
		 * ///最优价<br>
		 * #define THOST_FTDC_OPT_BestPrice '3'<br>
		 * ///最新价<br>
		 * #define THOST_FTDC_OPT_LastPrice '4'<br>
		 * ///最新价浮动上浮1个ticks<br>
		 * #define THOST_FTDC_OPT_LastPricePlusOneTicks '5'<br>
		 * ///最新价浮动上浮2个ticks<br>
		 * #define THOST_FTDC_OPT_LastPricePlusTwoTicks '6'<br>
		 * ///最新价浮动上浮3个ticks<br>
		 * #define THOST_FTDC_OPT_LastPricePlusThreeTicks '7'<br>
		 * ///卖一价<br>
		 * #define THOST_FTDC_OPT_AskPrice1 '8'<br>
		 * ///卖一价浮动上浮1个ticks<br>
		 * #define THOST_FTDC_OPT_AskPrice1PlusOneTicks '9'<br>
		 * ///卖一价浮动上浮2个ticks<br>
		 * #define THOST_FTDC_OPT_AskPrice1PlusTwoTicks 'A'<br>
		 * ///卖一价浮动上浮3个ticks<br>
		 * #define THOST_FTDC_OPT_AskPrice1PlusThreeTicks 'B'<br>
		 * ///买一价<br>
		 * #define THOST_FTDC_OPT_BidPrice1 'C'<br>
		 * ///买一价浮动上浮1个ticks<br>
		 * #define THOST_FTDC_OPT_BidPrice1PlusOneTicks 'D'<br>
		 * ///买一价浮动上浮2个ticks<br>
		 * #define THOST_FTDC_OPT_BidPrice1PlusTwoTicks 'E'<br>
		 * ///买一价浮动上浮3个ticks<br>
		 * #define THOST_FTDC_OPT_BidPrice1PlusThreeTicks 'F'<br>
		 * ///五档价<br>
		 * #define THOST_FTDC_OPT_FiveLevelPrice 'G'<br>
		 */
		ftdcInputOrder.setOrderPriceType(thosttraderapiConstants.THOST_FTDC_OPT_LimitPrice);
		/**
		 * /////////////////////////////////////////////////////////////////////////
		 * ///TFtdcOffsetFlagType是一个开平标志类型
		 * /////////////////////////////////////////////////////////////////////////
		 * ///开仓<br>
		 * #define THOST_FTDC_OF_Open '0' <br>
		 * ///平仓<br>
		 * #define THOST_FTDC_OF_Close '1'<br>
		 * ///强平<br>
		 * #define THOST_FTDC_OF_ForceClose '2' <br>
		 * ///平今<br>
		 * #define THOST_FTDC_OF_CloseToday '3'<br>
		 * ///平昨<br>
		 * #define THOST_FTDC_OF_CloseYesterday '4' <br>
		 * ///强减<br>
		 * #define THOST_FTDC_OF_ForceOff '5'<br>
		 * ///本地强平<br>
		 * #define THOST_FTDC_OF_LocalForceClose '6'
		 */
		ftdcInputOrder.setCombOffsetFlag(new String(new char[] { thosttraderapiConstants.THOST_FTDC_OF_Open }));
		/**
		 * /////////////////////////////////////////////////////////////////////////
		 * ///TFtdcHedgeFlagType是一个投机套保标志类型
		 * /////////////////////////////////////////////////////////////////////////
		 * ///投机<br>
		 * #define THOST_FTDC_HF_Speculation '1'<br>
		 * ///套利<br>
		 * #define THOST_FTDC_HF_Arbitrage '2'<br>
		 * ///套保<br>
		 * #define THOST_FTDC_HF_Hedge '3'<br>
		 * ///做市商<br>
		 * #define THOST_FTDC_HF_MarketMaker '5'<br>
		 * ///第一腿投机第二腿套保 大商所专用<br>
		 * #define THOST_FTDC_HF_SpecHedge '6'<br>
		 * ///第一腿套保第二腿投机 大商所专用<br>
		 * #define THOST_FTDC_HF_HedgeSpec '7'<br>
		 */
		ftdcInputOrder.setCombHedgeFlag(new String(new char[] { thosttraderapiConstants.THOST_FTDC_HF_Speculation }));
		/**
		 * /////////////////////////////////////////////////////////////////////////
		 * ///TFtdcDirectionType是一个买卖方向类型
		 * /////////////////////////////////////////////////////////////////////////
		 * ///买<br>
		 * #define THOST_FTDC_D_Buy '0'<br>
		 * ///卖<br>
		 * #define THOST_FTDC_D_Sell '1'<br>
		 */
		switch (order.ordSide().direction()) {
		case Long:
			ftdcInputOrder.setDirection(thosttraderapiConstants.THOST_FTDC_D_Buy);
			break;
		case Short:
			ftdcInputOrder.setDirection(thosttraderapiConstants.THOST_FTDC_D_Sell);
			break;
		default:
			throw new RuntimeException(order.ordSide() + " does not exist.");
		}
		/**
		 * 设置价格
		 */
		ftdcInputOrder.setLimitPrice(order.ordPrice().offerPrice());
		/**
		 * 设置数量
		 */
		ftdcInputOrder.setVolumeTotalOriginal((int) order.ordQty().offerQty());
		/**
		 * /////////////////////////////////////////////////////////////////////////<br>
		 * ///TFtdcTimeConditionType是一个有效期类型类型<br>
		 * /////////////////////////////////////////////////////////////////////////<br>
		 * ///立即完成，否则撤销<br>
		 * #define THOST_FTDC_TC_IOC '1'<br>
		 * ///本节有效<br>
		 * #define THOST_FTDC_TC_GFS '2'<br>
		 * ///当日有效<br>
		 * #define THOST_FTDC_TC_GFD '3'<br>
		 * ///指定日期前有效<br>
		 * #define THOST_FTDC_TC_GTD '4'<br>
		 * ///撤销前有效<br>
		 * #define THOST_FTDC_TC_GTC '5'<br>
		 * ///集合竞价有效<br>
		 * #define THOST_FTDC_TC_GFA '6'<br>
		 */
		ftdcInputOrder.setTimeCondition(thosttraderapiConstants.THOST_FTDC_TC_GFD);
		/**
		 * /////////////////////////////////////////////////////////////////////////<br>
		 * ///TFtdcVolumeConditionType是一个成交量类型类型<br>
		 * /////////////////////////////////////////////////////////////////////////<br>
		 * ///任何数量<br>
		 * #define THOST_FTDC_VC_AV '1'<br>
		 * ///最小数量<br>
		 * #define THOST_FTDC_VC_MV '2'<br>
		 * ///全部数量<br>
		 * #define THOST_FTDC_VC_CV '3'<br>
		 */
		ftdcInputOrder.setVolumeCondition(thosttraderapiConstants.THOST_FTDC_VC_AV);
		/**
		 * 设置最小成交数量
		 */
		ftdcInputOrder.setMinVolume(1);
		/**
		 * /////////////////////////////////////////////////////////////////////////
		 * ///TFtdcContingentConditionType是一个触发条件类型
		 * /////////////////////////////////////////////////////////////////////////
		 * ///立即<br>
		 * #define THOST_FTDC_CC_Immediately '1'<br>
		 * ///止损<br>
		 * #define THOST_FTDC_CC_Touch '2'<br>
		 * ///止赢<br>
		 * #define THOST_FTDC_CC_TouchProfit '3'<br>
		 * ///预埋单<br>
		 * #define THOST_FTDC_CC_ParkedOrder '4'<br>
		 * ///最新价大于条件价<br>
		 * #define THOST_FTDC_CC_LastPriceGreaterThanStopPrice '5'<br>
		 * ///最新价大于等于条件价<br>
		 * #define THOST_FTDC_CC_LastPriceGreaterEqualStopPrice '6'<br>
		 * ///最新价小于条件价<br>
		 * #define THOST_FTDC_CC_LastPriceLesserThanStopPrice '7'<br>
		 * ///最新价小于等于条件价<br>
		 * #define THOST_FTDC_CC_LastPriceLesserEqualStopPrice '8'<br>
		 * ///卖一价大于条件价<br>
		 * #define THOST_FTDC_CC_AskPriceGreaterThanStopPrice '9'<br>
		 * ///卖一价大于等于条件价<br>
		 * #define THOST_FTDC_CC_AskPriceGreaterEqualStopPrice 'A'<br>
		 * ///卖一价小于条件价<br>
		 * #define THOST_FTDC_CC_AskPriceLesserThanStopPrice 'B'<br>
		 * ///卖一价小于等于条件价<br>
		 * #define THOST_FTDC_CC_AskPriceLesserEqualStopPrice 'C'<br>
		 * ///买一价大于条件价<br>
		 * #define THOST_FTDC_CC_BidPriceGreaterThanStopPrice 'D'<br>
		 * ///买一价大于等于条件价<br>
		 * #define THOST_FTDC_CC_BidPriceGreaterEqualStopPrice 'E'<br>
		 * ///买一价小于条件价<br>
		 * #define THOST_FTDC_CC_BidPriceLesserThanStopPrice 'F'<br>
		 * ///买一价小于等于条件价<br>
		 * #define THOST_FTDC_CC_BidPriceLesserEqualStopPrice 'H'<br>
		 */
		ftdcInputOrder.setContingentCondition(thosttraderapiConstants.THOST_FTDC_CC_Immediately);
		/**
		 * 设置止损价格
		 */
		ftdcInputOrder.setStopPrice(0.0D);
		/**
		 * /////////////////////////////////////////////////////////////////////////
		 * ///TFtdcForceCloseReasonType是一个强平原因类型
		 * /////////////////////////////////////////////////////////////////////////
		 * ///非强平<br>
		 * #define THOST_FTDC_FCC_NotForceClose '0'<br>
		 * ///资金不足<br>
		 * #define THOST_FTDC_FCC_LackDeposit '1'<br>
		 * ///客户超仓<br>
		 * #define THOST_FTDC_FCC_ClientOverPositionLimit '2'<br>
		 * ///会员超仓<br>
		 * #define THOST_FTDC_FCC_MemberOverPositionLimit '3'<br>
		 * ///持仓非整数倍<br>
		 * #define THOST_FTDC_FCC_NotMultiple '4'<br>
		 * ///违规<br>
		 * #define THOST_FTDC_FCC_Violation '5'<br>
		 * ///其它<br>
		 * #define THOST_FTDC_FCC_Other '6'<br>
		 * ///自然人临近交割<br>
		 * #define THOST_FTDC_FCC_PersonDeliv '7'<br>
		 */
		ftdcInputOrder.setForceCloseReason(thosttraderapiConstants.THOST_FTDC_FCC_NotForceClose);
		/**
		 * 设置自动挂起标识
		 */
		ftdcInputOrder.setIsAutoSuspend(0);
		return ftdcInputOrder;
	};

	@Override
	public boolean newOredr(ChildOrder order) {
		try {
			CThostFtdcInputOrderField ftdcInputOrder = newOrderConverter.apply(order);
			CtpOrderRefKeeper.put(ftdcInputOrder.getOrderRef(), order.ordSysId());
			gateway.reqOrderInsert(ftdcInputOrder);
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
			String orderRef = CtpOrderRefKeeper.getOrderRef(order.ordSysId());
			ftdcInputOrderAction.setOrderRef(orderRef);
			gateway.reqOrderAction(ftdcInputOrderAction);
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
			gateway.reqQryInvestorPosition();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean queryBalance(Account account) {
		try {
			gateway.reqQryTradingAccount();
			return true;
		} catch (Exception e) {
			log.error("gatewayqueryBalance ", e);
			return false;
		}
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub

	}

}
