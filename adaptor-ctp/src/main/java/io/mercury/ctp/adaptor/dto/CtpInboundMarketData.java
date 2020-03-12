package io.mercury.adaptor.ctp.dto;

/**
 * 
 * CTP MarketData
 * 
 * @author phoneix
 *
 */
public class CtpInboundMarketData {

	// 交易日
	private String TradingDay;

	// 交易标的
	private String InstrumentID;

	// 最新价
	private double LastPrice;

	// 昨结算价
	private double PreSettlementPrice;

	// 昨收盘价
	private double PreClosePrice;

	// 昨持仓量
	private int PreOpenInterest;

	// 开盘价
	private double OpenPrice;

	// 最高价
	private double HighestPrice;

	// 最低价
	private double LowestPrice;

	// 成交量
	private int Volume;

	// 成交金额
	private int Turnover;

	// 持仓量
	private int OpenInterest;

	// 收盘价
	private double ClosePrice;

	// 结算价
	private double SettlementPrice;

	// 涨停价
	private double UpperLimitPrice;

	// 跌停价
	private double LowerLimitPrice;

	// 昨Delta
	private double PreDelta;

	// 今Delta
	private double CurrDelta;

	// 更新时间
	private String UpdateTime;

	// 更新毫秒
	private int UpdateMillisec;

	// 买一价
	private double BidPrice1;

	// 买一量
	private int BidVolume1;

	// 卖一价
	private double AskPrice1;

	// 卖一量
	private int AskVolume1;

	// 买二价
	private double BidPrice2;

	// 买二量
	private int BidVolume2;

	// 卖二价
	private double AskPrice2;

	// 卖二量
	private int AskVolume2;

	// 买三价
	private double BidPrice3;

	// 买三量
	private int BidVolume3;

	// 卖三价
	private double AskPrice3;

	// 卖三量
	private int AskVolume3;

	// 买四价
	private double BidPrice4;

	// 买四量
	private int BidVolume4;

	// 卖四价
	private double AskPrice4;

	// 卖四量
	private int AskVolume4;

	// 买五价
	private double BidPrice5;

	// 买五量
	private int BidVolume5;

	// 卖五价
	private double AskPrice5;

	// 卖五量
	private int AskVolume5;

	// 平均价
	private double AveragePrice;

	// 业务日期
	private String ActionDay;

	public String getTradingDay() {
		return TradingDay;
	}

	public CtpInboundMarketData setTradingDay(String tradingDay) {
		TradingDay = tradingDay;
		return this;
	}

	public String getInstrumentID() {
		return InstrumentID;
	}

	public CtpInboundMarketData setInstrumentID(String instrumentID) {
		InstrumentID = instrumentID;
		return this;
	}

	public double getLastPrice() {
		return LastPrice;
	}

	public CtpInboundMarketData setLastPrice(double lastPrice) {
		LastPrice = lastPrice;
		return this;
	}

	public double getPreSettlementPrice() {
		return PreSettlementPrice;
	}

	public CtpInboundMarketData setPreSettlementPrice(double preSettlementPrice) {
		PreSettlementPrice = preSettlementPrice;
		return this;
	}

	public double getPreClosePrice() {
		return PreClosePrice;
	}

	public CtpInboundMarketData setPreClosePrice(double preClosePrice) {
		PreClosePrice = preClosePrice;
		return this;
	}

	public int getPreOpenInterest() {
		return PreOpenInterest;
	}

	public CtpInboundMarketData setPreOpenInterest(int preOpenInterest) {
		PreOpenInterest = preOpenInterest;
		return this;
	}

	public double getOpenPrice() {
		return OpenPrice;
	}

	public CtpInboundMarketData setOpenPrice(double openPrice) {
		OpenPrice = openPrice;
		return this;
	}

	public double getHighestPrice() {
		return HighestPrice;
	}

	public CtpInboundMarketData setHighestPrice(double highestPrice) {
		HighestPrice = highestPrice;
		return this;
	}

	public double getLowestPrice() {
		return LowestPrice;
	}

	public CtpInboundMarketData setLowestPrice(double lowestPrice) {
		LowestPrice = lowestPrice;
		return this;
	}

	public int getVolume() {
		return Volume;
	}

	public CtpInboundMarketData setVolume(int volume) {
		Volume = volume;
		return this;
	}

	public int getTurnover() {
		return Turnover;
	}

	public CtpInboundMarketData setTurnover(int turnover) {
		Turnover = turnover;
		return this;
	}

	public int getOpenInterest() {
		return OpenInterest;
	}

	public CtpInboundMarketData setOpenInterest(int openInterest) {
		OpenInterest = openInterest;
		return this;
	}

	public double getClosePrice() {
		return ClosePrice;
	}

	public CtpInboundMarketData setClosePrice(double closePrice) {
		ClosePrice = closePrice;
		return this;
	}

	public double getSettlementPrice() {
		return SettlementPrice;
	}

	public CtpInboundMarketData setSettlementPrice(double settlementPrice) {
		SettlementPrice = settlementPrice;
		return this;
	}

	public double getUpperLimitPrice() {
		return UpperLimitPrice;
	}

	public CtpInboundMarketData setUpperLimitPrice(double upperLimitPrice) {
		UpperLimitPrice = upperLimitPrice;
		return this;
	}

	public double getLowerLimitPrice() {
		return LowerLimitPrice;
	}

	public CtpInboundMarketData setLowerLimitPrice(double lowerLimitPrice) {
		LowerLimitPrice = lowerLimitPrice;
		return this;
	}

	public double getPreDelta() {
		return PreDelta;
	}

	public CtpInboundMarketData setPreDelta(double preDelta) {
		PreDelta = preDelta;
		return this;
	}

	public double getCurrDelta() {
		return CurrDelta;
	}

	public CtpInboundMarketData setCurrDelta(double currDelta) {
		CurrDelta = currDelta;
		return this;
	}

	public String getUpdateTime() {
		return UpdateTime;
	}

	public CtpInboundMarketData setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
		return this;
	}

	public int getUpdateMillisec() {
		return UpdateMillisec;
	}

	public CtpInboundMarketData setUpdateMillisec(int updateMillisec) {
		UpdateMillisec = updateMillisec;
		return this;
	}

	public double getBidPrice1() {
		return BidPrice1;
	}

	public CtpInboundMarketData setBidPrice1(double bidPrice1) {
		BidPrice1 = bidPrice1;
		return this;
	}

	public int getBidVolume1() {
		return BidVolume1;
	}

	public CtpInboundMarketData setBidVolume1(int bidVolume1) {
		BidVolume1 = bidVolume1;
		return this;
	}

	public double getAskPrice1() {
		return AskPrice1;
	}

	public CtpInboundMarketData setAskPrice1(double askPrice1) {
		AskPrice1 = askPrice1;
		return this;
	}

	public int getAskVolume1() {
		return AskVolume1;
	}

	public CtpInboundMarketData setAskVolume1(int askVolume1) {
		AskVolume1 = askVolume1;
		return this;
	}

	public double getBidPrice2() {
		return BidPrice2;
	}

	public CtpInboundMarketData setBidPrice2(double bidPrice2) {
		BidPrice2 = bidPrice2;
		return this;
	}

	public int getBidVolume2() {
		return BidVolume2;
	}

	public CtpInboundMarketData setBidVolume2(int bidVolume2) {
		BidVolume2 = bidVolume2;
		return this;
	}

	public double getAskPrice2() {
		return AskPrice2;
	}

	public CtpInboundMarketData setAskPrice2(double askPrice2) {
		AskPrice2 = askPrice2;
		return this;
	}

	public int getAskVolume2() {
		return AskVolume2;
	}

	public CtpInboundMarketData setAskVolume2(int askVolume2) {
		AskVolume2 = askVolume2;
		return this;
	}

	public double getBidPrice3() {
		return BidPrice3;
	}

	public CtpInboundMarketData setBidPrice3(double bidPrice3) {
		BidPrice3 = bidPrice3;
		return this;
	}

	public int getBidVolume3() {
		return BidVolume3;
	}

	public CtpInboundMarketData setBidVolume3(int bidVolume3) {
		BidVolume3 = bidVolume3;
		return this;
	}

	public double getAskPrice3() {
		return AskPrice3;
	}

	public CtpInboundMarketData setAskPrice3(double askPrice3) {
		AskPrice3 = askPrice3;
		return this;
	}

	public int getAskVolume3() {
		return AskVolume3;
	}

	public CtpInboundMarketData setAskVolume3(int askVolume3) {
		AskVolume3 = askVolume3;
		return this;
	}

	public double getBidPrice4() {
		return BidPrice4;
	}

	public CtpInboundMarketData setBidPrice4(double bidPrice4) {
		BidPrice4 = bidPrice4;
		return this;
	}

	public int getBidVolume4() {
		return BidVolume4;
	}

	public CtpInboundMarketData setBidVolume4(int bidVolume4) {
		BidVolume4 = bidVolume4;
		return this;
	}

	public double getAskPrice4() {
		return AskPrice4;
	}

	public CtpInboundMarketData setAskPrice4(double askPrice4) {
		AskPrice4 = askPrice4;
		return this;
	}

	public int getAskVolume4() {
		return AskVolume4;
	}

	public CtpInboundMarketData setAskVolume4(int askVolume4) {
		AskVolume4 = askVolume4;
		return this;
	}

	public double getBidPrice5() {
		return BidPrice5;
	}

	public CtpInboundMarketData setBidPrice5(double bidPrice5) {
		BidPrice5 = bidPrice5;
		return this;
	}

	public int getBidVolume5() {
		return BidVolume5;
	}

	public CtpInboundMarketData setBidVolume5(int bidVolume5) {
		BidVolume5 = bidVolume5;
		return this;
	}

	public double getAskPrice5() {
		return AskPrice5;
	}

	public CtpInboundMarketData setAskPrice5(double askPrice5) {
		AskPrice5 = askPrice5;
		return this;
	}

	public int getAskVolume5() {
		return AskVolume5;
	}

	public CtpInboundMarketData setAskVolume5(int askVolume5) {
		AskVolume5 = askVolume5;
		return this;
	}

	public double getAveragePrice() {
		return AveragePrice;
	}

	public CtpInboundMarketData setAveragePrice(double averagePrice) {
		AveragePrice = averagePrice;
		return this;
	}

	public String getActionDay() {
		return ActionDay;
	}

	public CtpInboundMarketData setActionDay(String actionDay) {
		ActionDay = actionDay;
		return this;
	}

	public static void main(String[] args) {

	}

}
