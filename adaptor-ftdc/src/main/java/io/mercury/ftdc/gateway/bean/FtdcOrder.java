package io.mercury.ftdc.gateway.bean;

public final class FtdcOrder {

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
	private String OrderLocalID;
	private String ExchangeID;
	private String ParticipantID;
	private String ClientID;
	private String ExchangeInstID;
	private String TraderID;
	private int InstallID;
	private char OrderSubmitStatus;
	private int NotifySequence;
	private String TradingDay;
	private int SettlementID;
	private String OrderSysID;
	private char OrderSource;
	private char OrderStatus;
	private char OrderType;
	private int VolumeTraded;
	private int VolumeTotal;
	private String InsertDate;
	private String InsertTime;
	private String ActiveTime;
	private String SuspendTime;
	private String UpdateTime;
	private String CancelTime;
	private String ActiveTraderID;
	private String ClearingPartID;
	private int SequenceNo;
	private int FrontID;
	private int SessionID;
	private String UserProductInfo;
	private String StatusMsg;
	private int UserForceClose;
	private String ActiveUserID;
	private int BrokerOrderSeq;
	private String RelativeOrderSysID;
	private int ZCETotalTradedVolume;
	private int IsSwapOrder;
	private String BranchID;
	private String InvestUnitID;
	private String AccountID;
	private String CurrencyID;
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

	public String getOrderLocalID() {
		return OrderLocalID;
	}

	public String getExchangeID() {
		return ExchangeID;
	}

	public String getParticipantID() {
		return ParticipantID;
	}

	public String getClientID() {
		return ClientID;
	}

	public String getExchangeInstID() {
		return ExchangeInstID;
	}

	public String getTraderID() {
		return TraderID;
	}

	public int getInstallID() {
		return InstallID;
	}

	public char getOrderSubmitStatus() {
		return OrderSubmitStatus;
	}

	public int getNotifySequence() {
		return NotifySequence;
	}

	public String getTradingDay() {
		return TradingDay;
	}

	public int getSettlementID() {
		return SettlementID;
	}

	public String getOrderSysID() {
		return OrderSysID;
	}

	public char getOrderSource() {
		return OrderSource;
	}

	public char getOrderStatus() {
		return OrderStatus;
	}

	public char getOrderType() {
		return OrderType;
	}

	public int getVolumeTraded() {
		return VolumeTraded;
	}

	public int getVolumeTotal() {
		return VolumeTotal;
	}

	public String getInsertDate() {
		return InsertDate;
	}

	public String getInsertTime() {
		return InsertTime;
	}

	public String getActiveTime() {
		return ActiveTime;
	}

	public String getSuspendTime() {
		return SuspendTime;
	}

	public String getUpdateTime() {
		return UpdateTime;
	}

	public String getCancelTime() {
		return CancelTime;
	}

	public String getActiveTraderID() {
		return ActiveTraderID;
	}

	public String getClearingPartID() {
		return ClearingPartID;
	}

	public int getSequenceNo() {
		return SequenceNo;
	}

	public int getFrontID() {
		return FrontID;
	}

	public int getSessionID() {
		return SessionID;
	}

	public String getUserProductInfo() {
		return UserProductInfo;
	}

	public String getStatusMsg() {
		return StatusMsg;
	}

	public int getUserForceClose() {
		return UserForceClose;
	}

	public String getActiveUserID() {
		return ActiveUserID;
	}

	public int getBrokerOrderSeq() {
		return BrokerOrderSeq;
	}

	public String getRelativeOrderSysID() {
		return RelativeOrderSysID;
	}

	public int getZCETotalTradedVolume() {
		return ZCETotalTradedVolume;
	}

	public int getIsSwapOrder() {
		return IsSwapOrder;
	}

	public String getBranchID() {
		return BranchID;
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

	public String getIPAddress() {
		return IPAddress;
	}

	public String getMacAddress() {
		return MacAddress;
	}

	public FtdcOrder setBrokerID(String brokerID) {
		BrokerID = brokerID;
		return this;
	}

	public FtdcOrder setInvestorID(String investorID) {
		InvestorID = investorID;
		return this;
	}

	public FtdcOrder setInstrumentID(String instrumentID) {
		InstrumentID = instrumentID;
		return this;
	}

	public FtdcOrder setOrderRef(String orderRef) {
		OrderRef = orderRef;
		return this;
	}

	public FtdcOrder setUserID(String userID) {
		UserID = userID;
		return this;
	}

	public FtdcOrder setOrderPriceType(char orderPriceType) {
		OrderPriceType = orderPriceType;
		return this;
	}

	public FtdcOrder setDirection(char direction) {
		Direction = direction;
		return this;
	}

	public FtdcOrder setCombOffsetFlag(String combOffsetFlag) {
		CombOffsetFlag = combOffsetFlag;
		return this;
	}

	public FtdcOrder setCombHedgeFlag(String combHedgeFlag) {
		CombHedgeFlag = combHedgeFlag;
		return this;
	}

	public FtdcOrder setLimitPrice(double limitPrice) {
		LimitPrice = limitPrice;
		return this;
	}

	public FtdcOrder setVolumeTotalOriginal(int volumeTotalOriginal) {
		VolumeTotalOriginal = volumeTotalOriginal;
		return this;
	}

	public FtdcOrder setTimeCondition(char timeCondition) {
		TimeCondition = timeCondition;
		return this;
	}

	public FtdcOrder setGTDDate(String gTDDate) {
		GTDDate = gTDDate;
		return this;
	}

	public FtdcOrder setVolumeCondition(char volumeCondition) {
		VolumeCondition = volumeCondition;
		return this;
	}

	public FtdcOrder setMinVolume(int minVolume) {
		MinVolume = minVolume;
		return this;
	}

	public FtdcOrder setContingentCondition(char contingentCondition) {
		ContingentCondition = contingentCondition;
		return this;
	}

	public FtdcOrder setStopPrice(double stopPrice) {
		StopPrice = stopPrice;
		return this;
	}

	public FtdcOrder setForceCloseReason(char forceCloseReason) {
		ForceCloseReason = forceCloseReason;
		return this;
	}

	public FtdcOrder setIsAutoSuspend(int isAutoSuspend) {
		IsAutoSuspend = isAutoSuspend;
		return this;
	}

	public FtdcOrder setBusinessUnit(String businessUnit) {
		BusinessUnit = businessUnit;
		return this;
	}

	public FtdcOrder setRequestID(int requestID) {
		RequestID = requestID;
		return this;
	}

	public FtdcOrder setOrderLocalID(String orderLocalID) {
		OrderLocalID = orderLocalID;
		return this;
	}

	public FtdcOrder setExchangeID(String exchangeID) {
		ExchangeID = exchangeID;
		return this;
	}

	public FtdcOrder setParticipantID(String participantID) {
		ParticipantID = participantID;
		return this;
	}

	public FtdcOrder setClientID(String clientID) {
		ClientID = clientID;
		return this;
	}

	public FtdcOrder setExchangeInstID(String exchangeInstID) {
		ExchangeInstID = exchangeInstID;
		return this;
	}

	public FtdcOrder setTraderID(String traderID) {
		TraderID = traderID;
		return this;
	}

	public FtdcOrder setInstallID(int installID) {
		InstallID = installID;
		return this;
	}

	public FtdcOrder setOrderSubmitStatus(char orderSubmitStatus) {
		OrderSubmitStatus = orderSubmitStatus;
		return this;
	}

	public FtdcOrder setNotifySequence(int notifySequence) {
		NotifySequence = notifySequence;
		return this;
	}

	public FtdcOrder setTradingDay(String tradingDay) {
		TradingDay = tradingDay;
		return this;
	}

	public FtdcOrder setSettlementID(int settlementID) {
		SettlementID = settlementID;
		return this;
	}

	public FtdcOrder setOrderSysID(String orderSysID) {
		OrderSysID = orderSysID;
		return this;
	}

	public FtdcOrder setOrderSource(char orderSource) {
		OrderSource = orderSource;
		return this;
	}

	public FtdcOrder setOrderStatus(char orderStatus) {
		OrderStatus = orderStatus;
		return this;
	}

	public FtdcOrder setOrderType(char orderType) {
		OrderType = orderType;
		return this;
	}

	public FtdcOrder setVolumeTraded(int volumeTraded) {
		VolumeTraded = volumeTraded;
		return this;
	}

	public FtdcOrder setVolumeTotal(int volumeTotal) {
		VolumeTotal = volumeTotal;
		return this;
	}

	public FtdcOrder setInsertDate(String insertDate) {
		InsertDate = insertDate;
		return this;
	}

	public FtdcOrder setInsertTime(String insertTime) {
		InsertTime = insertTime;
		return this;
	}

	public FtdcOrder setActiveTime(String activeTime) {
		ActiveTime = activeTime;
		return this;
	}

	public FtdcOrder setSuspendTime(String suspendTime) {
		SuspendTime = suspendTime;
		return this;
	}

	public FtdcOrder setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
		return this;
	}

	public FtdcOrder setCancelTime(String cancelTime) {
		CancelTime = cancelTime;
		return this;
	}

	public FtdcOrder setActiveTraderID(String activeTraderID) {
		ActiveTraderID = activeTraderID;
		return this;
	}

	public FtdcOrder setClearingPartID(String clearingPartID) {
		ClearingPartID = clearingPartID;
		return this;
	}

	public FtdcOrder setSequenceNo(int sequenceNo) {
		SequenceNo = sequenceNo;
		return this;
	}

	public FtdcOrder setFrontID(int frontID) {
		FrontID = frontID;
		return this;
	}

	public FtdcOrder setSessionID(int sessionID) {
		SessionID = sessionID;
		return this;
	}

	public FtdcOrder setUserProductInfo(String userProductInfo) {
		UserProductInfo = userProductInfo;
		return this;
	}

	public FtdcOrder setStatusMsg(String statusMsg) {
		StatusMsg = statusMsg;
		return this;
	}

	public FtdcOrder setUserForceClose(int userForceClose) {
		UserForceClose = userForceClose;
		return this;
	}

	public FtdcOrder setActiveUserID(String activeUserID) {
		ActiveUserID = activeUserID;
		return this;
	}

	public FtdcOrder setBrokerOrderSeq(int brokerOrderSeq) {
		BrokerOrderSeq = brokerOrderSeq;
		return this;
	}

	public FtdcOrder setRelativeOrderSysID(String relativeOrderSysID) {
		RelativeOrderSysID = relativeOrderSysID;
		return this;
	}

	public FtdcOrder setZCETotalTradedVolume(int zCETotalTradedVolume) {
		ZCETotalTradedVolume = zCETotalTradedVolume;
		return this;
	}

	public FtdcOrder setIsSwapOrder(int isSwapOrder) {
		IsSwapOrder = isSwapOrder;
		return this;
	}

	public FtdcOrder setBranchID(String branchID) {
		BranchID = branchID;
		return this;
	}

	public FtdcOrder setInvestUnitID(String investUnitID) {
		InvestUnitID = investUnitID;
		return this;
	}

	public FtdcOrder setAccountID(String accountID) {
		AccountID = accountID;
		return this;
	}

	public FtdcOrder setCurrencyID(String currencyID) {
		CurrencyID = currencyID;
		return this;
	}

	public FtdcOrder setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
		return this;
	}

	public FtdcOrder setMacAddress(String macAddress) {
		MacAddress = macAddress;
		return this;
	}

}
