package io.mercury.ftdc.gateway.converter;

import java.util.function.Function;

import ctp.thostapi.CThostFtdcOrderField;
import io.mercury.ftdc.gateway.bean.FtdcOrder;

public class FromCThostFtdcOrder implements Function<CThostFtdcOrderField, FtdcOrder> {

	@Override
	public FtdcOrder apply(CThostFtdcOrderField from) {
		return new FtdcOrder()
				
				.setBrokerID(from.getBrokerID())
				
				.setInvestorID(from.getInvestorID())
				
				.setInstrumentID(from.getInstrumentID())
				
				.setOrderRef(from.getOrderRef())
				
				.setUserID(from.getUserID())
				
				.setOrderPriceType(from.getOrderPriceType())
				
				.setDirection(from.getDirection())
				
				.setCombOffsetFlag(from.getCombOffsetFlag())
				
				.setCombHedgeFlag(from.getCombHedgeFlag())
				
				.setLimitPrice(from.getLimitPrice())
				
				.setVolumeTotalOriginal(from.getVolumeTotalOriginal())
				
				.setTimeCondition(from.getTimeCondition())
				
				.setGTDDate(from.getGTDDate())
				
				.setVolumeCondition(from.getVolumeCondition())
				
				.setMinVolume(from.getMinVolume())
				
				.setContingentCondition(from.getContingentCondition())
				
				.setStopPrice(from.getStopPrice())
				
				.setForceCloseReason(from.getForceCloseReason())
				
				.setIsAutoSuspend(from.getIsAutoSuspend())
				
				.setBusinessUnit(from.getBusinessUnit())
				
				.setRequestID(from.getRequestID())
				
				.setOrderLocalID(from.getOrderLocalID())
				
				.setExchangeID(from.getExchangeID())
				
				.setParticipantID(from.getParticipantID())
				
				.setClientID(from.getClientID())
				
				.setExchangeInstID(from.getExchangeInstID())
				
				.setTraderID(from.getTraderID())
				
				.setInstallID(from.getInstallID())
				
				.setOrderSubmitStatus(from.getOrderSubmitStatus())
				
				.setNotifySequence(from.getNotifySequence())
				
				.setTradingDay(from.getTradingDay())
				
				.setSettlementID(from.getSettlementID())
				
				.setOrderSysID(from.getOrderSysID())
				
				.setOrderSource(from.getOrderSource())
				
				.setOrderStatus(from.getOrderStatus())
				
				.setOrderType(from.getOrderType())
				
				.setVolumeTraded(from.getVolumeTraded())
				
				.setVolumeTotal(from.getVolumeTotal())
				
				.setInsertDate(from.getInsertDate())
				
				.setInsertTime(from.getInsertTime())
				
				.setActiveTime(from.getActiveTime())
				
				.setSuspendTime(from.getSuspendTime())
				
				.setUpdateTime(from.getUpdateTime())
				
				.setCancelTime(from.getCancelTime())
				
				.setActiveTraderID(from.getActiveTraderID())
				
				.setClearingPartID(from.getClearingPartID())
				
				.setSequenceNo(from.getSequenceNo())
				
				.setFrontID(from.getFrontID())
				
				.setSessionID(from.getSessionID())
				
				.setUserProductInfo(from.getUserProductInfo())
				
				.setStatusMsg(from.getStatusMsg())
				
				.setUserForceClose(from.getUserForceClose())
				
				.setActiveUserID(from.getActiveUserID())
				
				.setBrokerOrderSeq(from.getBrokerOrderSeq())
				
				.setRelativeOrderSysID(from.getRelativeOrderSysID())
				
				.setZCETotalTradedVolume(from.getZCETotalTradedVolume())
				
				.setIsSwapOrder(from.getIsSwapOrder())
				
				.setBranchID(from.getBranchID())
				
				.setInvestUnitID(from.getInvestUnitID())
				
				.setAccountID(from.getAccountID())
				
				.setCurrencyID(from.getCurrencyID())
				
				.setIPAddress(from.getIPAddress())
				
				.setMacAddress(from.getMacAddress());
	
	}

}
