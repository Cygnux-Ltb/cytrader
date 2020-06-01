package io.mercury.ftdc.gateway.bean;

public final class FtdcTrade {

	/// 经纪公司代码
	private String BrokerID;

	/// 投资者代码
	private String InvestorID;

	/// 合约代码
	private String InstrumentID;

	/// 报单引用
	private String OrderRef;

	/// 用户代码
	private String UserID;

	/// 交易所代码
	private String ExchangeID;

	/// 成交编号
	private String TradeID;

	/// 买卖方向
	private char Direction;

	/// 报单编号
	private String OrderSysID;

	/// 会员代码
	private String ParticipantID;

	/// 客户代码
	private String ClientID;

	/// 交易角色
	private char TradingRole;

	/// 合约在交易所的代码
	private String ExchangeInstID;

	/// 开平标志
	private char OffsetFlag;

	/// 投机套保标志
	private char HedgeFlag;

	/// 价格
	private double Price;

	/// 数量
	private int Volume;

	/// 成交时期
	private String TradeDate;

	/// 成交时间
	private String TradeTime;

	/// 成交类型
	private char TradeType;

	/// 成交价来源
	private char PriceSource;

	/// 交易所交易员代码
	private String TraderID;

	/// 本地报单编号
	private String OrderLocalID;

	/// 结算会员编号
	private String ClearingPartID;

	/// 业务单元
	private String BusinessUnit;

	/// 序号
	private int SequenceNo;

	/// 交易日
	private String TradingDay;

	/// 结算编号
	private int SettlementID;

	/// 经纪公司报单编号
	private int BrokerOrderSeq;

	/// 成交来源
	private char TradeSource;

	/// 投资单元代码
	private String InvestUnitID;

	public String getBrokerID() {
		return BrokerID;
	}

	public String getInvestorID() {
		return InvestorID;
	}

	public String getInstrumentID() {
		return InstrumentID;
	}

	public String getOrderRef() {
		return OrderRef;
	}

	public String getUserID() {
		return UserID;
	}

	public String getExchangeID() {
		return ExchangeID;
	}

	public String getTradeID() {
		return TradeID;
	}

	public char getDirection() {
		return Direction;
	}

	public String getOrderSysID() {
		return OrderSysID;
	}

	public String getParticipantID() {
		return ParticipantID;
	}

	public String getClientID() {
		return ClientID;
	}

	public char getTradingRole() {
		return TradingRole;
	}

	public String getExchangeInstID() {
		return ExchangeInstID;
	}

	public char getOffsetFlag() {
		return OffsetFlag;
	}

	public char getHedgeFlag() {
		return HedgeFlag;
	}

	public double getPrice() {
		return Price;
	}

	public int getVolume() {
		return Volume;
	}

	public String getTradeDate() {
		return TradeDate;
	}

	public String getTradeTime() {
		return TradeTime;
	}

	public char getTradeType() {
		return TradeType;
	}

	public char getPriceSource() {
		return PriceSource;
	}

	public String getTraderID() {
		return TraderID;
	}

	public String getOrderLocalID() {
		return OrderLocalID;
	}

	public String getClearingPartID() {
		return ClearingPartID;
	}

	public String getBusinessUnit() {
		return BusinessUnit;
	}

	public int getSequenceNo() {
		return SequenceNo;
	}

	public String getTradingDay() {
		return TradingDay;
	}

	public int getSettlementID() {
		return SettlementID;
	}

	public int getBrokerOrderSeq() {
		return BrokerOrderSeq;
	}

	public char getTradeSource() {
		return TradeSource;
	}

	public String getInvestUnitID() {
		return InvestUnitID;
	}

	public FtdcTrade setBrokerID(String brokerID) {
		BrokerID = brokerID;
		return this;
	}

	public FtdcTrade setInvestorID(String investorID) {
		InvestorID = investorID;
		return this;
	}

	public FtdcTrade setInstrumentID(String instrumentID) {
		InstrumentID = instrumentID;
		return this;
	}

	public FtdcTrade setOrderRef(String orderRef) {
		OrderRef = orderRef;
		return this;
	}

	public FtdcTrade setUserID(String userID) {
		UserID = userID;
		return this;
	}

	public FtdcTrade setExchangeID(String exchangeID) {
		ExchangeID = exchangeID;
		return this;
	}

	public FtdcTrade setTradeID(String tradeID) {
		TradeID = tradeID;
		return this;
	}

	public FtdcTrade setDirection(char direction) {
		Direction = direction;
		return this;
	}

	public FtdcTrade setOrderSysID(String orderSysID) {
		OrderSysID = orderSysID;
		return this;
	}

	public FtdcTrade setParticipantID(String participantID) {
		ParticipantID = participantID;
		return this;
	}

	public FtdcTrade setClientID(String clientID) {
		ClientID = clientID;
		return this;
	}

	public FtdcTrade setTradingRole(char tradingRole) {
		TradingRole = tradingRole;
		return this;
	}

	public FtdcTrade setExchangeInstID(String exchangeInstID) {
		ExchangeInstID = exchangeInstID;
		return this;
	}

	public FtdcTrade setOffsetFlag(char offsetFlag) {
		OffsetFlag = offsetFlag;
		return this;
	}

	public FtdcTrade setHedgeFlag(char hedgeFlag) {
		HedgeFlag = hedgeFlag;
		return this;
	}

	public FtdcTrade setPrice(double price) {
		Price = price;
		return this;
	}

	public FtdcTrade setVolume(int volume) {
		Volume = volume;
		return this;
	}

	public FtdcTrade setTradeDate(String tradeDate) {
		TradeDate = tradeDate;
		return this;
	}

	public FtdcTrade setTradeTime(String tradeTime) {
		TradeTime = tradeTime;
		return this;
	}

	public FtdcTrade setTradeType(char tradeType) {
		TradeType = tradeType;
		return this;
	}

	public FtdcTrade setPriceSource(char priceSource) {
		PriceSource = priceSource;
		return this;
	}

	public FtdcTrade setTraderID(String traderID) {
		TraderID = traderID;
		return this;
	}

	public FtdcTrade setOrderLocalID(String orderLocalID) {
		OrderLocalID = orderLocalID;
		return this;
	}

	public FtdcTrade setClearingPartID(String clearingPartID) {
		ClearingPartID = clearingPartID;
		return this;
	}

	public FtdcTrade setBusinessUnit(String businessUnit) {
		BusinessUnit = businessUnit;
		return this;
	}

	public FtdcTrade setSequenceNo(int sequenceNo) {
		SequenceNo = sequenceNo;
		return this;
	}

	public FtdcTrade setTradingDay(String tradingDay) {
		TradingDay = tradingDay;
		return this;
	}

	public FtdcTrade setSettlementID(int settlementID) {
		SettlementID = settlementID;
		return this;
	}

	public FtdcTrade setBrokerOrderSeq(int brokerOrderSeq) {
		BrokerOrderSeq = brokerOrderSeq;
		return this;
	}

	public FtdcTrade setTradeSource(char tradeSource) {
		TradeSource = tradeSource;
		return this;
	}

	public FtdcTrade setInvestUnitID(String investUnitID) {
		InvestUnitID = investUnitID;
		return this;
	}

}
