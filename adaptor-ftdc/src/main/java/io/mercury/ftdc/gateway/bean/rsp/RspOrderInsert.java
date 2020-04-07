package io.mercury.ftdc.gateway.bean.rsp;

public class RspOrderInsert {

	private String BrokerID;
	private String InvestorID;
	private String InstrumentID;
	private String OrderRef;
	private String UserID;
	private char OrderPriceType;
	private char Direction;
	private String CombOffsetFlag;
	private String CombHedgeFlag;
	private double LimitPrice;
	private int VolumeTotalOriginal;
	private char TimeCondition;
	private String GTDDate;
	private char VolumeCondition;
	private int MinVolume;
	private char ContingentCondition;
	private double StopPrice;
	private char ForceCloseReason;
	private int IsAutoSuspend;
	private String BusinessUnit;
	private int RequestID;
	private int UserForceClose;
	private int IsSwapOrder;
	private String ExchangeID;
	private String InvestUnitID;
	private String AccountID;
	private String CurrencyID;
	private String ClientID;
	private String IPAddress;
	private String MacAddress;

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

	public char getOrderPriceType() {
		return OrderPriceType;
	}

	public char getDirection() {
		return Direction;
	}

	public String getCombOffsetFlag() {
		return CombOffsetFlag;
	}

	public String getCombHedgeFlag() {
		return CombHedgeFlag;
	}

	public double getLimitPrice() {
		return LimitPrice;
	}

	public int getVolumeTotalOriginal() {
		return VolumeTotalOriginal;
	}

	public char getTimeCondition() {
		return TimeCondition;
	}

	public String getGTDDate() {
		return GTDDate;
	}

	public char getVolumeCondition() {
		return VolumeCondition;
	}

	public int getMinVolume() {
		return MinVolume;
	}

	public char getContingentCondition() {
		return ContingentCondition;
	}

	public double getStopPrice() {
		return StopPrice;
	}

	public char getForceCloseReason() {
		return ForceCloseReason;
	}

	public int getIsAutoSuspend() {
		return IsAutoSuspend;
	}

	public String getBusinessUnit() {
		return BusinessUnit;
	}

	public int getRequestID() {
		return RequestID;
	}

	public int getUserForceClose() {
		return UserForceClose;
	}

	public int getIsSwapOrder() {
		return IsSwapOrder;
	}

	public String getExchangeID() {
		return ExchangeID;
	}

	public String getInvestUnitID() {
		return InvestUnitID;
	}

	public String getAccountID() {
		return AccountID;
	}

	public String getCurrencyID() {
		return CurrencyID;
	}

	public String getClientID() {
		return ClientID;
	}

	public String getIPAddress() {
		return IPAddress;
	}

	public String getMacAddress() {
		return MacAddress;
	}

	public RspOrderInsert setBrokerID(String brokerID) {
		BrokerID = brokerID;
		return this;
	}

	public RspOrderInsert setInvestorID(String investorID) {
		InvestorID = investorID;
		return this;
	}

	public RspOrderInsert setInstrumentID(String instrumentID) {
		InstrumentID = instrumentID;
		return this;
	}

	public RspOrderInsert setOrderRef(String orderRef) {
		OrderRef = orderRef;
		return this;
	}

	public RspOrderInsert setUserID(String userID) {
		UserID = userID;
		return this;
	}

	public RspOrderInsert setOrderPriceType(char orderPriceType) {
		OrderPriceType = orderPriceType;
		return this;
	}

	public RspOrderInsert setDirection(char direction) {
		Direction = direction;
		return this;
	}

	public RspOrderInsert setCombOffsetFlag(String combOffsetFlag) {
		CombOffsetFlag = combOffsetFlag;
		return this;
	}

	public RspOrderInsert setCombHedgeFlag(String combHedgeFlag) {
		CombHedgeFlag = combHedgeFlag;
		return this;
	}

	public RspOrderInsert setLimitPrice(double limitPrice) {
		LimitPrice = limitPrice;
		return this;
	}

	public RspOrderInsert setVolumeTotalOriginal(int volumeTotalOriginal) {
		VolumeTotalOriginal = volumeTotalOriginal;
		return this;
	}

	public RspOrderInsert setTimeCondition(char timeCondition) {
		TimeCondition = timeCondition;
		return this;
	}

	public RspOrderInsert setGTDDate(String gTDDate) {
		GTDDate = gTDDate;
		return this;
	}

	public RspOrderInsert setVolumeCondition(char volumeCondition) {
		VolumeCondition = volumeCondition;
		return this;
	}

	public RspOrderInsert setMinVolume(int minVolume) {
		MinVolume = minVolume;
		return this;
	}

	public RspOrderInsert setContingentCondition(char contingentCondition) {
		ContingentCondition = contingentCondition;
		return this;
	}

	public RspOrderInsert setStopPrice(double stopPrice) {
		StopPrice = stopPrice;
		return this;
	}

	public RspOrderInsert setForceCloseReason(char forceCloseReason) {
		ForceCloseReason = forceCloseReason;
		return this;
	}

	public RspOrderInsert setIsAutoSuspend(int isAutoSuspend) {
		IsAutoSuspend = isAutoSuspend;
		return this;
	}

	public RspOrderInsert setBusinessUnit(String businessUnit) {
		BusinessUnit = businessUnit;
		return this;
	}

	public RspOrderInsert setRequestID(int requestID) {
		RequestID = requestID;
		return this;
	}

	public RspOrderInsert setUserForceClose(int userForceClose) {
		UserForceClose = userForceClose;
		return this;
	}

	public RspOrderInsert setIsSwapOrder(int isSwapOrder) {
		IsSwapOrder = isSwapOrder;
		return this;
	}

	public RspOrderInsert setExchangeID(String exchangeID) {
		ExchangeID = exchangeID;
		return this;
	}

	public RspOrderInsert setInvestUnitID(String investUnitID) {
		InvestUnitID = investUnitID;
		return this;
	}

	public RspOrderInsert setAccountID(String accountID) {
		AccountID = accountID;
		return this;
	}

	public RspOrderInsert setCurrencyID(String currencyID) {
		CurrencyID = currencyID;
		return this;
	}

	public RspOrderInsert setClientID(String clientID) {
		ClientID = clientID;
		return this;
	}

	public RspOrderInsert setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
		return this;
	}

	public RspOrderInsert setMacAddress(String macAddress) {
		MacAddress = macAddress;
		return this;
	}

}
