package io.mercury.ctp.adaptor.dto;

/**
 * 
 * CTP RtnTrade
 * 
 * @author phoneix
 *
 */
public class CtpInboundRtnTrade {

	// 经纪公司代码
	private String BrokerID;

	// 投资者代码
	private String InvestorID;

	// 合约代码
	private String InstrumentID;

	// 报单引用
	private int OrderRef;

	// 用户代码
	private String UserID;

	// 交易所代码
	private String ExchangeID;

	// 成交编号
	private String TradeID;

	// 买卖方向
	private char Direction;

	// 报单编号
	private String OrderSysID;

	// 会员代码
	private String ParticipantID;

	// 客户代码
	private String ClientID;

	// 交易角色
	private char TradingRole;

	// 合约在交易所的代码
	private String ExchangeInstID;

	// 开平标志
	private char OffsetFlag;

	// 投机套保标志
	private char HedgeFlag;

	// 价格
	private double Price;

	// 数量
	private int Volume;

	// 成交时期
	private String TradeDate;

	// 成交时间
	private String TradeTime;

	// 成交类型
	private char TradeType;

	// 成交价来源
	private char PriceSource;

	// 交易所交易员代码
	private String TraderID;

	// 本地报单编号
	private String OrderLocalID;

	// 结算会员编号
	private String ClearingPartID;

	// 业务单元
	private String BusinessUnit;

	// 序号
	private int SequenceNo;

	// 交易日
	private String TradingDay;

	// 结算编号
	private int SettlementID;

	// 经纪公司报单编号
	private int BrokerOrderSeq;

	// 成交来源
	private char TradeSource;

	// 投资单元代码
	private String InvestUnitID;

	public String getBrokerID() {
		return BrokerID;
	}

	public CtpInboundRtnTrade setBrokerID(String brokerID) {
		BrokerID = brokerID;
		return this;
	}

	public String getInvestorID() {
		return InvestorID;
	}

	public CtpInboundRtnTrade setInvestorID(String investorID) {
		InvestorID = investorID;
		return this;
	}

	public String getInstrumentID() {
		return InstrumentID;
	}

	public CtpInboundRtnTrade setInstrumentID(String instrumentID) {
		InstrumentID = instrumentID;
		return this;
	}

	public int getOrderRef() {
		return OrderRef;
	}

	public CtpInboundRtnTrade setOrderRef(int orderRef) {
		OrderRef = orderRef;
		return this;
	}

	public String getUserID() {
		return UserID;
	}

	public CtpInboundRtnTrade setUserID(String userID) {
		UserID = userID;
		return this;
	}

	public String getExchangeID() {
		return ExchangeID;
	}

	public CtpInboundRtnTrade setExchangeID(String exchangeID) {
		ExchangeID = exchangeID;
		return this;
	}

	public String getTradeID() {
		return TradeID;
	}

	public CtpInboundRtnTrade setTradeID(String tradeID) {
		TradeID = tradeID;
		return this;
	}

	public char getDirection() {
		return Direction;
	}

	public CtpInboundRtnTrade setDirection(char direction) {
		Direction = direction;
		return this;
	}

	public String getOrderSysID() {
		return OrderSysID;
	}

	public CtpInboundRtnTrade setOrderSysID(String orderSysID) {
		OrderSysID = orderSysID;
		return this;
	}

	public String getParticipantID() {
		return ParticipantID;
	}

	public CtpInboundRtnTrade setParticipantID(String participantID) {
		ParticipantID = participantID;
		return this;
	}

	public String getClientID() {
		return ClientID;
	}

	public CtpInboundRtnTrade setClientID(String clientID) {
		ClientID = clientID;
		return this;
	}

	public char getTradingRole() {
		return TradingRole;
	}

	public CtpInboundRtnTrade setTradingRole(char tradingRole) {
		TradingRole = tradingRole;
		return this;
	}

	public String getExchangeInstID() {
		return ExchangeInstID;
	}

	public CtpInboundRtnTrade setExchangeInstID(String exchangeInstID) {
		ExchangeInstID = exchangeInstID;
		return this;
	}

	public char getOffsetFlag() {
		return OffsetFlag;
	}

	public CtpInboundRtnTrade setOffsetFlag(char offsetFlag) {
		OffsetFlag = offsetFlag;
		return this;
	}

	public char getHedgeFlag() {
		return HedgeFlag;
	}

	public CtpInboundRtnTrade setHedgeFlag(char hedgeFlag) {
		HedgeFlag = hedgeFlag;
		return this;
	}

	public double getPrice() {
		return Price;
	}

	public CtpInboundRtnTrade setPrice(double price) {
		Price = price;
		return this;
	}

	public int getVolume() {
		return Volume;
	}

	public CtpInboundRtnTrade setVolume(int volume) {
		Volume = volume;
		return this;
	}

	public String getTradeDate() {
		return TradeDate;
	}

	public CtpInboundRtnTrade setTradeDate(String tradeDate) {
		TradeDate = tradeDate;
		return this;
	}

	public String getTradeTime() {
		return TradeTime;
	}

	public CtpInboundRtnTrade setTradeTime(String tradeTime) {
		TradeTime = tradeTime;
		return this;
	}

	public char getTradeType() {
		return TradeType;
	}

	public CtpInboundRtnTrade setTradeType(char tradeType) {
		TradeType = tradeType;
		return this;
	}

	public char getPriceSource() {
		return PriceSource;
	}

	public CtpInboundRtnTrade setPriceSource(char priceSource) {
		PriceSource = priceSource;
		return this;
	}

	public String getTraderID() {
		return TraderID;
	}

	public CtpInboundRtnTrade setTraderID(String traderID) {
		TraderID = traderID;
		return this;
	}

	public String getOrderLocalID() {
		return OrderLocalID;
	}

	public CtpInboundRtnTrade setOrderLocalID(String orderLocalID) {
		OrderLocalID = orderLocalID;
		return this;
	}

	public String getClearingPartID() {
		return ClearingPartID;
	}

	public CtpInboundRtnTrade setClearingPartID(String clearingPartID) {
		ClearingPartID = clearingPartID;
		return this;
	}

	public String getBusinessUnit() {
		return BusinessUnit;
	}

	public CtpInboundRtnTrade setBusinessUnit(String businessUnit) {
		BusinessUnit = businessUnit;
		return this;
	}

	public int getSequenceNo() {
		return SequenceNo;
	}

	public CtpInboundRtnTrade setSequenceNo(int sequenceNo) {
		SequenceNo = sequenceNo;
		return this;
	}

	public String getTradingDay() {
		return TradingDay;
	}

	public CtpInboundRtnTrade setTradingDay(String tradingDay) {
		TradingDay = tradingDay;
		return this;
	}

	public int getSettlementID() {
		return SettlementID;
	}

	public CtpInboundRtnTrade setSettlementID(int settlementID) {
		SettlementID = settlementID;
		return this;
	}

	public int getBrokerOrderSeq() {
		return BrokerOrderSeq;
	}

	public CtpInboundRtnTrade setBrokerOrderSeq(int brokerOrderSeq) {
		BrokerOrderSeq = brokerOrderSeq;
		return this;
	}

	public char getTradeSource() {
		return TradeSource;
	}

	public CtpInboundRtnTrade setTradeSource(char tradeSource) {
		TradeSource = tradeSource;
		return this;
	}

	public String getInvestUnitID() {
		return InvestUnitID;
	}

	public CtpInboundRtnTrade setInvestUnitID(String investUnitID) {
		InvestUnitID = investUnitID;
		return this;
	}

}
