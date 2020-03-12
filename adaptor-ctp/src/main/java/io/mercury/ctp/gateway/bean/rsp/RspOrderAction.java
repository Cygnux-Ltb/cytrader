package io.mercury.gateway.ctp.bean.rsp;

public class RspOrderAction {

	private String BrokerID;
	private String InvestorID;
	private int OrderActionRef;
	private String OrderRef;
	private int RequestID;
	private int FrontID;
	private int SessionID;
	private String ExchangeID;
	private String OrderSysID;
	private char ActionFlag;
	private double LimitPrice;
	private int VolumeChange;
	private String UserID;
	private String InstrumentID;
	private String InvestUnitID;
	private String IPAddress;
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

	public RspOrderAction setBrokerID(String brokerID) {
		BrokerID = brokerID;
		return this;
	}

	public RspOrderAction setInvestorID(String investorID) {
		InvestorID = investorID;
		return this;
	}

	public RspOrderAction setOrderActionRef(int orderActionRef) {
		OrderActionRef = orderActionRef;
		return this;
	}

	public RspOrderAction setOrderRef(String orderRef) {
		OrderRef = orderRef;
		return this;
	}

	public RspOrderAction setRequestID(int requestID) {
		RequestID = requestID;
		return this;
	}

	public RspOrderAction setFrontID(int frontID) {
		FrontID = frontID;
		return this;
	}

	public RspOrderAction setSessionID(int sessionID) {
		SessionID = sessionID;
		return this;
	}

	public RspOrderAction setExchangeID(String exchangeID) {
		ExchangeID = exchangeID;
		return this;
	}

	public RspOrderAction setOrderSysID(String orderSysID) {
		OrderSysID = orderSysID;
		return this;
	}

	public RspOrderAction setActionFlag(char actionFlag) {
		ActionFlag = actionFlag;
		return this;
	}

	public RspOrderAction setLimitPrice(double limitPrice) {
		LimitPrice = limitPrice;
		return this;
	}

	public RspOrderAction setVolumeChange(int volumeChange) {
		VolumeChange = volumeChange;
		return this;
	}

	public RspOrderAction setUserID(String userID) {
		UserID = userID;
		return this;
	}

	public RspOrderAction setInstrumentID(String instrumentID) {
		InstrumentID = instrumentID;
		return this;
	}

	public RspOrderAction setInvestUnitID(String investUnitID) {
		InvestUnitID = investUnitID;
		return this;
	}

	public RspOrderAction setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
		return this;
	}

	public RspOrderAction setMacAddress(String macAddress) {
		MacAddress = macAddress;
		return this;
	}

}
