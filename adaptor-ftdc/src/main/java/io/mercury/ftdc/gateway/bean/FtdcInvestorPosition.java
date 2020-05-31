package io.mercury.ftdc.gateway.bean;

public final class FtdcInvestorPosition {

	/// 合约代码
	private String InstrumentID;

	/// 经纪公司代码
	private String BrokerID;

	/// 投资者代码
	private String InvestorID;

	/// 持仓多空方向
	private char PosiDirection;

	/// 投机套保标志
	private char HedgeFlag;

	/// 持仓日期
	private char PositionDate;

	/// 上日持仓
	private int YdPosition;

	/// 今日持仓
	private int Position;

	/// 多头冻结
	private int LongFrozen;

	/// 空头冻结
	private int ShortFrozen;

	/// 开仓冻结金额
	private double LongFrozenAmount;

	/// 开仓冻结金额
	private double ShortFrozenAmount;

	/// 开仓量
	private int OpenVolume;

	/// 平仓量
	private int CloseVolume;

	/// 开仓金额
	private double OpenAmount;

	/// 平仓金额
	private double CloseAmount;

	/// 持仓成本
	private double PositionCost;

	/// 上次占用的保证金
	private double PreMargin;

	/// 占用的保证金
	private double UseMargin;

	/// 冻结的保证金
	private double FrozenMargin;

	/// 冻结的资金
	private double FrozenCash;

	/// 冻结的手续费
	private double FrozenCommission;

	/// 资金差额
	private double CashIn;

	/// 手续费
	private double Commission;

	/// 平仓盈亏
	private double CloseProfit;

	/// 持仓盈亏
	private double PositionProfit;

	/// 上次结算价
	private double PreSettlementPrice;

	/// 本次结算价
	private double SettlementPrice;

	/// 交易日
	private String TradingDay;

	/// 结算编号
	private int SettlementID;

	/// 开仓成本
	private double OpenCost;

	/// 交易所保证金
	private double ExchangeMargin;

	/// 组合成交形成的持仓
	private int CombPosition;

	/// 组合多头冻结
	private int CombLongFrozen;

	/// 组合空头冻结
	private int CombShortFrozen;

	/// 逐日盯市平仓盈亏
	private double CloseProfitByDate;

	/// 逐笔对冲平仓盈亏
	private double CloseProfitByTrade;

	/// 今日持仓
	private int TodayPosition;

	/// 保证金率
	private double MarginRateByMoney;

	/// 保证金率(按手数)
	private double MarginRateByVolume;

	/// 执行冻结
	private int StrikeFrozen;

	/// 执行冻结金额
	private double StrikeFrozenAmount;

	/// 放弃执行冻结
	private int AbandonFrozen;

	/// 交易所代码
	private String ExchangeID;

	/// 执行冻结的昨仓
	private int YdStrikeFrozen;

	/// 投资单元代码
	private String InvestUnitID;

	/// 大商所持仓成本差值，只有大商所使用
	private double PositionCostOffset;

	public String getInstrumentID() {
		return InstrumentID;
	}

	public String getBrokerID() {
		return BrokerID;
	}

	public String getInvestorID() {
		return InvestorID;
	}

	public char getPosiDirection() {
		return PosiDirection;
	}

	public char getHedgeFlag() {
		return HedgeFlag;
	}

	public char getPositionDate() {
		return PositionDate;
	}

	public int getYdPosition() {
		return YdPosition;
	}

	public int getPosition() {
		return Position;
	}

	public int getLongFrozen() {
		return LongFrozen;
	}

	public int getShortFrozen() {
		return ShortFrozen;
	}

	public double getLongFrozenAmount() {
		return LongFrozenAmount;
	}

	public double getShortFrozenAmount() {
		return ShortFrozenAmount;
	}

	public int getOpenVolume() {
		return OpenVolume;
	}

	public int getCloseVolume() {
		return CloseVolume;
	}

	public double getOpenAmount() {
		return OpenAmount;
	}

	public double getCloseAmount() {
		return CloseAmount;
	}

	public double getPositionCost() {
		return PositionCost;
	}

	public double getPreMargin() {
		return PreMargin;
	}

	public double getUseMargin() {
		return UseMargin;
	}

	public double getFrozenMargin() {
		return FrozenMargin;
	}

	public double getFrozenCash() {
		return FrozenCash;
	}

	public double getFrozenCommission() {
		return FrozenCommission;
	}

	public double getCashIn() {
		return CashIn;
	}

	public double getCommission() {
		return Commission;
	}

	public double getCloseProfit() {
		return CloseProfit;
	}

	public double getPositionProfit() {
		return PositionProfit;
	}

	public double getPreSettlementPrice() {
		return PreSettlementPrice;
	}

	public double getSettlementPrice() {
		return SettlementPrice;
	}

	public String getTradingDay() {
		return TradingDay;
	}

	public int getSettlementID() {
		return SettlementID;
	}

	public double getOpenCost() {
		return OpenCost;
	}

	public double getExchangeMargin() {
		return ExchangeMargin;
	}

	public int getCombPosition() {
		return CombPosition;
	}

	public int getCombLongFrozen() {
		return CombLongFrozen;
	}

	public int getCombShortFrozen() {
		return CombShortFrozen;
	}

	public double getCloseProfitByDate() {
		return CloseProfitByDate;
	}

	public double getCloseProfitByTrade() {
		return CloseProfitByTrade;
	}

	public int getTodayPosition() {
		return TodayPosition;
	}

	public double getMarginRateByMoney() {
		return MarginRateByMoney;
	}

	public double getMarginRateByVolume() {
		return MarginRateByVolume;
	}

	public int getStrikeFrozen() {
		return StrikeFrozen;
	}

	public double getStrikeFrozenAmount() {
		return StrikeFrozenAmount;
	}

	public int getAbandonFrozen() {
		return AbandonFrozen;
	}

	public String getExchangeID() {
		return ExchangeID;
	}

	public int getYdStrikeFrozen() {
		return YdStrikeFrozen;
	}

	public String getInvestUnitID() {
		return InvestUnitID;
	}

	public double getPositionCostOffset() {
		return PositionCostOffset;
	}

	public FtdcInvestorPosition setInstrumentID(String instrumentID) {
		InstrumentID = instrumentID;
		return this;
	}

	public FtdcInvestorPosition setBrokerID(String brokerID) {
		BrokerID = brokerID;
		return this;
	}

	public FtdcInvestorPosition setInvestorID(String investorID) {
		InvestorID = investorID;
		return this;
	}

	public FtdcInvestorPosition setPosiDirection(char posiDirection) {
		PosiDirection = posiDirection;
		return this;
	}

	public FtdcInvestorPosition setHedgeFlag(char hedgeFlag) {
		HedgeFlag = hedgeFlag;
		return this;
	}

	public FtdcInvestorPosition setPositionDate(char positionDate) {
		PositionDate = positionDate;
		return this;
	}

	public FtdcInvestorPosition setYdPosition(int ydPosition) {
		YdPosition = ydPosition;
		return this;
	}

	public FtdcInvestorPosition setPosition(int position) {
		Position = position;
		return this;
	}

	public FtdcInvestorPosition setLongFrozen(int longFrozen) {
		LongFrozen = longFrozen;
		return this;
	}

	public FtdcInvestorPosition setShortFrozen(int shortFrozen) {
		ShortFrozen = shortFrozen;
		return this;
	}

	public FtdcInvestorPosition setLongFrozenAmount(double longFrozenAmount) {
		LongFrozenAmount = longFrozenAmount;
		return this;
	}

	public FtdcInvestorPosition setShortFrozenAmount(double shortFrozenAmount) {
		ShortFrozenAmount = shortFrozenAmount;
		return this;
	}

	public FtdcInvestorPosition setOpenVolume(int openVolume) {
		OpenVolume = openVolume;
		return this;
	}

	public FtdcInvestorPosition setCloseVolume(int closeVolume) {
		CloseVolume = closeVolume;
		return this;
	}

	public FtdcInvestorPosition setOpenAmount(double openAmount) {
		OpenAmount = openAmount;
		return this;
	}

	public FtdcInvestorPosition setCloseAmount(double closeAmount) {
		CloseAmount = closeAmount;
		return this;
	}

	public FtdcInvestorPosition setPositionCost(double positionCost) {
		PositionCost = positionCost;
		return this;
	}

	public FtdcInvestorPosition setPreMargin(double preMargin) {
		PreMargin = preMargin;
		return this;
	}

	public FtdcInvestorPosition setUseMargin(double useMargin) {
		UseMargin = useMargin;
		return this;
	}

	public FtdcInvestorPosition setFrozenMargin(double frozenMargin) {
		FrozenMargin = frozenMargin;
		return this;
	}

	public FtdcInvestorPosition setFrozenCash(double frozenCash) {
		FrozenCash = frozenCash;
		return this;
	}

	public FtdcInvestorPosition setFrozenCommission(double frozenCommission) {
		FrozenCommission = frozenCommission;
		return this;
	}

	public FtdcInvestorPosition setCashIn(double cashIn) {
		CashIn = cashIn;
		return this;
	}

	public FtdcInvestorPosition setCommission(double commission) {
		Commission = commission;
		return this;
	}

	public FtdcInvestorPosition setCloseProfit(double closeProfit) {
		CloseProfit = closeProfit;
		return this;
	}

	public FtdcInvestorPosition setPositionProfit(double positionProfit) {
		PositionProfit = positionProfit;
		return this;
	}

	public FtdcInvestorPosition setPreSettlementPrice(double preSettlementPrice) {
		PreSettlementPrice = preSettlementPrice;
		return this;
	}

	public FtdcInvestorPosition setSettlementPrice(double settlementPrice) {
		SettlementPrice = settlementPrice;
		return this;
	}

	public FtdcInvestorPosition setTradingDay(String tradingDay) {
		TradingDay = tradingDay;
		return this;
	}

	public FtdcInvestorPosition setSettlementID(int settlementID) {
		SettlementID = settlementID;
		return this;
	}

	public FtdcInvestorPosition setOpenCost(double openCost) {
		OpenCost = openCost;
		return this;
	}

	public FtdcInvestorPosition setExchangeMargin(double exchangeMargin) {
		ExchangeMargin = exchangeMargin;
		return this;
	}

	public FtdcInvestorPosition setCombPosition(int combPosition) {
		CombPosition = combPosition;
		return this;
	}

	public FtdcInvestorPosition setCombLongFrozen(int combLongFrozen) {
		CombLongFrozen = combLongFrozen;
		return this;
	}

	public FtdcInvestorPosition setCombShortFrozen(int combShortFrozen) {
		CombShortFrozen = combShortFrozen;
		return this;
	}

	public FtdcInvestorPosition setCloseProfitByDate(double closeProfitByDate) {
		CloseProfitByDate = closeProfitByDate;
		return this;
	}

	public FtdcInvestorPosition setCloseProfitByTrade(double closeProfitByTrade) {
		CloseProfitByTrade = closeProfitByTrade;
		return this;
	}

	public FtdcInvestorPosition setTodayPosition(int todayPosition) {
		TodayPosition = todayPosition;
		return this;
	}

	public FtdcInvestorPosition setMarginRateByMoney(double marginRateByMoney) {
		MarginRateByMoney = marginRateByMoney;
		return this;
	}

	public FtdcInvestorPosition setMarginRateByVolume(double marginRateByVolume) {
		MarginRateByVolume = marginRateByVolume;
		return this;
	}

	public FtdcInvestorPosition setStrikeFrozen(int strikeFrozen) {
		StrikeFrozen = strikeFrozen;
		return this;
	}

	public FtdcInvestorPosition setStrikeFrozenAmount(double strikeFrozenAmount) {
		StrikeFrozenAmount = strikeFrozenAmount;
		return this;
	}

	public FtdcInvestorPosition setAbandonFrozen(int abandonFrozen) {
		AbandonFrozen = abandonFrozen;
		return this;
	}

	public FtdcInvestorPosition setExchangeID(String exchangeID) {
		ExchangeID = exchangeID;
		return this;
	}

	public FtdcInvestorPosition setYdStrikeFrozen(int ydStrikeFrozen) {
		YdStrikeFrozen = ydStrikeFrozen;
		return this;
	}

	public FtdcInvestorPosition setInvestUnitID(String investUnitID) {
		InvestUnitID = investUnitID;
		return this;
	}

	public FtdcInvestorPosition setPositionCostOffset(double positionCostOffset) {
		PositionCostOffset = positionCostOffset;
		return this;
	}

}
