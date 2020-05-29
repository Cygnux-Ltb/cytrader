package io.mercury.ftdc.gateway.converter;

import java.util.function.Function;

import ctp.thostapi.CThostFtdcTradeField;
import io.mercury.ftdc.gateway.bean.FtdcTrade;

public class FromCThostFtdcTrade implements Function<CThostFtdcTradeField, FtdcTrade> {

	@Override
	public FtdcTrade apply(CThostFtdcTradeField from) {
		return new FtdcTrade()
				
				.setBrokerID(from.getBrokerID())
				
				.setInvestorID(from.getInvestorID())
				
				.setInstrumentID(from.getInstrumentID())
				
				.setOrderRef(from.getOrderRef())
				
				.setUserID(from.getUserID())
				
				.setExchangeID(from.getExchangeID())
				
				.setTradeID(from.getTradeID())
				
				.setDirection(from.getDirection())
				
				.setOrderSysID(from.getOrderSysID())
				
				.setParticipantID(from.getParticipantID())
				
				.setClientID(from.getClientID())
				
				.setTradingRole(from.getTradingRole())
				
				.setExchangeInstID(from.getExchangeInstID())
				
				.setOffsetFlag(from.getOffsetFlag())
				
				.setHedgeFlag(from.getHedgeFlag())
				
				.setPrice(from.getPrice())
				
				.setVolume(from.getVolume())
				
				.setTradeDate(from.getTradeDate())
				
				.setTradeTime(from.getTradeTime())
				
				.setTradeType(from.getTradeType())
				
				.setPriceSource(from.getPriceSource())
				
				.setTraderID(from.getTraderID())
				
				.setOrderLocalID(from.getOrderLocalID())
				
				.setClearingPartID(from.getClearingPartID())
				
				.setBusinessUnit(from.getBusinessUnit())
				
				.setSequenceNo(from.getSequenceNo())
				
				.setTradingDay(from.getTradingDay())
				
				.setSettlementID(from.getSettlementID())
				
				.setBrokerOrderSeq(from.getBrokerOrderSeq())
				
				.setTradeSource(from.getTradeSource())
				
				.setInvestUnitID(from.getInvestUnitID());
	
	}

}
