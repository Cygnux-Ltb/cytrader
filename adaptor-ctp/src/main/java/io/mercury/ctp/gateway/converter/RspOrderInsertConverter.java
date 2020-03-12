package io.mercury.gateway.ctp.converter;

import java.util.function.Function;

import ctp.thostapi.CThostFtdcInputOrderField;
import io.mercury.gateway.ctp.bean.rsp.RspOrderInsert;

public class RspOrderInsertConverter implements Function<CThostFtdcInputOrderField, RspOrderInsert> {

	@Override
	public RspOrderInsert apply(CThostFtdcInputOrderField from) {
		return new RspOrderInsert().setBrokerID(from.getBrokerID()).setInvestorID(from.getInvestorID())
				.setInstrumentID(from.getInstrumentID()).setOrderRef(from.getOrderRef()).setUserID(from.getUserID())
				.setOrderPriceType(from.getOrderPriceType()).setDirection(from.getDirection())
				.setCombOffsetFlag(from.getCombOffsetFlag()).setCombHedgeFlag(from.getCombHedgeFlag())
				.setLimitPrice(from.getLimitPrice()).setVolumeTotalOriginal(from.getVolumeTotalOriginal())
				.setTimeCondition(from.getTimeCondition()).setGTDDate(from.getGTDDate())
				.setVolumeCondition(from.getVolumeCondition()).setMinVolume(from.getMinVolume())
				.setContingentCondition(from.getContingentCondition()).setStopPrice(from.getStopPrice())
				.setForceCloseReason(from.getForceCloseReason()).setIsAutoSuspend(from.getIsAutoSuspend())
				.setBusinessUnit(from.getBusinessUnit()).setRequestID(from.getRequestID())
				.setUserForceClose(from.getUserForceClose()).setIsSwapOrder(from.getIsSwapOrder())
				.setExchangeID(from.getExchangeID()).setInvestUnitID(from.getInvestUnitID())
				.setAccountID(from.getAccountID()).setCurrencyID(from.getCurrencyID()).setClientID(from.getClientID())
				.setIPAddress(from.getIPAddress()).setMacAddress(from.getMacAddress());
	}

}
