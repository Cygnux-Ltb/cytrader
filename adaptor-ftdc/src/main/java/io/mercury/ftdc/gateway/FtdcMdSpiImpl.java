package io.mercury.ftdc.gateway;

import static io.mercury.ftdc.gateway.base.FtdcErrorValidator.hasError;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcMdSpi;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import io.mercury.common.log.CommonLoggerFactory;

public final class FtdcMdSpiImpl extends CThostFtdcMdSpi {

	private Logger log = CommonLoggerFactory.getLogger(getClass());

	private FtdcGateway gateway;

	FtdcMdSpiImpl(FtdcGateway gateway) {
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
		gateway.onMdFrontDisconnected();
	}

	@Override
	public void OnRspUserLogin(CThostFtdcRspUserLoginField pRspUserLogin, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		hasError("OnRspUserLogin", pRspInfo);
		log.info("MdSpiImpl OnRspUserLogin");
		if (pRspUserLogin != null)
			gateway.onMdRspUserLogin(pRspUserLogin);
		else
			log.warn("OnRspUserLogin return null");
	}

	@Override
	public void OnRspSubMarketData(CThostFtdcSpecificInstrumentField pSpecificInstrument,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		hasError("OnRspSubMarketData", pRspInfo);
		log.info("MdSpiImpl OnRspSubMarketData");
		if (pSpecificInstrument != null)
			gateway.onRspSubMarketData(pSpecificInstrument);
		else
			log.warn("OnRspSubMarketData return null");
	}

	@Override
	public void OnRtnDepthMarketData(CThostFtdcDepthMarketDataField pDepthMarketData) {
		if (pDepthMarketData != null)
			gateway.onRtnDepthMarketData(pDepthMarketData);
		else
			log.warn("OnRtnDepthMarketData return null");
	}

	/**
	 * 错误回调
	 */
	@Override
	public void OnRspError(CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		log.warn("Md SPI return OnRspError");
		gateway.onRspError(pRspInfo);
	}

}