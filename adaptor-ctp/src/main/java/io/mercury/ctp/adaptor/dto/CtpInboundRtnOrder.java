package io.mercury.ctp.adaptor.dto;

/**
 * 
 * CTP RtnOrder confirm
 * 
 * @author phoneix
 *
 */
public class CtpInboundRtnOrder {

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

	// 报单价格条件
	private char OrderPriceType;

	// 买卖方向
	private char Direction;

	// 组合开平标志
	private String CombOffsetFlag;

	// 组合投机套保标志
	private String CombHedgeFlag;

	// 价格
	private double LimitPrice;

	// 数量
	private int VolumeTotalOriginal;

	// 有效期类型
	private char TimeCondition;

	// GTD日期
	private String GTDDate;

	// 成交量类型
	private char VolumeCondition;

	// 最小成交量
	private int MinVolume;

	// 触发条件
	private char ContingentCondition;

	// 止损价
	private double StopPrice;

	// 强平原因
	private char ForceCloseReason;

	// 自动挂起标志
	private int IsAutoSuspend;

	// 业务单元
	private String BusinessUnit;

	// 请求编号
	private int RequestID;

	// 本地报单编号
	private String OrderLocalID;

	// 交易所代码
	private String ExchangeID;

	// 会员代码
	private String ParticipantID;

	// 客户代码
	private String ClientID;

	// 合约在交易所的代码
	private String ExchangeInstID;

	// 交易所交易员代码
	private String TraderID;

	// 安装编号
	private int InstallID;

	// 报单提交状态
	private char OrderSubmitStatus;

	// 报单提示序号
	private int NotifySequence;

	// 交易日
	private String TradingDay;

	// 结算编号
	private int SettlementID;

	// 报单编号
	private String OrderSysID;

	// 报单来源
	private char OrderSource;

	// 报单状态
	private char OrderStatus;

	// 报单类型
	private char OrderType;

	// 今成交数量
	private int VolumeTraded;

	// 剩余数量
	private int VolumeTotal;

	// 报单日期
	private String InsertDate;

	// 委托时间
	private String InsertTime;

	// 激活时间
	private String ActiveTime;

	// 挂起时间
	private String SuspendTime;

	// 最后修改时间
	private String UpdateTime;

	// 撤销时间
	private String CancelTime;

	// 最后修改交易所交易员代码
	private String ActiveTraderID;

	// 结算会员编号
	private char ClearingPartID;

	public String getBrokerID() {
		return BrokerID;
	}

	public CtpInboundRtnOrder setBrokerID(String brokerID) {
		BrokerID = brokerID;
		return this;
	}

	public String getInvestorID() {
		return InvestorID;
	}

	public CtpInboundRtnOrder setInvestorID(String investorID) {
		InvestorID = investorID;
		return this;
	}

	public String getInstrumentID() {
		return InstrumentID;
	}

	public CtpInboundRtnOrder setInstrumentID(String instrumentID) {
		InstrumentID = instrumentID;
		return this;
	}

	public int getOrderRef() {
		return OrderRef;
	}

	public CtpInboundRtnOrder setOrderRef(int orderRef) {
		OrderRef = orderRef;
		return this;
	}

	public String getUserID() {
		return UserID;
	}

	public CtpInboundRtnOrder setUserID(String userID) {
		UserID = userID;
		return this;
	}

	public char getOrderPriceType() {
		return OrderPriceType;
	}

	public CtpInboundRtnOrder setOrderPriceType(char orderPriceType) {
		OrderPriceType = orderPriceType;
		return this;
	}

	public char getDirection() {
		return Direction;
	}

	public CtpInboundRtnOrder setDirection(char direction) {
		Direction = direction;
		return this;
	}

	public String getCombOffsetFlag() {
		return CombOffsetFlag;
	}

	public CtpInboundRtnOrder setCombOffsetFlag(String combOffsetFlag) {
		CombOffsetFlag = combOffsetFlag;
		return this;
	}

	public String getCombHedgeFlag() {
		return CombHedgeFlag;
	}

	public CtpInboundRtnOrder setCombHedgeFlag(String combHedgeFlag) {
		CombHedgeFlag = combHedgeFlag;
		return this;
	}

	public double getLimitPrice() {
		return LimitPrice;
	}

	public CtpInboundRtnOrder setLimitPrice(double limitPrice) {
		LimitPrice = limitPrice;
		return this;
	}

	public int getVolumeTotalOriginal() {
		return VolumeTotalOriginal;
	}

	public CtpInboundRtnOrder setVolumeTotalOriginal(int volumeTotalOriginal) {
		VolumeTotalOriginal = volumeTotalOriginal;
		return this;
	}

	public char getTimeCondition() {
		return TimeCondition;
	}

	public CtpInboundRtnOrder setTimeCondition(char timeCondition) {
		TimeCondition = timeCondition;
		return this;
	}

	public String getGTDDate() {
		return GTDDate;
	}

	public CtpInboundRtnOrder setGTDDate(String gTDDate) {
		GTDDate = gTDDate;
		return this;
	}

	public char getVolumeCondition() {
		return VolumeCondition;
	}

	public CtpInboundRtnOrder setVolumeCondition(char volumeCondition) {
		VolumeCondition = volumeCondition;
		return this;
	}

	public int getMinVolume() {
		return MinVolume;
	}

	public CtpInboundRtnOrder setMinVolume(int minVolume) {
		MinVolume = minVolume;
		return this;
	}

	public char getContingentCondition() {
		return ContingentCondition;
	}

	public CtpInboundRtnOrder setContingentCondition(char contingentCondition) {
		ContingentCondition = contingentCondition;
		return this;
	}

	public double getStopPrice() {
		return StopPrice;
	}

	public CtpInboundRtnOrder setStopPrice(double stopPrice) {
		StopPrice = stopPrice;
		return this;
	}

	public char getForceCloseReason() {
		return ForceCloseReason;
	}

	public CtpInboundRtnOrder setForceCloseReason(char forceCloseReason) {
		ForceCloseReason = forceCloseReason;
		return this;
	}

	public int getIsAutoSuspend() {
		return IsAutoSuspend;
	}

	public CtpInboundRtnOrder setIsAutoSuspend(int isAutoSuspend) {
		IsAutoSuspend = isAutoSuspend;
		return this;
	}

	public String getBusinessUnit() {
		return BusinessUnit;
	}

	public CtpInboundRtnOrder setBusinessUnit(String businessUnit) {
		BusinessUnit = businessUnit;
		return this;
	}

	public int getRequestID() {
		return RequestID;
	}

	public CtpInboundRtnOrder setRequestID(int requestID) {
		RequestID = requestID;
		return this;
	}

	public String getOrderLocalID() {
		return OrderLocalID;
	}

	public CtpInboundRtnOrder setOrderLocalID(String orderLocalID) {
		OrderLocalID = orderLocalID;
		return this;
	}

	public String getExchangeID() {
		return ExchangeID;
	}

	public CtpInboundRtnOrder setExchangeID(String exchangeID) {
		ExchangeID = exchangeID;
		return this;
	}

	public String getParticipantID() {
		return ParticipantID;
	}

	public CtpInboundRtnOrder setParticipantID(String participantID) {
		ParticipantID = participantID;
		return this;
	}

	public String getClientID() {
		return ClientID;
	}

	public CtpInboundRtnOrder setClientID(String clientID) {
		ClientID = clientID;
		return this;
	}

	public String getExchangeInstID() {
		return ExchangeInstID;
	}

	public CtpInboundRtnOrder setExchangeInstID(String exchangeInstID) {
		ExchangeInstID = exchangeInstID;
		return this;
	}

	public String getTraderID() {
		return TraderID;
	}

	public CtpInboundRtnOrder setTraderID(String traderID) {
		TraderID = traderID;
		return this;
	}

	public int getInstallID() {
		return InstallID;
	}

	public CtpInboundRtnOrder setInstallID(int installID) {
		InstallID = installID;
		return this;
	}

	public char getOrderSubmitStatus() {
		return OrderSubmitStatus;
	}

	public CtpInboundRtnOrder setOrderSubmitStatus(char orderSubmitStatus) {
		OrderSubmitStatus = orderSubmitStatus;
		return this;
	}

	public int getNotifySequence() {
		return NotifySequence;
	}

	public CtpInboundRtnOrder setNotifySequence(int notifySequence) {
		NotifySequence = notifySequence;
		return this;
	}

	public String getTradingDay() {
		return TradingDay;
	}

	public CtpInboundRtnOrder setTradingDay(String tradingDay) {
		TradingDay = tradingDay;
		return this;
	}

	public int getSettlementID() {
		return SettlementID;
	}

	public CtpInboundRtnOrder setSettlementID(int settlementID) {
		SettlementID = settlementID;
		return this;
	}

	public String getOrderSysID() {
		return OrderSysID;
	}

	public CtpInboundRtnOrder setOrderSysID(String orderSysID) {
		OrderSysID = orderSysID;
		return this;
	}

	public char getOrderSource() {
		return OrderSource;
	}

	public CtpInboundRtnOrder setOrderSource(char orderSource) {
		OrderSource = orderSource;
		return this;
	}

	public char getOrderStatus() {
		return OrderStatus;
	}

	public CtpInboundRtnOrder setOrderStatus(char orderStatus) {
		OrderStatus = orderStatus;
		return this;
	}

	public char getOrderType() {
		return OrderType;
	}

	public CtpInboundRtnOrder setOrderType(char orderType) {
		OrderType = orderType;
		return this;
	}

	public int getVolumeTraded() {
		return VolumeTraded;
	}

	public CtpInboundRtnOrder setVolumeTraded(int volumeTraded) {
		VolumeTraded = volumeTraded;
		return this;
	}

	public int getVolumeTotal() {
		return VolumeTotal;
	}

	public CtpInboundRtnOrder setVolumeTotal(int volumeTotal) {
		VolumeTotal = volumeTotal;
		return this;
	}

	public String getInsertDate() {
		return InsertDate;
	}

	public CtpInboundRtnOrder setInsertDate(String insertDate) {
		InsertDate = insertDate;
		return this;
	}

	public String getInsertTime() {
		return InsertTime;
	}

	public CtpInboundRtnOrder setInsertTime(String insertTime) {
		InsertTime = insertTime;
		return this;
	}

	public String getActiveTime() {
		return ActiveTime;
	}

	public CtpInboundRtnOrder setActiveTime(String activeTime) {
		ActiveTime = activeTime;
		return this;
	}

	public String getSuspendTime() {
		return SuspendTime;
	}

	public CtpInboundRtnOrder setSuspendTime(String suspendTime) {
		SuspendTime = suspendTime;
		return this;
	}

	public String getUpdateTime() {
		return UpdateTime;
	}

	public CtpInboundRtnOrder setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
		return this;
	}

	public String getCancelTime() {
		return CancelTime;
	}

	public CtpInboundRtnOrder setCancelTime(String cancelTime) {
		CancelTime = cancelTime;
		return this;
	}

	public String getActiveTraderID() {
		return ActiveTraderID;
	}

	public CtpInboundRtnOrder setActiveTraderID(String activeTraderID) {
		ActiveTraderID = activeTraderID;
		return this;
	}

	public char getClearingPartID() {
		return ClearingPartID;
	}

	public CtpInboundRtnOrder setClearingPartID(char clearingPartID) {
		ClearingPartID = clearingPartID;
		return this;
	}

}
