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

public final class FtdcTraderSpiImpl extends CThostFtdcTraderSpi {

	private static final Logger log = CommonLoggerFactory.getLogger(FtdcTraderSpiImpl.class);

	private final FtdcGateway gateway;

	FtdcTraderSpiImpl(FtdcGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public void OnFrontConnected() {
		log.info("TraderSpiImpl :: OnFrontConnected");
		gateway.onTraderFrontConnected();
	}

	@Override
	public void OnFrontDisconnected(int nReason) {
		log.error("TraderSpiImpl :: OnFrontDisconnected, nReason==[{}]", nReason);
		gateway.onTraderFrontDisconnected();
	}

	@Override
	public void OnRspAuthenticate(CThostFtdcRspAuthenticateField pRspAuthenticateField, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		log.info("TraderSpiImpl :: OnRspAuthenticate, nRequestID==[{}], bIsLast==[{}]", nRequestID, bIsLast);
		if (!hasError("TraderSpi :: OnRspAuthenticate", pRspInfo)) {
			if (pRspAuthenticateField != null)
				gateway.onRspAuthenticate(pRspAuthenticateField);
			else
				log.warn("TraderSpiImpl :: OnRspAuthenticate return null");
		}
	}

	@Override
	public void OnRspUserLogin(CThostFtdcRspUserLoginField pRspUserLogin, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		log.info("TraderSpiImpl :: OnRspUserLogin, nRequestID==[{}], bIsLast==[{}]", nRequestID, bIsLast);
		if (!hasError("TraderSpi :: OnRspUserLogin", pRspInfo)) {
			if (pRspUserLogin != null)
				gateway.onTraderRspUserLogin(pRspUserLogin);
			else
				log.error("TraderSpiImpl :: OnRspUserLogin return null");
		}
	}

	@Override
	public void OnRspUserLogout(CThostFtdcUserLogoutField pUserLogout, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
		log.info("TraderSpiImpl :: OnRspUserLogout, nRequestID==[{}], bIsLast==[{}]", nRequestID, bIsLast);
		if (!hasError("TraderSpi :: OnRspUserLogout", pRspInfo)) {
			if (pUserLogout != null) {
				// TODO 处理用户登出
				log.info("Output :: OnRspUserLogout -> BrokerID==[{}], UserID==[{}]", pUserLogout.getBrokerID(),
						pUserLogout.getUserID());
			} else {
				log.error("TraderSpiImpl :: OnRspUserLogout return null");
			}
		}
	}

	@Override
	public void OnRspQryOrder(CThostFtdcOrderField pOrder, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
		log.info("TraderSpiImpl :: OnRspQryOrder, nRequestID==[{}], bIsLast==[{}]", nRequestID, bIsLast);
		if (!hasError("TraderSpi :: OnRspQryOrder", pRspInfo)) {
			if (pOrder != null)
				gateway.onRspQryOrder(pOrder, bIsLast);
			else
				log.error("TraderSpiImpl :: OnRspQryOrder return null");

		}
	}

	@Override
	public void OnRspQryTradingAccount(CThostFtdcTradingAccountField pTradingAccount, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		log.info("TraderSpiImpl :: OnRspQryTradingAccount, nRequestID==[{}], bIsLast==[{}]", nRequestID, bIsLast);
		if (!hasError("TraderSpi :: OnRspQryTradingAccount", pRspInfo)) {
			if (pTradingAccount != null)
				gateway.onQryTradingAccount(pTradingAccount, bIsLast);
			else
				log.error("TraderSpiImpl :: OnRspQryTradingAccount return null");
		}
	}

	@Override
	public void OnRspQryInvestorPosition(CThostFtdcInvestorPositionField pInvestorPosition,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		log.info("TraderSpiImpl :: OnRspQryInvestorPosition, nRequestID==[{}], bIsLast==[{}]", nRequestID, bIsLast);
		hasError("TraderSpi :: OnRspQryInvestorPosition", pRspInfo);
		if (pInvestorPosition != null) {
			gateway.onRspQryInvestorPosition(pInvestorPosition, bIsLast);
		} else {
			log.error("TraderSpiImpl :: OnRspQryInvestorPosition return null");
		}
	}

	@Override
	public void OnRspQrySettlementInfo(CThostFtdcSettlementInfoField pSettlementInfo, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		log.info("TraderSpiImpl :: OnRspQrySettlementInfo, nRequestID==[{}], bIsLast==[{}]", nRequestID, bIsLast);
		if (!hasError("TraderSpi :: OnRspQrySettlementInfo", pRspInfo)) {
			if (pSettlementInfo != null) {
				log.info(
						"Output :: OnRspQrySettlementInfo -> BrokerID==[{}], AccountID==[{}], "
								+ "InvestorID==[{}], SettlementID==[{}], TradingDay==[{}], CurrencyID==[{}], "
								+ "\n <<<<<<<<<<<<<<<< CONTENT TEXT >>>>>>>>>>>>>>>> \n",
						pSettlementInfo.getBrokerID(), pSettlementInfo.getAccountID(), pSettlementInfo.getInvestorID(),
						pSettlementInfo.getSettlementID(), pSettlementInfo.getTradingDay(),
						pSettlementInfo.getCurrencyID(), pSettlementInfo.getContent());
			} else {
				log.error("TraderSpiImpl :: OnRspQrySettlementInfo return null");
			}
		}
	}

	@Override
	public void OnRspQryInstrument(CThostFtdcInstrumentField pInstrument, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		log.info("TraderSpiImpl :: OnRspQryInstrument, nRequestID==[{}], bIsLast==[{}]", nRequestID, bIsLast);
		if (!hasError("TraderSpi :: OnRspQryInstrument", pRspInfo)) {
			if (pInstrument != null)
				log.info("Output :: OnRspQryInstrument, ExchangeID==[{}], InstrumentID==[{}]",
						pInstrument.getExchangeID(), pInstrument.getInstrumentID());
			else
				log.error("TraderSpiImpl :: OnRspQryInstrument return null");
		}
	}

	@Override
	public void OnRtnOrder(CThostFtdcOrderField pOrder) {
		if (pOrder != null)
			gateway.onRtnOrder(pOrder);
		else
			log.error("TraderSpiImpl :: OnRtnOrder return null");
	}

	@Override
	public void OnRtnTrade(CThostFtdcTradeField pTrade) {
		if (pTrade != null)
			gateway.onRtnTrade(pTrade);
		else
			log.error("TraderSpiImpl :: OnRtnTrade return null");
	}

	/**
	 * 报单错误回调:1
	 */
	@Override
	public void OnRspOrderInsert(CThostFtdcInputOrderField pInputOrder, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
		log.info("TraderSpiImpl :: OnRspOrderInsert, nRequestID==[{}], bIsLast==[{}]", nRequestID, bIsLast);
		if (!hasError("SPI :: OnRspOrderInsert", pRspInfo)) {
			if (pInputOrder != null)
				gateway.onRspOrderInsert(pInputOrder);
			else
				log.error("TraderSpiImpl :: OnRspOrderInsert return null");
		}
	}

	/**
	 * 报单错误回调:2
	 */
	@Override
	public void OnErrRtnOrderInsert(CThostFtdcInputOrderField pInputOrder, CThostFtdcRspInfoField pRspInfo) {
		log.info("TraderSpiImpl :: OnErrRtnOrderInsert");
		if (!hasError("TraderSpi :: OnErrRtnOrderInsert", pRspInfo)) {
			if (pInputOrder != null)
				gateway.onErrRtnOrderInsert(pInputOrder);
			else
				log.error("TraderSpiImpl :: OnErrRtnOrderInsert return null");
		}
	}

	/**
	 * 撤单错误回调:1
	 */
	@Override
	public void OnRspOrderAction(CThostFtdcInputOrderActionField pInputOrderAction, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		log.info("TraderSpiImpl :: OnRspOrderAction, nRequestID==[{}], bIsLast==[{}]", nRequestID, bIsLast);
		if (!hasError("TraderSpi :: OnRspOrderAction", pRspInfo)) {
			if (pInputOrderAction != null)
				gateway.onRspOrderAction(pInputOrderAction);
			else
				log.error("TraderSpiImpl :: OnRspOrderAction return null");
		}
	}

	/**
	 * 撤单错误回调:2
	 */
	@Override
	public void OnErrRtnOrderAction(CThostFtdcOrderActionField pOrderAction, CThostFtdcRspInfoField pRspInfo) {
		log.info("TraderSpiImpl :: OnErrRtnOrderAction");
		if (!hasError("TraderSpi :: OnErrRtnOrderAction", pRspInfo)) {
			if (pOrderAction != null)
				gateway.onErrRtnOrderAction(pOrderAction);
			else
				log.error("TraderSpiImpl :: OnErrRtnOrderAction return null");
		}
	}

	/**
	 * 错误回调
	 */
	@Override
	public void OnRspError(CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		log.info("TraderSpiImpl :: OnRspError, nRequestID==[{}], bIsLast==[{}]", nRequestID, bIsLast);
		gateway.onRspError(pRspInfo);
	}

}