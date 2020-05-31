package io.mercury.ftdc.gateway.bean;

public final class FtdcInvestorPosition {
	
	
	public void setInstrumentID(String var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_InstrumentID_set(this.swigCPtr, this, var1);
	}

	public String getInstrumentID() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_InstrumentID_get(this.swigCPtr, this);
	}

	public void setBrokerID(String var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_BrokerID_set(this.swigCPtr, this, var1);
	}

	public String getBrokerID() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_BrokerID_get(this.swigCPtr, this);
	}

	public void setInvestorID(String var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_InvestorID_set(this.swigCPtr, this, var1);
	}

	public String getInvestorID() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_InvestorID_get(this.swigCPtr, this);
	}

	public void setPosiDirection(char var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_PosiDirection_set(this.swigCPtr, this, var1);
	}

	public char getPosiDirection() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_PosiDirection_get(this.swigCPtr, this);
	}

	public void setHedgeFlag(char var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_HedgeFlag_set(this.swigCPtr, this, var1);
	}

	public char getHedgeFlag() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_HedgeFlag_get(this.swigCPtr, this);
	}

	public void setPositionDate(char var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_PositionDate_set(this.swigCPtr, this, var1);
	}

	public char getPositionDate() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_PositionDate_get(this.swigCPtr, this);
	}

	public void setYdPosition(int var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_YdPosition_set(this.swigCPtr, this, var1);
	}

	public int getYdPosition() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_YdPosition_get(this.swigCPtr, this);
	}

	public void setPosition(int var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_Position_set(this.swigCPtr, this, var1);
	}

	public int getPosition() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_Position_get(this.swigCPtr, this);
	}

	public void setLongFrozen(int var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_LongFrozen_set(this.swigCPtr, this, var1);
	}

	public int getLongFrozen() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_LongFrozen_get(this.swigCPtr, this);
	}

	public void setShortFrozen(int var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_ShortFrozen_set(this.swigCPtr, this, var1);
	}

	public int getShortFrozen() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_ShortFrozen_get(this.swigCPtr, this);
	}

	public void setLongFrozenAmount(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_LongFrozenAmount_set(this.swigCPtr, this, var1);
	}

	public double getLongFrozenAmount() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_LongFrozenAmount_get(this.swigCPtr, this);
	}

	public void setShortFrozenAmount(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_ShortFrozenAmount_set(this.swigCPtr, this, var1);
	}

	public double getShortFrozenAmount() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_ShortFrozenAmount_get(this.swigCPtr, this);
	}

	public void setOpenVolume(int var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_OpenVolume_set(this.swigCPtr, this, var1);
	}

	public int getOpenVolume() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_OpenVolume_get(this.swigCPtr, this);
	}

	public void setCloseVolume(int var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_CloseVolume_set(this.swigCPtr, this, var1);
	}

	public int getCloseVolume() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_CloseVolume_get(this.swigCPtr, this);
	}

	public void setOpenAmount(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_OpenAmount_set(this.swigCPtr, this, var1);
	}

	public double getOpenAmount() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_OpenAmount_get(this.swigCPtr, this);
	}

	public void setCloseAmount(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_CloseAmount_set(this.swigCPtr, this, var1);
	}

	public double getCloseAmount() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_CloseAmount_get(this.swigCPtr, this);
	}

	public void setPositionCost(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_PositionCost_set(this.swigCPtr, this, var1);
	}

	public double getPositionCost() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_PositionCost_get(this.swigCPtr, this);
	}

	public void setPreMargin(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_PreMargin_set(this.swigCPtr, this, var1);
	}

	public double getPreMargin() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_PreMargin_get(this.swigCPtr, this);
	}

	public void setUseMargin(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_UseMargin_set(this.swigCPtr, this, var1);
	}

	public double getUseMargin() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_UseMargin_get(this.swigCPtr, this);
	}

	public void setFrozenMargin(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_FrozenMargin_set(this.swigCPtr, this, var1);
	}

	public double getFrozenMargin() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_FrozenMargin_get(this.swigCPtr, this);
	}

	public void setFrozenCash(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_FrozenCash_set(this.swigCPtr, this, var1);
	}

	public double getFrozenCash() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_FrozenCash_get(this.swigCPtr, this);
	}

	public void setFrozenCommission(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_FrozenCommission_set(this.swigCPtr, this, var1);
	}

	public double getFrozenCommission() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_FrozenCommission_get(this.swigCPtr, this);
	}

	public void setCashIn(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_CashIn_set(this.swigCPtr, this, var1);
	}

	public double getCashIn() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_CashIn_get(this.swigCPtr, this);
	}

	public void setCommission(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_Commission_set(this.swigCPtr, this, var1);
	}

	public double getCommission() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_Commission_get(this.swigCPtr, this);
	}

	public void setCloseProfit(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_CloseProfit_set(this.swigCPtr, this, var1);
	}

	public double getCloseProfit() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_CloseProfit_get(this.swigCPtr, this);
	}

	public void setPositionProfit(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_PositionProfit_set(this.swigCPtr, this, var1);
	}

	public double getPositionProfit() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_PositionProfit_get(this.swigCPtr, this);
	}

	public void setPreSettlementPrice(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_PreSettlementPrice_set(this.swigCPtr, this, var1);
	}

	public double getPreSettlementPrice() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_PreSettlementPrice_get(this.swigCPtr, this);
	}

	public void setSettlementPrice(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_SettlementPrice_set(this.swigCPtr, this, var1);
	}

	public double getSettlementPrice() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_SettlementPrice_get(this.swigCPtr, this);
	}

	public void setTradingDay(String var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_TradingDay_set(this.swigCPtr, this, var1);
	}

	public String getTradingDay() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_TradingDay_get(this.swigCPtr, this);
	}

	public void setSettlementID(int var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_SettlementID_set(this.swigCPtr, this, var1);
	}

	public int getSettlementID() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_SettlementID_get(this.swigCPtr, this);
	}

	public void setOpenCost(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_OpenCost_set(this.swigCPtr, this, var1);
	}

	public double getOpenCost() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_OpenCost_get(this.swigCPtr, this);
	}

	public void setExchangeMargin(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_ExchangeMargin_set(this.swigCPtr, this, var1);
	}

	public double getExchangeMargin() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_ExchangeMargin_get(this.swigCPtr, this);
	}

	public void setCombPosition(int var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_CombPosition_set(this.swigCPtr, this, var1);
	}

	public int getCombPosition() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_CombPosition_get(this.swigCPtr, this);
	}

	public void setCombLongFrozen(int var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_CombLongFrozen_set(this.swigCPtr, this, var1);
	}

	public int getCombLongFrozen() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_CombLongFrozen_get(this.swigCPtr, this);
	}

	public void setCombShortFrozen(int var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_CombShortFrozen_set(this.swigCPtr, this, var1);
	}

	public int getCombShortFrozen() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_CombShortFrozen_get(this.swigCPtr, this);
	}

	public void setCloseProfitByDate(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_CloseProfitByDate_set(this.swigCPtr, this, var1);
	}

	public double getCloseProfitByDate() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_CloseProfitByDate_get(this.swigCPtr, this);
	}

	public void setCloseProfitByTrade(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_CloseProfitByTrade_set(this.swigCPtr, this, var1);
	}

	public double getCloseProfitByTrade() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_CloseProfitByTrade_get(this.swigCPtr, this);
	}

	public void setTodayPosition(int var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_TodayPosition_set(this.swigCPtr, this, var1);
	}

	public int getTodayPosition() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_TodayPosition_get(this.swigCPtr, this);
	}

	public void setMarginRateByMoney(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_MarginRateByMoney_set(this.swigCPtr, this, var1);
	}

	public double getMarginRateByMoney() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_MarginRateByMoney_get(this.swigCPtr, this);
	}

	public void setMarginRateByVolume(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_MarginRateByVolume_set(this.swigCPtr, this, var1);
	}

	public double getMarginRateByVolume() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_MarginRateByVolume_get(this.swigCPtr, this);
	}

	public void setStrikeFrozen(int var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_StrikeFrozen_set(this.swigCPtr, this, var1);
	}

	public int getStrikeFrozen() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_StrikeFrozen_get(this.swigCPtr, this);
	}

	public void setStrikeFrozenAmount(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_StrikeFrozenAmount_set(this.swigCPtr, this, var1);
	}

	public double getStrikeFrozenAmount() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_StrikeFrozenAmount_get(this.swigCPtr, this);
	}

	public void setAbandonFrozen(int var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_AbandonFrozen_set(this.swigCPtr, this, var1);
	}

	public int getAbandonFrozen() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_AbandonFrozen_get(this.swigCPtr, this);
	}

	public void setExchangeID(String var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_ExchangeID_set(this.swigCPtr, this, var1);
	}

	public String getExchangeID() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_ExchangeID_get(this.swigCPtr, this);
	}

	public void setYdStrikeFrozen(int var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_YdStrikeFrozen_set(this.swigCPtr, this, var1);
	}

	public int getYdStrikeFrozen() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_YdStrikeFrozen_get(this.swigCPtr, this);
	}

	public void setInvestUnitID(String var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_InvestUnitID_set(this.swigCPtr, this, var1);
	}

	public String getInvestUnitID() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_InvestUnitID_get(this.swigCPtr, this);
	}

	public void setPositionCostOffset(double var1) {
		thosttraderapiJNI.CThostFtdcInvestorPositionField_PositionCostOffset_set(this.swigCPtr, this, var1);
	}

	public double getPositionCostOffset() {
		return thosttraderapiJNI.CThostFtdcInvestorPositionField_PositionCostOffset_get(this.swigCPtr, this);
	}


}
