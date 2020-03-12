package io.mercury.ctp.gateway.bak;

import java.util.HashMap;

import org.slf4j.Logger;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import ctp.thostapi.CThostFtdcForQuoteRspField;
import ctp.thostapi.CThostFtdcMdSpi;
import ctp.thostapi.CThostFtdcRspInfoField;
import ctp.thostapi.CThostFtdcRspUserLoginField;
import ctp.thostapi.CThostFtdcSpecificInstrumentField;
import ctp.thostapi.CThostFtdcUserLogoutField;
import io.mercury.common.log.CommonLoggerFactory;

@Deprecated
@SuppressWarnings("unused")
public class MdSpi extends CThostFtdcMdSpi {

	Logger log = CommonLoggerFactory.getLogger(MdSpi.class);

	private CtpGateway ctpGateway;
	private String gatewayId;

	private HashMap<String, String> contractExchangeMap;
	private HashMap<String, String> contractNameMap;
	private HashMap<String, Integer> preTickVolumeMap = new HashMap<>();

	MdSpi(CtpGateway ctpGateway) {
		this.ctpGateway = ctpGateway;
		this.gatewayId = ctpGateway.getGatewayId();
	}

	// 前置机联机回报
	@Override
	public void OnFrontConnected() {
		log.info("{} MdApi front connected.", gatewayId);
		ctpGateway.onMdFrontConnected();
	}

	// 前置机断开回报
	@Override
	public void OnFrontDisconnected(int nReason) {
		log.info("{} MdApi front disconnected, Reason: {}", gatewayId, nReason);
		ctpGateway.onMdFrontDisconnected(nReason);
	}

	// 登录回报
	@Override
	public void OnRspUserLogin(CThostFtdcRspUserLoginField pRspUserLogin, CThostFtdcRspInfoField pRspInfo,
			int nRequestID, boolean bIsLast) {
		if (pRspInfo.getErrorID() == 0) {
			log.info("{} OnRspUserLogin, TradingDay:{}, SessionID:{}, BrokerID:{}, UserID:{}", gatewayId,
					pRspUserLogin.getTradingDay(), pRspUserLogin.getSessionID(), pRspUserLogin.getBrokerID(),
					pRspUserLogin.getUserID());
			ctpGateway.onMdRspUserLogin(true);
		} else {
			log.warn("{} OnRspUserLogin error, ErrorID:{}, ErrorMsg:{}", gatewayId, pRspInfo.getErrorID(),
					pRspInfo.getErrorMsg());
			ctpGateway.onMdRspUserLogin(false);
		}
	}

	// 心跳警告
	@Override
	public void OnHeartBeatWarning(int nTimeLapse) {
		log.warn(gatewayId + "行情接口心跳警告 nTimeLapse:" + nTimeLapse);
	}

	// 登出回报
	@Override
	public void OnRspUserLogout(CThostFtdcUserLogoutField pUserLogout, CThostFtdcRspInfoField pRspInfo, int nRequestID,
			boolean bIsLast) {
		if (pRspInfo.getErrorID() != 0)
			log.info("{} OnRspUserLogout, ErrorID: {}, ErrorMsg: {}", gatewayId, pRspInfo.getErrorID(),
					pRspInfo.getErrorMsg());
		else
			log.info("{} OnRspUserLogout, BrokerID: {}, UserID: {}", gatewayId, pUserLogout.getBrokerID(),
					pUserLogout.getUserID());
		// this.loginStatus = false;
		ctpGateway.onMdRspUserLogout();
	}

	// 错误回报
	@Override
	public void OnRspError(CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		log.info("{} 行情接口错误回报!ErrorID:{}, ErrorMsg:{}, RequestID:{}, isLast{}", gatewayId, pRspInfo.getErrorID(),
				pRspInfo.getErrorMsg(), nRequestID, bIsLast);
		ctpGateway.onMdRspError();
	}

	// 订阅合约回报
	@Override
	public void OnRspSubMarketData(CThostFtdcSpecificInstrumentField pSpecificInstrument,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		if (pRspInfo.getErrorID() == 0)
			log.info("{} OnRspSubMarketData! 订阅合约成功:{}", gatewayId, pSpecificInstrument.getInstrumentID());
		else
			log.error("{} OnRspSubMarketData! 订阅合约失败,ErrorID:{} ErrorMsg:{}", gatewayId, pRspInfo.getErrorID(),
					pRspInfo.getErrorMsg());
		ctpGateway.onRspSubMarketData();
	}

	// 退订合约回报
	@Override
	public void OnRspUnSubMarketData(CThostFtdcSpecificInstrumentField pSpecificInstrument,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		if (pRspInfo.getErrorID() == 0)
			log.info("{} OnRspSubMarketData! 退订合约成功:{}", gatewayId, pSpecificInstrument.getInstrumentID());
		else
			log.error("{} OnRspSubMarketData! 退订合约失败,ErrorID:{} ErrorMsg:{}", gatewayId, pRspInfo.getErrorID(),
					pRspInfo.getErrorMsg());
		ctpGateway.onRspUnSubMarketData();
	}

	// 合约行情推送
	@Override
	public void OnRtnDepthMarketData(CThostFtdcDepthMarketDataField pDepthMarketData) {
		if (pDepthMarketData != null) {
			String symbol = pDepthMarketData.getInstrumentID();
			System.out.println(pDepthMarketData.getLastPrice());
//			if (!contractExchangeMap.containsKey(symbol)) {
//				log.warn("{} 收到合约 {}行情,但尚未获取到交易所信息,丢弃", gatewayId, symbol);
//				return;
//			}
//			// 上期所 郑商所正常,大商所错误
//			// TODO 大商所时间修正
//			Long updateTime = Long.valueOf(pDepthMarketData.getUpdateTime().replaceAll(":", ""));
//			Long updateMillisec = (long) pDepthMarketData.getUpdateMillisec();
//			Long actionDay = Long.valueOf(pDepthMarketData.getActionDay());
//
//			String updateDateTimeWithMS = (actionDay * 100 * 100 * 100 * 1000 + updateTime * 1000 + updateMillisec)
//					+ "";
//
//			LocalDateTime dateTime = LocalDateTime.parse(updateDateTimeWithMS,
//					Constant.DT_FORMAT_WITH_MS_INT_FORMATTER);
//
//			String exchange = contractExchangeMap.get(symbol);
//			String rtSymbol = symbol + "." + exchange;
//			String contractName = contractNameMap.get(symbol);
//			String tickID = rtSymbol + "." + gatewayId;
//			// String tradingDay = tradingDayStr;
//			String actionDayStr = pDepthMarketData.getActionDay();
//			String actionTime = dateTime.toString();
//			Integer status = 0;
//			Double lastPrice = pDepthMarketData.getLastPrice();
//			Integer volume = pDepthMarketData.getVolume();
//			Integer lastVolume = 0;
//			if (preTickVolumeMap.containsKey(tickID)) {
//				lastVolume = volume - preTickVolumeMap.get(tickID);
//			} else {
//				lastVolume = volume;
//			}
//			preTickVolumeMap.put(tickID, volume);
//			Double openInterest = pDepthMarketData.getOpenInterest();
//			Long preOpenInterest = (long) pDepthMarketData.getPreOpenInterest();
//			Double preClosePrice = pDepthMarketData.getPreClosePrice();
//			Double preSettlePrice = pDepthMarketData.getPreSettlementPrice();
//			Double openPrice = pDepthMarketData.getOpenPrice();
//			Double highPrice = pDepthMarketData.getHighestPrice();
//			Double lowPrice = pDepthMarketData.getLowestPrice();
//			Double upperLimit = pDepthMarketData.getUpperLimitPrice();
//			Double lowerLimit = pDepthMarketData.getLowerLimitPrice();
//			Double bidPrice1 = pDepthMarketData.getBidPrice1();
//			Double bidPrice2 = pDepthMarketData.getBidPrice2();
//			Double bidPrice3 = pDepthMarketData.getBidPrice3();
//			Double bidPrice4 = pDepthMarketData.getBidPrice4();
//			Double bidPrice5 = pDepthMarketData.getBidPrice5();
//			Double bidPrice6 = 0d;
//			Double bidPrice7 = 0d;
//			Double bidPrice8 = 0d;
//			Double bidPrice9 = 0d;
//			Double bidPrice10 = 0d;
//			Double askPrice1 = pDepthMarketData.getAskPrice1();
//			Double askPrice2 = pDepthMarketData.getAskPrice2();
//			Double askPrice3 = pDepthMarketData.getAskPrice3();
//			Double askPrice4 = pDepthMarketData.getAskPrice4();
//			Double askPrice5 = pDepthMarketData.getAskPrice5();
//			Double askPrice6 = 0d;
//			Double askPrice7 = 0d;
//			Double askPrice8 = 0d;
//			Double askPrice9 = 0d;
//			Double askPrice10 = 0d;
//			Integer bidVolume1 = pDepthMarketData.getBidVolume1();
//			Integer bidVolume2 = pDepthMarketData.getBidVolume2();
//			Integer bidVolume3 = pDepthMarketData.getBidVolume3();
//			Integer bidVolume4 = pDepthMarketData.getBidVolume4();
//			Integer bidVolume5 = pDepthMarketData.getBidVolume5();
//			Integer bidVolume6 = 0;
//			Integer bidVolume7 = 0;
//			Integer bidVolume8 = 0;
//			Integer bidVolume9 = 0;
//			Integer bidVolume10 = 0;
//			Integer askVolume1 = pDepthMarketData.getAskVolume1();
//			Integer askVolume2 = pDepthMarketData.getAskVolume2();
//			Integer askVolume3 = pDepthMarketData.getAskVolume3();
//			Integer askVolume4 = pDepthMarketData.getAskVolume4();
//			Integer askVolume5 = pDepthMarketData.getAskVolume5();
//			Integer askVolume6 = 0;
//			Integer askVolume7 = 0;
//			Integer askVolume8 = 0;
//			Integer askVolume9 = 0;
//			Integer askVolume10 = 0;

		} else
			log.warn("{}OnRtnDepthMarketData! 收到行情信息为空", gatewayId);
		ctpGateway.onRtnDepthMarketData();
	}

	// 订阅期权询价
	@Override
	public void OnRspSubForQuoteRsp(CThostFtdcSpecificInstrumentField pSpecificInstrument,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		log.info("{} OnRspSubForQuoteRsp!", gatewayId);
	}

	// 退订期权询价
	@Override
	public void OnRspUnSubForQuoteRsp(CThostFtdcSpecificInstrumentField pSpecificInstrument,
			CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
		log.info("{} OnRspUnSubForQuoteRsp!", gatewayId);
	}

	// 期权询价推送
	@Override
	public void OnRtnForQuoteRsp(CThostFtdcForQuoteRspField pForQuoteRsp) {
		log.info("{} OnRspUnSubForQuoteRsp!", gatewayId);
	}

}