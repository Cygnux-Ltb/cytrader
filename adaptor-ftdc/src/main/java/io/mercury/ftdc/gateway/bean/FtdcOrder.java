package io.mercury.ftdc.gateway.bean;

public final class FtdcOrder {

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

	/// 报单价格条件
	private char OrderPriceType;

	/// 买卖方向
	private char Direction;

	/// 组合开平标志
	private String CombOffsetFlag;

	/// 组合投机套保标志
	private String CombHedgeFlag;

	/// 价格
	private double LimitPrice;

	/// 数量
	private int VolumeTotalOriginal;

	/// 有效期类型
	private char TimeCondition;

	/// GTD日期
	private String GTDDate;

	/// 成交量类型
	private char VolumeCondition;

	/// 最小成交量
	private int MinVolume;

	/// 触发条件
	private char ContingentCondition;

	/// 止损价
	private double StopPrice;

	/// 强平原因
	private char ForceCloseReason;

	/// 自动挂起标志
	private int IsAutoSuspend;

	/// 业务单元
	private String BusinessUnit;

	/// 请求编号
	private int RequestID;

	/// 本地报单编号
	private String OrderLocalID;

	/// 交易所代码
	private String ExchangeID;

	/// 会员代码
	private String ParticipantID;

	/// 客户代码
	private String ClientID;

	/// 合约在交易所的代码
	private String ExchangeInstID;

	/// 交易所交易员代码
	private String TraderID;

	/// 安装编号
	private int InstallID;

	/// 报单提交状态
	private char OrderSubmitStatus;

	/// 报单提示序号
	private int NotifySequence;

	/// 交易日
	private String TradingDay;

	/// 结算编号
	private int SettlementID;

	/// 报单编号
	private String OrderSysID;

	/// 报单来源
	private char OrderSource;

	/// 报单状态
	private char OrderStatus;

	/// 报单类型
	private char OrderType;

	/// 今成交数量
	private int VolumeTraded;

	/// 剩余数量
	private int VolumeTotal;

	/// 报单日期
	private String InsertDate;

	/// 委托时间
	private String InsertTime;

	/// 激活时间
	private String ActiveTime;

	/// 挂起时间
	private String SuspendTime;

	/// 最后修改时间
	private String UpdateTime;

	/// 撤销时间
	private String CancelTime;

	/// 最后修改交易所交易员代码
	private String ActiveTraderID;

	/// 结算会员编号
	private String ClearingPartID;

	/// 序号
	private int SequenceNo;

	/// 前置编号
	private int FrontID;

	/// 会话编号
	private int SessionID;

	/// 用户端产品信息
	private String UserProductInfo;

	/// 状态信息
	private String StatusMsg;

	/// 用户强评标志
	private int UserForceClose;

	/// 操作用户代码
	private String ActiveUserID;

	/// 经纪公司报单编号
	private int BrokerOrderSeq;

	/// 相关报单
	private String RelativeOrderSysID;

	/// 郑商所成交数量
	private int ZCETotalTradedVolume;

	/// 互换单标志
	private int IsSwapOrder;

	/// 营业部编号
	private String BranchID;

	/// 投资单元代码
	private String InvestUnitID;

	/// 资金账号
	private String AccountID;

	/// 币种代码
	private String CurrencyID;

	/// IP地址
	private String IPAddress;

	/// Mac地址
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
