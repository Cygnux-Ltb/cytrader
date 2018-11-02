package io.ffreedom.redstone.adaptor.ctp.dto.outbound;

public class CtpOutboundCancelOrder {

	// 经纪公司代码
	private String BrokerID;

	// 投资者代码
	private String InvestorID;

	// 报单操作引用
	private int OrderActionRef;

	// 报单引用
	private int OrderRef;

	// 请求编号
	private int RequestID;

	// 前置编号
	private int FrontID;

	// 会话编号
	private int SessionID;

	// 交易所代码
	private String ExchangeID;

	// 报单编号
	private String OrderSysID;

	// 操作标志
	private char ActionFlag;

	// 价格
	private double LimitPrice;

	// 数量变化
	private int VolumeChange;

	// 用户代码
	private String UserID;

	// 合约代码
	private String InstrumentID;

	// 投资单元代码
	private String InvestUnitID;

	// IP地址
	private String IPAddress;

	// Mac地址
	private String MacAddress;

	public String getBrokerID() {
		return BrokerID;
	}

	public CtpOutboundCancelOrder setBrokerID(String brokerID) {
		BrokerID = brokerID;
		return this;
	}

	public String getInvestorID() {
		return InvestorID;
	}

	public CtpOutboundCancelOrder setInvestorID(String investorID) {
		InvestorID = investorID;
		return this;
	}

	public int getOrderActionRef() {
		return OrderActionRef;
	}

	public CtpOutboundCancelOrder setOrderActionRef(int orderActionRef) {
		OrderActionRef = orderActionRef;
		return this;
	}

	public int getOrderRef() {
		return OrderRef;
	}

	public CtpOutboundCancelOrder setOrderRef(int orderRef) {
		OrderRef = orderRef;
		return this;
	}

	public int getRequestID() {
		return RequestID;
	}

	public CtpOutboundCancelOrder setRequestID(int requestID) {
		RequestID = requestID;
		return this;
	}

	public int getFrontID() {
		return FrontID;
	}

	public CtpOutboundCancelOrder setFrontID(int frontID) {
		FrontID = frontID;
		return this;
	}

	public int getSessionID() {
		return SessionID;
	}

	public CtpOutboundCancelOrder setSessionID(int sessionID) {
		SessionID = sessionID;
		return this;
	}

	public String getExchangeID() {
		return ExchangeID;
	}

	public CtpOutboundCancelOrder setExchangeID(String exchangeID) {
		ExchangeID = exchangeID;
		return this;
	}

	public String getOrderSysID() {
		return OrderSysID;
	}

	public CtpOutboundCancelOrder setOrderSysID(String orderSysID) {
		OrderSysID = orderSysID;
		return this;
	}

	public char getActionFlag() {
		return ActionFlag;
	}

	public CtpOutboundCancelOrder setActionFlag(char actionFlag) {
		ActionFlag = actionFlag;
		return this;
	}

	public double getLimitPrice() {
		return LimitPrice;
	}

	public CtpOutboundCancelOrder setLimitPrice(double limitPrice) {
		LimitPrice = limitPrice;
		return this;
	}

	public int getVolumeChange() {
		return VolumeChange;
	}

	public CtpOutboundCancelOrder setVolumeChange(int volumeChange) {
		VolumeChange = volumeChange;
		return this;
	}

	public String getUserID() {
		return UserID;
	}

	public CtpOutboundCancelOrder setUserID(String userID) {
		UserID = userID;
		return this;
	}

	public String getInstrumentID() {
		return InstrumentID;
	}

	public CtpOutboundCancelOrder setInstrumentID(String instrumentID) {
		InstrumentID = instrumentID;
		return this;
	}

	public String getInvestUnitID() {
		return InvestUnitID;
	}

	public CtpOutboundCancelOrder setInvestUnitID(String investUnitID) {
		InvestUnitID = investUnitID;
		return this;
	}

	public String getIPAddress() {
		return IPAddress;
	}

	public CtpOutboundCancelOrder setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
		return this;
	}

	public String getMacAddress() {
		return MacAddress;
	}

	public CtpOutboundCancelOrder setMacAddress(String macAddress) {
		MacAddress = macAddress;
		return this;
	}

}
