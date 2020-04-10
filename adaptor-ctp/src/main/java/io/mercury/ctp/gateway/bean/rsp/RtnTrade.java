package io.mercury.ftdc.gateway.bean.rsp;

public class RtnTrade {

	private String BrokerID;
	private String InvestorID;
	private String InstrumentID;
	private String OrderRef;
	private String UserID;
	private String ExchangeID;
	private String TradeID;
	private char Direction;
	private String OrderSysID;
	private String ParticipantID;
	private String ClientID;
	private char TradingRole;
	private String ExchangeInstID;
	private char OffsetFlag;
	private char HedgeFlag;
	private double Price;
	private int Volume;
	private String TradeDate;
	private String TradeTime;
	private char TradeType;
	private char PriceSource;
	private String TraderID;
	private String OrderLocalID;
	private String ClearingPartID;
	private String BusinessUnit;
	private int SequenceNo;
	private String TradingDay;
	private int SettlementID;
	private int BrokerOrderSeq;
	private char TradeSource;
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

	public RtnTrade setBrokerID(String brokerID) {
		BrokerID = brokerID;
		return this;
	}

	public RtnTrade setInvestorID(String investorID) {
		InvestorID = investorID;
		return this;
	}

	public RtnTrade setInstrumentID(String instrumentID) {
		InstrumentID = instrumentID;
		return this;
	}

	public RtnTrade setOrderRef(String orderRef) {
		OrderRef = orderRef;
		return this;
	}

	public RtnTrade setUserID(String userID) {
		UserID = userID;
		return this;
	}

	public RtnTrade setExchangeID(String exchangeID) {
		ExchangeID = exchangeID;
		return this;
	}

	public RtnTrade setTradeID(String tradeID) {
		TradeID = tradeID;
		return this;
	}

	public RtnTrade setDirection(char direction) {
		Direction = direction;
		return this;
	}

	public RtnTrade setOrderSysID(String orderSysID) {
		OrderSysID = orderSysID;
		return this;
	}

	public RtnTrade setParticipantID(String participantID) {
		ParticipantID = participantID;
		return this;
	}

	public RtnTrade setClientID(String clientID) {
		ClientID = clientID;
		return this;
	}

	public RtnTrade setTradingRole(char tradingRole) {
		TradingRole = tradingRole;
		return this;
	}

	public RtnTrade setExchangeInstID(String exchangeInstID) {
		ExchangeInstID = exchangeInstID;
		return this;
	}

	public RtnTrade setOffsetFlag(char offsetFlag) {
		OffsetFlag = offsetFlag;
		return this;
	}

	public RtnTrade setHedgeFlag(char hedgeFlag) {
		HedgeFlag = hedgeFlag;
		return this;
	}

	public RtnTrade setPrice(double price) {
		Price = price;
		return this;
	}

	public RtnTrade setVolume(int volume) {
		Volume = volume;
		return this;
	}

	public RtnTrade setTradeDate(String tradeDate) {
		TradeDate = tradeDate;
		return this;
	}

	public RtnTrade setTradeTime(String tradeTime) {
		TradeTime = tradeTime;
		return this;
	}

	public RtnTrade setTradeType(char tradeType) {
		TradeType = tradeType;
		return this;
	}

	public RtnTrade setPriceSource(char priceSource) {
		PriceSource = priceSource;
		return this;
	}

	public RtnTrade setTraderID(String traderID) {
		TraderID = traderID;
		return this;
	}

	public RtnTrade setOrderLocalID(String orderLocalID) {
		OrderLocalID = orderLocalID;
		return this;
	}

	public RtnTrade setClearingPartID(String clearingPartID) {
		ClearingPartID = clearingPartID;
		return this;
	}

	public RtnTrade setBusinessUnit(String businessUnit) {
		BusinessUnit = businessUnit;
		return this;
	}

	public RtnTrade setSequenceNo(int sequenceNo) {
		SequenceNo = sequenceNo;
		return this;
	}

	public RtnTrade setTradingDay(String tradingDay) {
		TradingDay = tradingDay;
		return this;
	}

	public RtnTrade setSettlementID(int settlementID) {
		SettlementID = settlementID;
		return this;
	}

	public RtnTrade setBrokerOrderSeq(int brokerOrderSeq) {
		BrokerOrderSeq = brokerOrderSeq;
		return this;
	}

	public RtnTrade setTradeSource(char tradeSource) {
		TradeSource = tradeSource;
		return this;
	}

	public RtnTrade setInvestUnitID(String investUnitID) {
		InvestUnitID = investUnitID;
		return this;
	}

}
