package io.mercury.financial.market.impl;

import io.mercury.financial.market.api.MarketData;

public final class SysMarketData implements MarketData {

	// 交易日
	private String TradingDay;
	// InstrumentID
	private String InstrumentID;
	// 交易所ID
	private String ExchangeID;
	// 最新价
	private double LastPrice;
	// 上次结算价
	private double PreSettlementPrice;
	// 昨收盘
	private double PreClosePrice;
	// 昨持仓量
	private double PreOpenInterest;
	// 开盘价
	private double OpenPrice;
	// 最高价
	private double HighestPrice;
	// 最低价
	private double LowestPrice;
	// 成交量
	private int Volume;
	// 成交金额
	private double Turnover;
	// 持仓量
	private double OpenInterest;
	// 涨停板价
	private double UpperLimitPrice;
	// 跌停板价
	private double LowerLimitPrice;
	/* 五档买价卖价及买量卖量 v */
	private double BidPrice1;
	private int BidVolume1;
	private double AskPrice1;
	private int AskVolume1;
	private double BidPrice2;
	private int BidVolume2;
	private double AskPrice2;
	private int AskVolume2;
	private double BidPrice3;
	private int BidVolume3;
	private double AskPrice3;
	private int AskVolume3;
	private double BidPrice4;
	private int BidVolume4;
	private double AskPrice4;
	private int AskVolume4;
	private double BidPrice5;
	private int BidVolume5;
	private double AskPrice5;
	private int AskVolume5;
	/* 五档买价卖价及买量卖量 ^ */
	// 平均价格
	private double AveragePrice;
	// 更新时间
	private String UpdateTime;
	// 更新毫秒数
	private int UpdateMillisec;

	public String getTradingDay() {
		return TradingDay;
	}

	public SysMarketData setTradingDay(String tradingDay) {
		TradingDay = tradingDay;
		return this;
	}

	public String getInstrumentID() {
		return InstrumentID;
	}

	public SysMarketData setInstrumentID(String instrumentID) {
		InstrumentID = instrumentID;
		return this;
	}

	public String getExchangeID() {
		return ExchangeID;
	}

	public SysMarketData setExchangeID(String exchangeID) {
		ExchangeID = exchangeID;
		return this;
	}

	public double getLastPrice() {
		return LastPrice;
	}

	public SysMarketData setLastPrice(double lastPrice) {
		LastPrice = lastPrice;
		return this;
	}

	public double getPreSettlementPrice() {
		return PreSettlementPrice;
	}

	public SysMarketData setPreSettlementPrice(double preSettlementPrice) {
		PreSettlementPrice = preSettlementPrice;
		return this;
	}

	public double getPreClosePrice() {
		return PreClosePrice;
	}

	public SysMarketData setPreClosePrice(double preClosePrice) {
		PreClosePrice = preClosePrice;
		return this;
	}

	public double getPreOpenInterest() {
		return PreOpenInterest;
	}

	public SysMarketData setPreOpenInterest(double preOpenInterest) {
		PreOpenInterest = preOpenInterest;
		return this;
	}

	public double getOpenPrice() {
		return OpenPrice;
	}

	public SysMarketData setOpenPrice(double openPrice) {
		OpenPrice = openPrice;
		return this;
	}

	public double getHighestPrice() {
		return HighestPrice;
	}

	public SysMarketData setHighestPrice(double highestPrice) {
		HighestPrice = highestPrice;
		return this;
	}

	public double getLowestPrice() {
		return LowestPrice;
	}

	public SysMarketData setLowestPrice(double lowestPrice) {
		LowestPrice = lowestPrice;
		return this;
	}

	public int getVolume() {
		return Volume;
	}

	public SysMarketData setVolume(int volume) {
		Volume = volume;
		return this;
	}

	public double getTurnover() {
		return Turnover;
	}

	public SysMarketData setTurnover(double turnover) {
		Turnover = turnover;
		return this;
	}

	public double getOpenInterest() {
		return OpenInterest;
	}

	public SysMarketData setOpenInterest(double openInterest) {
		OpenInterest = openInterest;
		return this;
	}

	public double getUpperLimitPrice() {
		return UpperLimitPrice;
	}

	public SysMarketData setUpperLimitPrice(double upperLimitPrice) {
		UpperLimitPrice = upperLimitPrice;
		return this;
	}

	public double getLowerLimitPrice() {
		return LowerLimitPrice;
	}

	public SysMarketData setLowerLimitPrice(double lowerLimitPrice) {
		LowerLimitPrice = lowerLimitPrice;
		return this;
	}

	public double getBidPrice1() {
		return BidPrice1;
	}

	public SysMarketData setBidPrice1(double bidPrice1) {
		BidPrice1 = bidPrice1;
		return this;
	}

	public int getBidVolume1() {
		return BidVolume1;
	}

	public SysMarketData setBidVolume1(int bidVolume1) {
		BidVolume1 = bidVolume1;
		return this;
	}

	public double getAskPrice1() {
		return AskPrice1;
	}

	public SysMarketData setAskPrice1(double askPrice1) {
		AskPrice1 = askPrice1;
		return this;
	}

	public int getAskVolume1() {
		return AskVolume1;
	}

	public SysMarketData setAskVolume1(int askVolume1) {
		AskVolume1 = askVolume1;
		return this;
	}

	public double getBidPrice2() {
		return BidPrice2;
	}

	public SysMarketData setBidPrice2(double bidPrice2) {
		BidPrice2 = bidPrice2;
		return this;
	}

	public int getBidVolume2() {
		return BidVolume2;
	}

	public SysMarketData setBidVolume2(int bidVolume2) {
		BidVolume2 = bidVolume2;
		return this;
	}

	public double getAskPrice2() {
		return AskPrice2;
	}

	public SysMarketData setAskPrice2(double askPrice2) {
		AskPrice2 = askPrice2;
		return this;
	}

	public int getAskVolume2() {
		return AskVolume2;
	}

	public SysMarketData setAskVolume2(int askVolume2) {
		AskVolume2 = askVolume2;
		return this;
	}

	public double getBidPrice3() {
		return BidPrice3;
	}

	public SysMarketData setBidPrice3(double bidPrice3) {
		BidPrice3 = bidPrice3;
		return this;
	}

	public int getBidVolume3() {
		return BidVolume3;
	}

	public SysMarketData setBidVolume3(int bidVolume3) {
		BidVolume3 = bidVolume3;
		return this;
	}

	public double getAskPrice3() {
		return AskPrice3;
	}

	public SysMarketData setAskPrice3(double askPrice3) {
		AskPrice3 = askPrice3;
		return this;
	}

	public int getAskVolume3() {
		return AskVolume3;
	}

	public SysMarketData setAskVolume3(int askVolume3) {
		AskVolume3 = askVolume3;
		return this;
	}

	public double getBidPrice4() {
		return BidPrice4;
	}

	public SysMarketData setBidPrice4(double bidPrice4) {
		BidPrice4 = bidPrice4;
		return this;
	}

	public int getBidVolume4() {
		return BidVolume4;
	}

	public SysMarketData setBidVolume4(int bidVolume4) {
		BidVolume4 = bidVolume4;
		return this;
	}

	public double getAskPrice4() {
		return AskPrice4;
	}

	public SysMarketData setAskPrice4(double askPrice4) {
		AskPrice4 = askPrice4;
		return this;
	}

	public int getAskVolume4() {
		return AskVolume4;
	}

	public SysMarketData setAskVolume4(int askVolume4) {
		AskVolume4 = askVolume4;
		return this;
	}

	public double getBidPrice5() {
		return BidPrice5;
	}

	public SysMarketData setBidPrice5(double bidPrice5) {
		BidPrice5 = bidPrice5;
		return this;
	}

	public int getBidVolume5() {
		return BidVolume5;
	}

	public SysMarketData setBidVolume5(int bidVolume5) {
		BidVolume5 = bidVolume5;
		return this;
	}

	public double getAskPrice5() {
		return AskPrice5;
	}

	public SysMarketData setAskPrice5(double askPrice5) {
		AskPrice5 = askPrice5;
		return this;
	}

	public int getAskVolume5() {
		return AskVolume5;
	}

	public SysMarketData setAskVolume5(int askVolume5) {
		AskVolume5 = askVolume5;
		return this;
	}

	public double getAveragePrice() {
		return AveragePrice;
	}

	public SysMarketData setAveragePrice(double averagePrice) {
		AveragePrice = averagePrice;
		return this;
	}

	public String getUpdateTime() {
		return UpdateTime;
	}

	public SysMarketData setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
		return this;
	}

	public int getUpdateMillisec() {
		return UpdateMillisec;
	}

	public SysMarketData setUpdateMillisec(int updateMillisec) {
		UpdateMillisec = updateMillisec;
		return this;
	}

	@Override
	public MarketDataType marketDataType() {
		// TODO Auto-generated method stub
		return null;
	}

}
