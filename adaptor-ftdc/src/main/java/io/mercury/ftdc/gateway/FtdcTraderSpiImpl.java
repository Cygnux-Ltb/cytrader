package io.mercury.ftdc.gateway;

import static io.mercury.ftdc.gateway.base.FtdcErrorValidator.hasError;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import ctp.thostapi.CThostFtdcInputOrderField;
import ctp.thostapi.CThostFtdcInstrumentField;
import ctp.thostapi.CThostFtdcInvestorPositionField;
import ctp.thostapi.CThostFtdcOrderActionField;
import ctp.thostapi.CThostFtdcOrderField;
import ctp.thostapi.CThostFtdcRspAuthenticateField;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSettlementInfoField;
import ctp.thostapi.CThostFtdcTradeField;
import ctp.thostapi.CThostFtdcTraderSpi;
import ctp.thostapi.CThostFtdcTradingAccountField;
import ctp.thostapi.CThostFtdcUserLogoutField;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.util.StringUtil;

public final class FtdcTraderSpiImpl extends CThostFtdcTraderSpi {

	private Logger log = CommonLoggerFactory.getLogger(getClass());

	private FtdcGateway gateway;

	FtdcTraderSpiImpl(FtdcGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public void OnFrontConnected() {
		log.info("TraderSpiImpl OnFrontConnected");
		gateway.onTraderFrontConnected();
	}

	@Override
	public void OnFrontDisconnected(int nReason) {
		log.warn("TraderSpiImpl OnFrontDisconnected -> Reason==[{}]", nReason);
		gateway.onTraderFrontDisconnected();
	}

	@Override
	public void OnRspUserLogin(CThostFtdcRspUserLoginField pRspUserLogin, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		hasError("OnRspUserLogin", pRspInfo);
		log.info("TraderSpiImpl OnRspUserLogin");
		if (pRspUserLogin != null)
			gateway.onTraderRspUserLogin(pRspUserLogin);
		else
			log.info("OnRspUserLogin return null");
	}

	@Override
	public void OnRspAuthenticate(CThostFtdcRspAuthenticateField pRspAuthenticateField, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		hasError("OnRspAuthenticate", pRspInfo);
		log.info("TraderSpiImpl OnRspAuthenticate");
		if (pRspAuthenticateField != null) {

		}
	}

	@Override
	public void OnRspUserLogout(CThostFtdcUserLogoutField pUserLogout, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
		hasError("OnRspUserLogout", pRspInfo);
		log.info("Call TraderSpiImpl OnRspUserLogout");
	}
	
	@Override
	public void OnRspQryOrder(CThostFtdcOrderField pOrder, CThostFtdcRspInfoField pRspInfo, int arg2, boolean arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnRspQryTradingAccount(CThostFtdcTradingAccountField pTradingAccount, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		hasError("OnRspQryTradingAccount", pRspInfo);
		log.info("Call TraderSpiImpl OnRspQryTradingAccount");
		if (pTradingAccount != null)
			gateway.onQryTradingAccount(pTradingAccount);
		else
			log.warn("OnRspQryTradingAccount return null");

	}

	@Override
	public void OnRspQryInvestorPosition(CThostFtdcInvestorPositionField pInvestorPosition,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		hasError("OnRspQryInvestorPosition", pRspInfo);
		log.info("Call TraderSpiImpl OnRspQryInvestorPosition");
		if (pInvestorPosition != null)
			gateway.onRspQryInvestorPosition(pInvestorPosition);
		else
			log.warn("OnRspQryInvestorPosition return null");
	}

	@Override
	public void OnRspQrySettlementInfo(CThostFtdcSettlementInfoField pSettlementInfo, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		hasError("OnRspQrySettlementInfo", pRspInfo);
		if (pSettlementInfo != null)
			log.info("OnRspQrySettlementInfo -> \n {}", StringUtil.conversionGbkToUtf8(pSettlementInfo.getContent()));
		else
			log.warn("OnRspQrySettlementInfo return null");
	}

	@Override
	public void OnRspQryInstrument(CThostFtdcInstrumentField pInstrument, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		hasError("OnRspQryInstrument", pRspInfo);
		if (pInstrument != null)
			log.info("OnRspQryInstrument -> InstrumentID==[{}]", pInstrument.getInstrumentID());
		else
			log.warn("OnRspQryInstrument return null");
	}

	@Override
	public void OnRtnOrder(CThostFtdcOrderField pOrder) {
		if (pOrder != null)
			gateway.onRtnOrder(pOrder);
		else
			log.warn("OnRtnOrder return null");
	}

	@Override
	public void OnRtnTrade(CThostFtdcTradeField pTrade) {
		if (pTrade != null)
			gateway.onRtnTrade(pTrade);
		else
			log.warn("OnRtnTrade return null");
	}

	/**
	 * 报单错误回调:1
	 */
	@Override
	public void OnRspOrderInsert(CThostFtdcInputOrderField pInputOrder, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
		hasError("OnRspOrderInsert", pRspInfo);
		gateway.onRspOrderInsert(pInputOrder);
	}

	/**
	 * 报单错误回调:2
	 */
	@Override
	public void OnErrRtnOrderInsert(CThostFtdcInputOrderField pInputOrder, CThostFtdcRspInfoField pRspInfo) {
		hasError("OnErrRtnOrderInsert", pRspInfo);
		gateway.onErrRtnOrderInsert(pInputOrder);
	}

	/**
	 * 撤单错误回调:1
	 */
	@Override
	public void OnRspOrderAction(CThostFtdcInputOrderActionField pInputOrderAction, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		hasError("OnRspOrderAction", pRspInfo);
		gateway.onRspOrderAction(pInputOrderAction);
	}

	/**
	 * 撤单错误回调:2
	 */
	@Override
	public void OnErrRtnOrderAction(CThostFtdcOrderActionField pOrderAction, CThostFtdcRspInfoField pRspInfo) {
		hasError("OnErrRtnOrderAction", pRspInfo);
		gateway.onErrRtnOrderAction(pOrderAction);
	}

	/**
	 * 错误回调
	 */
	@Override
	public void OnRspError(CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		log.info("Unhandle OnRspError");
		gateway.onRspError(pRspInfo);
	}

}