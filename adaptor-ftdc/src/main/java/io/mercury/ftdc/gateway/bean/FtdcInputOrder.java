package io.mercury.ftdc.gateway.bean;

public final class FtdcInputOrder {

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

	/// 用户强评标志
	private int UserForceClose;

	/// 互换单标志
	private int IsSwapOrder;

	/// 交易所代码
	private String ExchangeID;

	/// 投资单元代码
	private String InvestUnitID;

	/// 资金账号
	private String AccountID;

	/// 币种代码
	private String CurrencyID;

	/// 交易编码
	private String ClientID;

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
