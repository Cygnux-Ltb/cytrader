package io.mercury.ftdc.gateway.bak;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcExchangeField;
import ctp.thostapi.CThostFtdcExecOrderField;
import ctp.thostapi.CThostFtdcForQuoteField;
import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcInstrumentCommissionRateField;
import ctp.thostapi.CThostFtdcInstrumentField;
import ctp.thostapi.CThostFtdcInstrumentMarginRateField;
import ctp.thostapi.CThostFtdcInstrumentStatusField;
import ctp.thostapi.CThostFtdcInvestorField;
import ctp.thostapi.CThostFtdcInvestorPositionDetailField;
import ctp.thostapi.CThostFtdcInvestorPositionField;
import ctp.thostapi.CThostFtdcOrderActionField;
import ctp.thostapi.CThostFtdcOrderField;
import ctp.thostapi.CThostFtdcProductField;
import ctp.thostapi.CThostFtdcQuoteField;
import ctp.thostapi.CThostFtdcRspAuthenticateField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSettlementInfoConfirmField;
import ctp.thostapi.CThostFtdcSettlementInfoField;
import ctp.thostapi.CThostFtdcTradeField;
import ctp.thostapi.CThostFtdcTraderSpi;
import ctp.thostapi.CThostFtdcTradingAccountField;
import ctp.thostapi.CThostFtdcTradingCodeField;
import ctp.thostapi.CThostFtdcUserLogoutField;
import io.mercury.common.log.CommonLoggerFactory;

@Deprecated
public class TdSpi extends CThostFtdcTraderSpi {

	private Logger log = CommonLoggerFactory.getLogger(TdSpi.class);

	private CtpGateway gateway;
	private String gatewayId;

	// private HashMap<String, String> originalOrderIdMap = new HashMap<>();

	TdSpi(CtpGateway gateway) {
		this.gateway = gateway;
		this.gatewayId = gateway.getGatewayId();
	}

	// 登录起始阶段缓存Order
	// private List<Order> orderCacheList = new LinkedList<>();
	// 登录起始阶段缓存Trade
	// private List<Trade> tradeCacheList = new LinkedList<>();

	// 前置机联机回报
	@Override
	public void OnFrontConnected() {
		log.info("{} TdApi front connected", gatewayId);
		gateway.onTdFrontConnected();
	}

	// 前置机断开回报
	@Override
	public void OnFrontDisconnected(int nReason) {
		log.info("{} TdApi front disconnected, Reason : {}", gatewayId, nReason);
		gateway.onTdFrontDisconnected(nReason);
	}

	// 登录回报
	@Override
	public void OnRspUserLogin(CThostFtdcRspUserLoginField pRspUserLogin, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		if (pRspInfo.getErrorID() == 0) {
			log.info("{} TdApi login success. tradingDay==[{}], sessionId==[{}], brokerId==[{}], userId==[{}]",
					gatewayId, pRspUserLogin.getTradingDay(), pRspUserLogin.getSessionID(), pRspUserLogin.getBrokerID(),
					pRspUserLogin.getUserID());
			gateway.setTdFrontId(pRspUserLogin.getFrontID());
			gateway.setTdSessionId(pRspUserLogin.getSessionID());
			gateway.onTdRspUserLogin(true);
//			// 确认结算单
//			CThostFtdcSettlementInfoConfirmField settlementInfoConfirmField = new CThostFtdcSettlementInfoConfirmField();
//			settlementInfoConfirmField.setBrokerID(brokerId);
//			settlementInfoConfirmField.setInvestorID(userId);
//			cThostFtdcTraderApi.ReqSettlementInfoConfirm(settlementInfoConfirmField, reqID.incrementAndGet());
		} else {
			log.error("{} TdApi login failure, errorId==[{}], errorMsg==[{}]", gatewayId, pRspInfo.getErrorID(),
					pRspInfo.getErrorMsg());
			gateway.onTdRspUserLogin(false);
		}

	}

	// 心跳警告
	@Override
	public void OnHeartBeatWarning(int nTimeLapse) {
		log.warn("{} OnHeartBeatWarning, nTimeLapse==[{}]", gatewayId, nTimeLapse);
	}

	// 登出回报
	@Override
	public void OnRspUserLogout(CThostFtdcUserLogoutField pUserLogout, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
		if (pRspInfo.getErrorID() != 0)
			log.info("{}OnRspUserLogout!ErrorID:{},ErrorMsg:{}", gatewayId, pRspInfo.getErrorID(),
					pRspInfo.getErrorMsg());
		else
			log.info("{}OnRspUserLogout!BrokerID:{},UserID:{}", gatewayId, pUserLogout.getBrokerID(),
					pUserLogout.getUserID());
	}

	// 错误回报
	@Override
	public void OnRspError(CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		log.error("{}交易接口错误回报!ErrorID:{},ErrorMsg:{},RequestID:{},isLast:{}", gatewayId, pRspInfo.getErrorID(),
				pRspInfo.getErrorMsg(), nRequestID, bIsLast);

	}

	// 验证客户端回报
	@Override
	public void OnRspAuthenticate(CThostFtdcRspAuthenticateField pRspAuthenticateField, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		if (pRspInfo.getErrorID() == 0) {
			log.info(gatewayId + "交易接口客户端验证成功");
			gateway.onRspAuthenticate();
		} else
			log.error("{}交易接口客户端验证失败! ErrorID:{},ErrorMsg:{}", gatewayId, pRspInfo.getErrorID(),
					pRspInfo.getErrorMsg());
	}

	// 发单错误(柜台)
	@Override
	public void OnRspOrderInsert(CThostFtdcInputOrderField pInputOrder, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
		// TODO
		// 发送委托事件
		log.error("{}交易接口发单错误回报(柜台)! ErrorID:{},ErrorMsg:{}", gatewayId, pRspInfo.getErrorID(), pRspInfo.getErrorMsg());
	}

	// 撤单错误回报(柜台)
	@Override
	public void OnRspOrderAction(CThostFtdcInputOrderActionField pInputOrderAction, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		log.error("{}交易接口撤单错误（柜台）! ErrorID:{},ErrorMsg:{}", gatewayId, pRspInfo.getErrorID(), pRspInfo.getErrorMsg());
	}

	// 确认结算信息回报
	@Override
	public void OnRspSettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField pSettlementInfoConfirm,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		if (pRspInfo.getErrorID() == 0)
			log.warn("{}交易接口结算信息确认完成!", gatewayId);
		else
			log.error("{}交易接口结算信息确认出错! ErrorID:{},ErrorMsg:{}", gatewayId, pRspInfo.getErrorID(),
					pRspInfo.getErrorMsg());
		// 查询所有合约
		log.warn("{}交易接口开始查询合约信息!", gatewayId);
//		CThostFtdcQryInstrumentField cThostFtdcQryInstrumentField = new CThostFtdcQryInstrumentField();
//		cThostFtdcTraderApi.ReqQryInstrument(cThostFtdcQryInstrumentField, reqId.incrementAndGet());

	}

	// 持仓查询回报
	@Override
	public void OnRspQryInvestorPosition(CThostFtdcInvestorPositionField pInvestorPosition,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		// TODO Recode
	}

	// 账户查询回报
	@Override
	public void OnRspQryTradingAccount(CThostFtdcTradingAccountField pTradingAccount, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		// TODO

	}

	@Override
	public void OnRspQryInvestor(CThostFtdcInvestorField pInvestor, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
	}

	@Override
	public void OnRspQryTradingCode(CThostFtdcTradingCodeField pTradingCode, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	@Override
	public void OnRspQryInstrumentMarginRate(CThostFtdcInstrumentMarginRateField pInstrumentMarginRate,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	@Override
	public void OnRspQryInstrumentCommissionRate(CThostFtdcInstrumentCommissionRateField pInstrumentCommissionRate,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	@Override
	public void OnRspQryExchange(CThostFtdcExchangeField pExchange, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
	}

	@Override
	public void OnRspQryProduct(CThostFtdcProductField pProduct, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
	}

	// 合约查询回报
	public void OnRspQryInstrument(CThostFtdcInstrumentField pInstrument, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		// TODO
	}

	@Override
	public void OnRspQrySettlementInfo(CThostFtdcSettlementInfoField pSettlementInfo, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
	}

	@Override
	public void OnRspQryInvestorPositionDetail(CThostFtdcInvestorPositionDetailField pInvestorPositionDetail,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	@Override
	public void OnRspQrySettlementInfoConfirm(CThostFtdcSettlementInfoConfirmField pSettlementInfoConfirm,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
	}

	@Override
	public void OnRspQryExecOrder(CThostFtdcExecOrderField pExecOrder, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
	}

	@Override
	public void OnRspQryForQuote(CThostFtdcForQuoteField pForQuote, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
	}

	@Override
	public void OnRspQryQuote(CThostFtdcQuoteField pQuote, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
	}

	// 委托回报
	@Override
	public void OnRtnOrder(CThostFtdcOrderField pOrder) {
		// TODO
	}

	// 成交回报
	@Override
	public void OnRtnTrade(CThostFtdcTradeField pTrade) {
		// TODO
	}

	// 发单错误回报(交易所)
	@Override
	public void OnErrRtnOrderInsert(CThostFtdcInputOrderField pInputOrder, CThostFtdcRspInfoField pRspInfo) {
		// TODO
		log.error("{}交易接口发单错误回报（交易所）! ErrorID:{},ErrorMsg:{}", gatewayId, pRspInfo.getErrorID(),
				pRspInfo.getErrorMsg());
	}

	// 撤单错误回报(交易所)
	@Override
	public void OnErrRtnOrderAction(CThostFtdcOrderActionField pOrderAction, CThostFtdcRspInfoField pRspInfo) {
		log.error("{}交易接口撤单错误回报（交易所）! ErrorID:{},ErrorMsg:{}", gatewayId, pRspInfo.getErrorID(),
				pRspInfo.getErrorMsg());
	}

	@Override
	public void OnRtnInstrumentStatus(CThostFtdcInstrumentStatusField pInstrumentStatus) {
	}

}