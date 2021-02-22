package io.cygnus.restful.client;

import java.util.List;

import io.cygnus.restful.client.base.BaseApiClient;
import io.cygnus.restful.client.base.PathParam;
import io.cygnus.service.entity.SymbolInfo;
import io.cygnus.service.entity.SymbolTradingFee;
import io.cygnus.service.entity.SymbolTradingPeriod;
import io.cygnus.service.entity.TradeableInstrument;

public class SymbolApiClient extends BaseApiClient {

	private String baseUri = "/symbol";
	
	public List<SymbolInfo> getAllSymbol(){
		return super.getResultSet(SymbolInfo.class, baseUri);
	}

	public List<SymbolInfo> getSymbolByName(String symbol) {
		return super.getResultSet(SymbolInfo.class, baseUri + "/{symbol}", 
				new PathParam("symbol", symbol));
	}

	public List<SymbolTradingFee> getSymbolFeeByName(String symbol) {
		return super.getResultSet(SymbolTradingFee.class, baseUri + "/{symbol}/trading_fee",
				new PathParam("symbol", symbol));
	}

	public List<SymbolTradingPeriod> getSymbolTradingPeriodByName(String symbol) {
		return super.getResultSet(SymbolTradingPeriod.class, baseUri + "/{symbol}/trading_period",
				new PathParam("symbol", symbol));
	}

	public List<TradeableInstrument> getTradeableInstrumentByNameAndTradingDay(String symbol, String tradingDay) {
		return super.getResultSet(TradeableInstrument.class, baseUri + "/{symbol}/tradeable/{tradingDay}",
				new PathParam("symbol", symbol), 
				new PathParam("tradingDay", tradingDay));
	}

	public static void main(String[] args) {

	}

}
