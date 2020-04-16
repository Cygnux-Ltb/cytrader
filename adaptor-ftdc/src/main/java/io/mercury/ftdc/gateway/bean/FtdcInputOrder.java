package io.mercury.ftdc.gateway.bean;

public final class FtdcInputOrder {

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

	public FtdcInputOrder setBrokerID(String brokerID) {
		BrokerID = brokerID;
		return this;
	}

	public FtdcInputOrder setInvestorID(String investorID) {
		InvestorID = investorID;
		return this;
	}

	public FtdcInputOrder setInstrumentID(String instrumentID) {
		InstrumentID = instrumentID;
		return this;
	}

	public FtdcInputOrder setOrderRef(String orderRef) {
		OrderRef = orderRef;
		return this;
	}

	public FtdcInputOrder setUserID(String userID) {
		UserID = userID;
		return this;
	}

	public FtdcInputOrder setOrderPriceType(char orderPriceType) {
		OrderPriceType = orderPriceType;
		return this;
	}

	public FtdcInputOrder setDirection(char direction) {
		Direction = direction;
		return this;
	}

	public FtdcInputOrder setCombOffsetFlag(String combOffsetFlag) {
		CombOffsetFlag = combOffsetFlag;
		return this;
	}

	public FtdcInputOrder setCombHedgeFlag(String combHedgeFlag) {
		CombHedgeFlag = combHedgeFlag;
		return this;
	}

	public FtdcInputOrder setLimitPrice(double limitPrice) {
		LimitPrice = limitPrice;
		return this;
	}

	public FtdcInputOrder setVolumeTotalOriginal(int volumeTotalOriginal) {
		VolumeTotalOriginal = volumeTotalOriginal;
		return this;
	}

	public FtdcInputOrder setTimeCondition(char timeCondition) {
		TimeCondition = timeCondition;
		return this;
	}

	public FtdcInputOrder setGTDDate(String gTDDate) {
		GTDDate = gTDDate;
		return this;
	}

	public FtdcInputOrder setVolumeCondition(char volumeCondition) {
		VolumeCondition = volumeCondition;
		return this;
	}

	public FtdcInputOrder setMinVolume(int minVolume) {
		MinVolume = minVolume;
		return this;
	}

	public FtdcInputOrder setContingentCondition(char contingentCondition) {
		ContingentCondition = contingentCondition;
		return this;
	}

	public FtdcInputOrder setStopPrice(double stopPrice) {
		StopPrice = stopPrice;
		return this;
	}

	public FtdcInputOrder setForceCloseReason(char forceCloseReason) {
		ForceCloseReason = forceCloseReason;
		return this;
	}

	public FtdcInputOrder setIsAutoSuspend(int isAutoSuspend) {
		IsAutoSuspend = isAutoSuspend;
		return this;
	}

	public FtdcInputOrder setBusinessUnit(String businessUnit) {
		BusinessUnit = businessUnit;
		return this;
	}

	public FtdcInputOrder setRequestID(int requestID) {
		RequestID = requestID;
		return this;
	}

	public FtdcInputOrder setUserForceClose(int userForceClose) {
		UserForceClose = userForceClose;
		return this;
	}

	public FtdcInputOrder setIsSwapOrder(int isSwapOrder) {
		IsSwapOrder = isSwapOrder;
		return this;
	}

	public FtdcInputOrder setExchangeID(String exchangeID) {
		ExchangeID = exchangeID;
		return this;
	}

	public FtdcInputOrder setInvestUnitID(String investUnitID) {
		InvestUnitID = investUnitID;
		return this;
	}

	public FtdcInputOrder setAccountID(String accountID) {
		AccountID = accountID;
		return this;
	}

	public FtdcInputOrder setCurrencyID(String currencyID) {
		CurrencyID = currencyID;
		return this;
	}

	public FtdcInputOrder setClientID(String clientID) {
		ClientID = clientID;
		return this;
	}

	public FtdcInputOrder setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
		return this;
	}

	public FtdcInputOrder setMacAddress(String macAddress) {
		MacAddress = macAddress;
		return this;
	}

}
