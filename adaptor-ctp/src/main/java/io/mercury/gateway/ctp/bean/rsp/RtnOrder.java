package io.mercury.gateway.ctp.bean.rsp;

public class RtnOrder {

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

	public RtnOrder setBrokerID(String brokerID) {
		BrokerID = brokerID;
		return this;
	}

	public RtnOrder setInvestorID(String investorID) {
		InvestorID = investorID;
		return this;
	}

	public RtnOrder setInstrumentID(String instrumentID) {
		InstrumentID = instrumentID;
		return this;
	}

	public RtnOrder setOrderRef(String orderRef) {
		OrderRef = orderRef;
		return this;
	}

	public RtnOrder setUserID(String userID) {
		UserID = userID;
		return this;
	}

	public RtnOrder setOrderPriceType(char orderPriceType) {
		OrderPriceType = orderPriceType;
		return this;
	}

	public RtnOrder setDirection(char direction) {
		Direction = direction;
		return this;
	}

	public RtnOrder setCombOffsetFlag(String combOffsetFlag) {
		CombOffsetFlag = combOffsetFlag;
		return this;
	}

	public RtnOrder setCombHedgeFlag(String combHedgeFlag) {
		CombHedgeFlag = combHedgeFlag;
		return this;
	}

	public RtnOrder setLimitPrice(double limitPrice) {
		LimitPrice = limitPrice;
		return this;
	}

	public RtnOrder setVolumeTotalOriginal(int volumeTotalOriginal) {
		VolumeTotalOriginal = volumeTotalOriginal;
		return this;
	}

	public RtnOrder setTimeCondition(char timeCondition) {
		TimeCondition = timeCondition;
		return this;
	}

	public RtnOrder setGTDDate(String gTDDate) {
		GTDDate = gTDDate;
		return this;
	}

	public RtnOrder setVolumeCondition(char volumeCondition) {
		VolumeCondition = volumeCondition;
		return this;
	}

	public RtnOrder setMinVolume(int minVolume) {
		MinVolume = minVolume;
		return this;
	}

	public RtnOrder setContingentCondition(char contingentCondition) {
		ContingentCondition = contingentCondition;
		return this;
	}

	public RtnOrder setStopPrice(double stopPrice) {
		StopPrice = stopPrice;
		return this;
	}

	public RtnOrder setForceCloseReason(char forceCloseReason) {
		ForceCloseReason = forceCloseReason;
		return this;
	}

	public RtnOrder setIsAutoSuspend(int isAutoSuspend) {
		IsAutoSuspend = isAutoSuspend;
		return this;
	}

	public RtnOrder setBusinessUnit(String businessUnit) {
		BusinessUnit = businessUnit;
		return this;
	}

	public RtnOrder setRequestID(int requestID) {
		RequestID = requestID;
		return this;
	}

	public RtnOrder setOrderLocalID(String orderLocalID) {
		OrderLocalID = orderLocalID;
		return this;
	}

	public RtnOrder setExchangeID(String exchangeID) {
		ExchangeID = exchangeID;
		return this;
	}

	public RtnOrder setParticipantID(String participantID) {
		ParticipantID = participantID;
		return this;
	}

	public RtnOrder setClientID(String clientID) {
		ClientID = clientID;
		return this;
	}

	public RtnOrder setExchangeInstID(String exchangeInstID) {
		ExchangeInstID = exchangeInstID;
		return this;
	}

	public RtnOrder setTraderID(String traderID) {
		TraderID = traderID;
		return this;
	}

	public RtnOrder setInstallID(int installID) {
		InstallID = installID;
		return this;
	}

	public RtnOrder setOrderSubmitStatus(char orderSubmitStatus) {
		OrderSubmitStatus = orderSubmitStatus;
		return this;
	}

	public RtnOrder setNotifySequence(int notifySequence) {
		NotifySequence = notifySequence;
		return this;
	}

	public RtnOrder setTradingDay(String tradingDay) {
		TradingDay = tradingDay;
		return this;
	}

	public RtnOrder setSettlementID(int settlementID) {
		SettlementID = settlementID;
		return this;
	}

	public RtnOrder setOrderSysID(String orderSysID) {
		OrderSysID = orderSysID;
		return this;
	}

	public RtnOrder setOrderSource(char orderSource) {
		OrderSource = orderSource;
		return this;
	}

	public RtnOrder setOrderStatus(char orderStatus) {
		OrderStatus = orderStatus;
		return this;
	}

	public RtnOrder setOrderType(char orderType) {
		OrderType = orderType;
		return this;
	}

	public RtnOrder setVolumeTraded(int volumeTraded) {
		VolumeTraded = volumeTraded;
		return this;
	}

	public RtnOrder setVolumeTotal(int volumeTotal) {
		VolumeTotal = volumeTotal;
		return this;
	}

	public RtnOrder setInsertDate(String insertDate) {
		InsertDate = insertDate;
		return this;
	}

	public RtnOrder setInsertTime(String insertTime) {
		InsertTime = insertTime;
		return this;
	}

	public RtnOrder setActiveTime(String activeTime) {
		ActiveTime = activeTime;
		return this;
	}

	public RtnOrder setSuspendTime(String suspendTime) {
		SuspendTime = suspendTime;
		return this;
	}

	public RtnOrder setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
		return this;
	}

	public RtnOrder setCancelTime(String cancelTime) {
		CancelTime = cancelTime;
		return this;
	}

	public RtnOrder setActiveTraderID(String activeTraderID) {
		ActiveTraderID = activeTraderID;
		return this;
	}

	public RtnOrder setClearingPartID(String clearingPartID) {
		ClearingPartID = clearingPartID;
		return this;
	}

	public RtnOrder setSequenceNo(int sequenceNo) {
		SequenceNo = sequenceNo;
		return this;
	}

	public RtnOrder setFrontID(int frontID) {
		FrontID = frontID;
		return this;
	}

	public RtnOrder setSessionID(int sessionID) {
		SessionID = sessionID;
		return this;
	}

	public RtnOrder setUserProductInfo(String userProductInfo) {
		UserProductInfo = userProductInfo;
		return this;
	}

	public RtnOrder setStatusMsg(String statusMsg) {
		StatusMsg = statusMsg;
		return this;
	}

	public RtnOrder setUserForceClose(int userForceClose) {
		UserForceClose = userForceClose;
		return this;
	}

	public RtnOrder setActiveUserID(String activeUserID) {
		ActiveUserID = activeUserID;
		return this;
	}

	public RtnOrder setBrokerOrderSeq(int brokerOrderSeq) {
		BrokerOrderSeq = brokerOrderSeq;
		return this;
	}

	public RtnOrder setRelativeOrderSysID(String relativeOrderSysID) {
		RelativeOrderSysID = relativeOrderSysID;
		return this;
	}

	public RtnOrder setZCETotalTradedVolume(int zCETotalTradedVolume) {
		ZCETotalTradedVolume = zCETotalTradedVolume;
		return this;
	}

	public RtnOrder setIsSwapOrder(int isSwapOrder) {
		IsSwapOrder = isSwapOrder;
		return this;
	}

	public RtnOrder setBranchID(String branchID) {
		BranchID = branchID;
		return this;
	}

	public RtnOrder setInvestUnitID(String investUnitID) {
		InvestUnitID = investUnitID;
		return this;
	}

	public RtnOrder setAccountID(String accountID) {
		AccountID = accountID;
		return this;
	}

	public RtnOrder setCurrencyID(String currencyID) {
		CurrencyID = currencyID;
		return this;
	}

	public RtnOrder setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
		return this;
	}

	public RtnOrder setMacAddress(String macAddress) {
		MacAddress = macAddress;
		return this;
	}

}
