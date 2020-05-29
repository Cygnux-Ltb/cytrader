package io.mercury.ftdc.gateway.converter;

import java.util.function.Function;

import ctp.thostapi.CThostFtdcDepthMarketDataField;
import io.mercury.ftdc.gateway.bean.FtdcDepthMarketData;

public final class FromCThostFtdcDepthMarketData implements Function<CThostFtdcDepthMarketDataField, FtdcDepthMarketData> {

	@Override
	public FtdcDepthMarketData apply(CThostFtdcDepthMarketDataField from) {
		return new FtdcDepthMarketData()
				
				.setTradingDay(from.getTradingDay())
				
				.setInstrumentID(from.getInstrumentID())
				
				.setExchangeID(from.getExchangeID())
				
				.setExchangeInstID(from.getExchangeInstID())
				
				.setLastPrice(from.getLastPrice())
				
				.setPreSettlementPrice(from.getPreSettlementPrice())
				
				.setPreClosePrice(from.getPreClosePrice())
				
				.setPreOpenInterest(from.getPreOpenInterest())
				
				.setOpenPrice(from.getOpenPrice())
				
				.setHighestPrice(from.getHighestPrice())
				
				.setLowestPrice(from.getLowestPrice())
				
				.setVolume(from.getVolume())
				
				.setTurnover(from.getTurnover())
				
				.setOpenInterest(from.getOpenInterest())
				
				.setClosePrice(from.getClosePrice())
				
				.setSettlementPrice(from.getSettlementPrice())
				
				.setUpperLimitPrice(from.getUpperLimitPrice())
				
				.setLowerLimitPrice(from.getLowerLimitPrice())
				
				.setPreDelta(from.getPreDelta())
				
				.setCurrDelta(from.getCurrDelta())
				
				.setBidPrice1(from.getBidPrice1())
				
				.setBidVolume1(from.getBidVolume1())
				
				.setAskPrice1(from.getAskPrice1())
				
				.setAskVolume1(from.getAskVolume1())
				
				.setBidPrice2(from.getBidPrice2())
				
				.setBidVolume2(from.getBidVolume2())
				
				.setAskPrice2(from.getAskPrice2())
				
				.setAskVolume2(from.getAskVolume2())
				
				.setBidPrice3(from.getBidPrice3())
				
				.setBidVolume3(from.getBidVolume3())
				
				.setAskPrice3(from.getAskPrice3())
				
				.setAskVolume3(from.getAskVolume3())
				
				.setBidPrice4(from.getBidPrice4())
				
				.setBidVolume4(from.getBidVolume4())
				
				.setAskPrice4(from.getAskPrice4())
				
				.setAskVolume4(from.getAskVolume4())
				
				.setBidPrice5(from.getBidPrice5())
				
				.setBidVolume5(from.getBidVolume5())
				
				.setAskPrice5(from.getAskPrice5())
				
				.setAskVolume5(from.getAskVolume5())
				
				.setAveragePrice(from.getAveragePrice())
				
				.setUpdateTime(from.getUpdateTime())
				
				.setUpdateMillisec(from.getUpdateMillisec())
				
				.setActionDay(from.getActionDay());
		
	}

}
