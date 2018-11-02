package io.ffreedom.redstone.adaptor.ctp.dto.outbound;

/**
 * Mapping to CTP structure
 * 
 * @author phoneix
 *
 */
public class CtpOutboundNewOrder {

	// 经纪公司代码
	private String BrokerID;

	// 投资者代码(*)
	private String InvestorID;

	// 合约代码(*)
	private String InstrumentID;

	// 报单引用(*)
	private int OrderRef;

	// 用户代码
	private String UserID;

	// 报单价格条件
	private char OrderPriceType;

	// 买卖方向(*)
	private char Direction;

	// 组合开平标志
	private String CombOffsetFlag;

	// 组合投机套保标志
	private String CombHedgeFlag;

	// 价格(*)
	private double LimitPrice;

	// 数量(*)
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

	// 用户强评标志
	private int UserForceClose;

	// 互换单标志
	private int IsSwapOrder;

	// 交易所代码
	private String ExchangeID;

	// 投资单元代码
	private String InvestUnitID;

	// 资金账号(*)
	private String AccountID;

	// 币种代码
	private String CurrencyID;

	// 交易编码
	private String ClientID;

	// IP地址
	private String IPAddress;

	// Mac地址
	private String MacAddress;

	/**
	 * 
	 * @param orderRef
	 *            - 唯一报单标识
	 * @param instrumentId
	 *            - 合约ID
	 * @param price
	 *            - 价格
	 * @param volume
	 *            - 数量
	 * @param direction
	 *            - 方向
	 */
	public CtpOutboundNewOrder(int orderRef, String instrumentId, double price, int volume, char direction) {
		this.OrderRef = orderRef;
		this.InstrumentID = instrumentId;
		this.LimitPrice = price;
		this.VolumeTotalOriginal = volume;
		this.Direction = direction;
	}

	public CtpOutboundNewOrder() {

	}

	public String getBrokerID() {
		return BrokerID;
	}

	public CtpOutboundNewOrder setBrokerID(String brokerID) {
		BrokerID = brokerID;
		return this;
	}

	public String getInvestorID() {
		return InvestorID;
	}

	public CtpOutboundNewOrder setInvestorID(String investorID) {
		InvestorID = investorID;
		return this;
	}

	public String getInstrumentID() {
		return InstrumentID;
	}

	public CtpOutboundNewOrder setInstrumentID(String instrumentID) {
		InstrumentID = instrumentID;
		return this;
	}

	public int getOrderRef() {
		return OrderRef;
	}

	public CtpOutboundNewOrder setOrderRef(int orderRef) {
		OrderRef = orderRef;
		return this;
	}

	public String getUserID() {
		return UserID;
	}

	public CtpOutboundNewOrder setUserID(String userID) {
		UserID = userID;
		return this;
	}

	public char getOrderPriceType() {
		return OrderPriceType;
	}

	public CtpOutboundNewOrder setOrderPriceType(char orderPriceType) {
		OrderPriceType = orderPriceType;
		return this;
	}

	public char getDirection() {
		return Direction;
	}

	public CtpOutboundNewOrder setDirection(char direction) {
		Direction = direction;
		return this;
	}

	public String getCombOffsetFlag() {
		return CombOffsetFlag;
	}

	public CtpOutboundNewOrder setCombOffsetFlag(String combOffsetFlag) {
		CombOffsetFlag = combOffsetFlag;
		return this;
	}

	public String getCombHedgeFlag() {
		return CombHedgeFlag;
	}

	public CtpOutboundNewOrder setCombHedgeFlag(String combHedgeFlag) {
		CombHedgeFlag = combHedgeFlag;
		return this;
	}

	public double getLimitPrice() {
		return LimitPrice;
	}

	public CtpOutboundNewOrder setLimitPrice(double limitPrice) {
		LimitPrice = limitPrice;
		return this;
	}

	public int getVolumeTotalOriginal() {
		return VolumeTotalOriginal;
	}

	public CtpOutboundNewOrder setVolumeTotalOriginal(int volumeTotalOriginal) {
		VolumeTotalOriginal = volumeTotalOriginal;
		return this;
	}

	public char getTimeCondition() {
		return TimeCondition;
	}

	public CtpOutboundNewOrder setTimeCondition(char timeCondition) {
		TimeCondition = timeCondition;
		return this;
	}

	public String getGTDDate() {
		return GTDDate;
	}

	public CtpOutboundNewOrder setGTDDate(String gTDDate) {
		GTDDate = gTDDate;
		return this;
	}

	public char getVolumeCondition() {
		return VolumeCondition;
	}

	public CtpOutboundNewOrder setVolumeCondition(char volumeCondition) {
		VolumeCondition = volumeCondition;
		return this;
	}

	public int getMinVolume() {
		return MinVolume;
	}

	public CtpOutboundNewOrder setMinVolume(int minVolume) {
		MinVolume = minVolume;
		return this;
	}

	public char getContingentCondition() {
		return ContingentCondition;
	}

	public CtpOutboundNewOrder setContingentCondition(char contingentCondition) {
		ContingentCondition = contingentCondition;
		return this;
	}

	public double getStopPrice() {
		return StopPrice;
	}

	public CtpOutboundNewOrder setStopPrice(double stopPrice) {
		StopPrice = stopPrice;
		return this;
	}

	public char getForceCloseReason() {
		return ForceCloseReason;
	}

	public CtpOutboundNewOrder setForceCloseReason(char forceCloseReason) {
		ForceCloseReason = forceCloseReason;
		return this;
	}

	public int getIsAutoSuspend() {
		return IsAutoSuspend;
	}

	public CtpOutboundNewOrder setIsAutoSuspend(int isAutoSuspend) {
		IsAutoSuspend = isAutoSuspend;
		return this;
	}

	public String getBusinessUnit() {
		return BusinessUnit;
	}

	public CtpOutboundNewOrder setBusinessUnit(String businessUnit) {
		BusinessUnit = businessUnit;
		return this;
	}

	public int getRequestID() {
		return RequestID;
	}

	public CtpOutboundNewOrder setRequestID(int requestID) {
		RequestID = requestID;
		return this;
	}

	public int getUserForceClose() {
		return UserForceClose;
	}

	public CtpOutboundNewOrder setUserForceClose(int userForceClose) {
		UserForceClose = userForceClose;
		return this;
	}

	public int getIsSwapOrder() {
		return IsSwapOrder;
	}

	public CtpOutboundNewOrder setIsSwapOrder(int isSwapOrder) {
		IsSwapOrder = isSwapOrder;
		return this;
	}

	public String getExchangeID() {
		return ExchangeID;
	}

	public CtpOutboundNewOrder setExchangeID(String exchangeID) {
		ExchangeID = exchangeID;
		return this;
	}

	public String getInvestUnitID() {
		return InvestUnitID;
	}

	public CtpOutboundNewOrder setInvestUnitID(String investUnitID) {
		InvestUnitID = investUnitID;
		return this;
	}

	public String getAccountID() {
		return AccountID;
	}

	public CtpOutboundNewOrder setAccountID(String accountID) {
		AccountID = accountID;
		return this;
	}

	public String getCurrencyID() {
		return CurrencyID;
	}

	public CtpOutboundNewOrder setCurrencyID(String currencyID) {
		CurrencyID = currencyID;
		return this;
	}

	public String getClientID() {
		return ClientID;
	}

	public CtpOutboundNewOrder setClientID(String clientID) {
		ClientID = clientID;
		return this;
	}

	public String getIPAddress() {
		return IPAddress;
	}

	public CtpOutboundNewOrder setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
		return this;
	}

	public String getMacAddress() {
		return MacAddress;
	}

	public CtpOutboundNewOrder setMacAddress(String macAddress) {
		MacAddress = macAddress;
		return this;
	}

}
