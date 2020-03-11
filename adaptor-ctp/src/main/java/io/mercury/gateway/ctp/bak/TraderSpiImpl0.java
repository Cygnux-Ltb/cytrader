package io.mercury.gateway.ctp.bak;

import static io.mercury.gateway.ctp.base.JctpRspValidator.validateRspInfo;

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

public class TraderSpiImpl0 extends CThostFtdcTraderSpi {

	private Logger logger = CommonLoggerFactory.getLogger(getClass());

	private CtpGateway0 gateway;

	TraderSpiImpl0(CtpGateway0 gateway) {
		this.gateway = gateway;
	}

	@Override
	public void OnFrontConnected() {
		logger.info("TraderSpiImpl OnFrontConnected");
		gateway.onTraderFrontConnected();
	}

	@Override
	public void OnFrontDisconnected(int nReason) {
		logger.warn("TraderSpiImpl OnFrontDisconnected -> Reason==[{}]", nReason);
	}

	@Override
	public void OnRspUserLogin(CThostFtdcRspUserLoginField pRspUserLogin, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		validateRspInfo("OnRspUserLogin", pRspInfo);
		logger.info("TraderSpiImpl OnRspUserLogin");
		if (pRspUserLogin != null)
			gateway.onTraderRspUserLogin(pRspUserLogin);
		else
			logger.info("OnRspUserLogin return null");
	}

	@Override
	public void OnRspAuthenticate(CThostFtdcRspAuthenticateField pRspAuthenticateField, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		validateRspInfo("OnRspAuthenticate", pRspInfo);
		logger.info("TraderSpiImpl OnRspAuthenticate");
		if (pRspAuthenticateField != null) {

		}
	}

	@Override
	public void OnRspUserLogout(CThostFtdcUserLogoutField pUserLogout, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
		validateRspInfo("OnRspUserLogout", pRspInfo);
		logger.info("Call TraderSpiImpl OnRspUserLogout");
	}

	@Override
	public void OnRspQryTradingAccount(CThostFtdcTradingAccountField pTradingAccount, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		validateRspInfo("OnRspQryTradingAccount", pRspInfo);
		logger.info("Call TraderSpiImpl OnRspQryTradingAccount");
		if (pTradingAccount != null)
			gateway.onQryTradingAccount(pTradingAccount);
		else
			logger.warn("OnRspQryTradingAccount return null");
	}

	@Override
	public void OnRspQryInvestorPosition(CThostFtdcInvestorPositionField pInvestorPosition,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		validateRspInfo("OnRspQryInvestorPosition", pRspInfo);
		logger.info("Call TraderSpiImpl OnRspQryInvestorPosition");
		if (pInvestorPosition != null)
			gateway.onRspQryInvestorPosition(pInvestorPosition);
		else
			logger.warn("OnRspQryInvestorPosition return null");
	}

	@Override
	public void OnRspQrySettlementInfo(CThostFtdcSettlementInfoField pSettlementInfo, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		validateRspInfo("OnRspQrySettlementInfo", pRspInfo);
		if (pSettlementInfo != null)
			logger.info("OnRspQrySettlementInfo -> \n {}",
					StringUtil.conversionGbkToUtf8(pSettlementInfo.getContent()));
		else
			logger.warn("OnRspQrySettlementInfo return null");
	}

	@Override
	public void OnRspQryInstrument(CThostFtdcInstrumentField pInstrument, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		validateRspInfo("OnRspQryInstrument", pRspInfo);
		if (pInstrument != null)
			logger.info("OnRspQryInstrument -> InstrumentID==[{}]", pInstrument.getInstrumentID());
		else
			logger.warn("OnRspQryInstrument return null");
	}

	@Override
	public void OnRtnOrder(CThostFtdcOrderField pOrder) {
		if (pOrder != null)
			gateway.onRtnOrder(pOrder);
		else
			logger.warn("OnRtnOrder return null");
	}

	@Override
	public void OnRtnTrade(CThostFtdcTradeField pTrade) {
		if (pTrade != null)
			gateway.onRtnTrade(pTrade);
		else
			logger.warn("OnRtnTrade return null");
	}

	@Override
	public void OnRspOrderInsert(CThostFtdcInputOrderField pInputOrder, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
		validateRspInfo("OnRspOrderInsert", pRspInfo);
		gateway.onRspOrderInsert(pInputOrder);
	}

	@Override
	public void OnRspOrderAction(CThostFtdcInputOrderActionField pInputOrderAction, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		validateRspInfo("OnRspOrderAction", pRspInfo);
		gateway.onRspOrderAction(pInputOrderAction);
	}

	@Override
	public void OnErrRtnOrderInsert(CThostFtdcInputOrderField pInputOrder, CThostFtdcRspInfoField pRspInfo) {
		validateRspInfo("OnErrRtnOrderInsert", pRspInfo);
		gateway.onErrRtnOrderInsert(pInputOrder);
	}

	@Override
	public void OnErrRtnOrderAction(CThostFtdcOrderActionField pOrderAction, CThostFtdcRspInfoField pRspInfo) {
		validateRspInfo("OnErrRtnOrderAction", pRspInfo);
		gateway.onErrRtnOrderAction(pOrderAction);
	}

	@Override
	public void OnRspError(CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		logger.info("Unhandle OnRspError");
		gateway.onRspError(pRspInfo);
	}

}