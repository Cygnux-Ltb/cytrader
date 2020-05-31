package io.mercury.ftdc.gateway.bean;

public final class FtdcInputOrderAction {

	/// 经纪公司代码
	private String BrokerID;

	/// 投资者代码
	private String InvestorID;

	/// 报单操作引用
	private int OrderActionRef;

	/// 报单引用
	private String OrderRef;

	/// 请求编号
	private int RequestID;

	/// 前置编号
	private int FrontID;

	/// 会话编号
	private int SessionID;

	/// 交易所代码
	private String ExchangeID;

	/// 报单编号
	private String OrderSysID;

	/// 操作标志
	private char ActionFlag;

	/// 价格
	private double LimitPrice;

	/// 数量变化
	private int VolumeChange;

	/// 用户代码
	private String UserID;

	/// 合约代码
	private String InstrumentID;

	/// 投资单元代码
	private String InvestUnitID;

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

	public int getOrderActionRef() {
		return OrderActionRef;
	}

	public String getOrderRef() {
		return OrderRef;
	}

	public int getRequestID() {
		return RequestID;
	}

	public int getFrontID() {
		return FrontID;
	}

	public int getSessionID() {
		return SessionID;
	}

	public String getExchangeID() {
		return ExchangeID;
	}

	public String getOrderSysID() {
		return OrderSysID;
	}

	public char getActionFlag() {
		return ActionFlag;
	}

	public double getLimitPrice() {
		return LimitPrice;
	}

	public int getVolumeChange() {
		return VolumeChange;
	}

	public String getUserID() {
		return UserID;
	}

	public String getInstrumentID() {
		return InstrumentID;
	}

	public String getInvestUnitID() {
		return InvestUnitID;
	}

	public String getIPAddress() {
		return IPAddress;
	}

	public String getMacAddress() {
		return MacAddress;
	}

	public FtdcInputOrderAction setBrokerID(String brokerID) {
		BrokerID = brokerID;
		return this;
	}

	public FtdcInputOrderAction setInvestorID(String investorID) {
		InvestorID = investorID;
		return this;
	}

	public FtdcInputOrderAction setOrderActionRef(int orderActionRef) {
		OrderActionRef = orderActionRef;
		return this;
	}

	public FtdcInputOrderAction setOrderRef(String orderRef) {
		OrderRef = orderRef;
		return this;
	}

	public FtdcInputOrderAction setRequestID(int requestID) {
		RequestID = requestID;
		return this;
	}

	public FtdcInputOrderAction setFrontID(int frontID) {
		FrontID = frontID;
		return this;
	}

	public FtdcInputOrderAction setSessionID(int sessionID) {
		SessionID = sessionID;
		return this;
	}

	public FtdcInputOrderAction setExchangeID(String exchangeID) {
		ExchangeID = exchangeID;
		return this;
	}

	public FtdcInputOrderAction setOrderSysID(String orderSysID) {
		OrderSysID = orderSysID;
		return this;
	}

	public FtdcInputOrderAction setActionFlag(char actionFlag) {
		ActionFlag = actionFlag;
		return this;
	}

	public FtdcInputOrderAction setLimitPrice(double limitPrice) {
		LimitPrice = limitPrice;
		return this;
	}

	public FtdcInputOrderAction setVolumeChange(int volumeChange) {
		VolumeChange = volumeChange;
		return this;
	}

	public FtdcInputOrderAction setUserID(String userID) {
		UserID = userID;
		return this;
	}

	public FtdcInputOrderAction setInstrumentID(String instrumentID) {
		InstrumentID = instrumentID;
		return this;
	}

	public FtdcInputOrderAction setInvestUnitID(String investUnitID) {
		InvestUnitID = investUnitID;
		return this;
	}

	public FtdcInputOrderAction setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
		return this;
	}

	public FtdcInputOrderAction setMacAddress(String macAddress) {
		MacAddress = macAddress;
		return this;
	}

}
