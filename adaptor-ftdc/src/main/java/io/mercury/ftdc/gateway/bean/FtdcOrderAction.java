package io.mercury.ftdc.gateway.bean;

public final class FtdcOrderAction {

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

	/// 操作日期
	private String ActionDate;

	/// 操作时间
	private String ActionTime;

	/// 交易所交易员代码
	private String TraderID;

	/// 安装编号
	private int InstallID;

	/// 本地报单编号
	private String OrderLocalID;

	/// 操作本地编号
	private String ActionLocalID;

	/// 会员代码
	private String ParticipantID;

	/// 客户代码
	private String ClientID;

	/// 业务单元
	private String BusinessUnit;

	/// 报单操作状态
	private char OrderActionStatus;

	/// 用户代码
	private String UserID;

	/// 状态信息
	private String StatusMsg;

	/// 合约代码
	private String InstrumentID;

	/// 营业部编号
	private String BranchID;

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

	public String getActionDate() {
		return ActionDate;
	}

	public String getActionTime() {
		return ActionTime;
	}

	public String getTraderID() {
		return TraderID;
	}

	public int getInstallID() {
		return InstallID;
	}

	public String getOrderLocalID() {
		return OrderLocalID;
	}

	public String getActionLocalID() {
		return ActionLocalID;
	}

	public String getParticipantID() {
		return ParticipantID;
	}

	public String getClientID() {
		return ClientID;
	}

	public String getBusinessUnit() {
		return BusinessUnit;
	}

	public char getOrderActionStatus() {
		return OrderActionStatus;
	}

	public String getUserID() {
		return UserID;
	}

	public String getStatusMsg() {
		return StatusMsg;
	}

	public String getInstrumentID() {
		return InstrumentID;
	}

	public String getBranchID() {
		return BranchID;
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

	public FtdcOrderAction setBrokerID(String brokerID) {
		BrokerID = brokerID;
		return this;
	}

	public FtdcOrderAction setInvestorID(String investorID) {
		InvestorID = investorID;
		return this;
	}

	public FtdcOrderAction setOrderActionRef(int orderActionRef) {
		OrderActionRef = orderActionRef;
		return this;
	}

	public FtdcOrderAction setOrderRef(String orderRef) {
		OrderRef = orderRef;
		return this;
	}

	public FtdcOrderAction setRequestID(int requestID) {
		RequestID = requestID;
		return this;
	}

	public FtdcOrderAction setFrontID(int frontID) {
		FrontID = frontID;
		return this;
	}

	public FtdcOrderAction setSessionID(int sessionID) {
		SessionID = sessionID;
		return this;
	}

	public FtdcOrderAction setExchangeID(String exchangeID) {
		ExchangeID = exchangeID;
		return this;
	}

	public FtdcOrderAction setOrderSysID(String orderSysID) {
		OrderSysID = orderSysID;
		return this;
	}

	public FtdcOrderAction setActionFlag(char actionFlag) {
		ActionFlag = actionFlag;
		return this;
	}

	public FtdcOrderAction setLimitPrice(double limitPrice) {
		LimitPrice = limitPrice;
		return this;
	}

	public FtdcOrderAction setVolumeChange(int volumeChange) {
		VolumeChange = volumeChange;
		return this;
	}

	public FtdcOrderAction setActionDate(String actionDate) {
		ActionDate = actionDate;
		return this;
	}

	public FtdcOrderAction setActionTime(String actionTime) {
		ActionTime = actionTime;
		return this;
	}

	public FtdcOrderAction setTraderID(String traderID) {
		TraderID = traderID;
		return this;
	}

	public FtdcOrderAction setInstallID(int installID) {
		InstallID = installID;
		return this;
	}

	public FtdcOrderAction setOrderLocalID(String orderLocalID) {
		OrderLocalID = orderLocalID;
		return this;
	}

	public FtdcOrderAction setActionLocalID(String actionLocalID) {
		ActionLocalID = actionLocalID;
		return this;
	}

	public FtdcOrderAction setParticipantID(String participantID) {
		ParticipantID = participantID;
		return this;
	}

	public FtdcOrderAction setClientID(String clientID) {
		ClientID = clientID;
		return this;
	}

	public FtdcOrderAction setBusinessUnit(String businessUnit) {
		BusinessUnit = businessUnit;
		return this;
	}

	public FtdcOrderAction setOrderActionStatus(char orderActionStatus) {
		OrderActionStatus = orderActionStatus;
		return this;
	}

	public FtdcOrderAction setUserID(String userID) {
		UserID = userID;
		return this;
	}

	public FtdcOrderAction setStatusMsg(String statusMsg) {
		StatusMsg = statusMsg;
		return this;
	}

	public FtdcOrderAction setInstrumentID(String instrumentID) {
		InstrumentID = instrumentID;
		return this;
	}

	public FtdcOrderAction setBranchID(String branchID) {
		BranchID = branchID;
		return this;
	}

	public FtdcOrderAction setInvestUnitID(String investUnitID) {
		InvestUnitID = investUnitID;
		return this;
	}

	public FtdcOrderAction setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
		return this;
	}

	public FtdcOrderAction setMacAddress(String macAddress) {
		MacAddress = macAddress;
		return this;
	}

}
