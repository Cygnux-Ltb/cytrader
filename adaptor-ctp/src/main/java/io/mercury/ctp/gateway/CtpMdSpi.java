package io.mercury.ctp.gateway;

import static io.mercury.ctp.gateway.base.CtpRspValidator.validateRspInfo;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcMdSpi;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import io.mercury.common.log.CommonLoggerFactory;

public final class CtpMdSpi extends CThostFtdcMdSpi {

	private Logger log = CommonLoggerFactory.getLogger(getClass());

	private CtpGateway gateway;

	CtpMdSpi(CtpGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public void OnFrontConnected() {
		log.info("MdSpiImpl OnFrontConnected");
		gateway.onMdFrontConnected();
	}

	@Override
	public void OnFrontDisconnected(int nReason) {
		log.warn("MdSpiImpl OnFrontDisconnected -> Reason==[{}]", nReason);
	}

	@Override
	public void OnRspUserLogin(CThostFtdcRspUserLoginField pRspUserLogin, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		validateRspInfo("OnRspUserLogin", pRspInfo);
		log.info("MdSpiImpl OnRspUserLogin");
		if (pRspUserLogin != null)
			gateway.onMdRspUserLogin(pRspUserLogin);
		else
			log.info("OnRspUserLogin return null");
	}

	@Override
	public void OnRspSubMarketData(CThostFtdcSpecificInstrumentField pSpecificInstrument,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		validateRspInfo("OnRspSubMarketData", pRspInfo);
		log.info("MdSpiImpl OnRspSubMarketData");
		if (pSpecificInstrument != null)
			gateway.onRspSubMarketData(pSpecificInstrument);
		else
			log.info("OnRspSubMarketData return null");
	}

	@Override
	public void OnRtnDepthMarketData(CThostFtdcDepthMarketDataField pDepthMarketData) {
		if (pDepthMarketData != null)
			gateway.onRtnDepthMarketData(pDepthMarketData);
		else
			log.info("OnRtnDepthMarketData return null");
	}

}