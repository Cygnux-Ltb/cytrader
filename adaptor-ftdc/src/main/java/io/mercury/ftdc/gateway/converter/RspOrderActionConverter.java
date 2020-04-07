package io.mercury.ctp.gateway.converter;

import java.util.function.Function;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import io.mercury.ctp.gateway.bean.rsp.RspOrderAction;

public class RspOrderActionConverter implements Function<CThostFtdcInputOrderActionField, RspOrderAction> {

	@Override
	public RspOrderAction apply(CThostFtdcInputOrderActionField from) {
		return new RspOrderAction().setBrokerID(from.getBrokerID()).setInvestorID(from.getInvestorID())
				.setOrderActionRef(from.getOrderActionRef()).setOrderRef(from.getOrderRef())
				.setRequestID(from.getRequestID()).setFrontID(from.getFrontID()).setSessionID(from.getSessionID())
				.setExchangeID(from.getExchangeID()).setOrderSysID(from.getOrderSysID())
				.setActionFlag(from.getActionFlag()).setLimitPrice(from.getLimitPrice())
				.setVolumeChange(from.getVolumeChange()).setUserID(from.getUserID())
				.setInstrumentID(from.getInstrumentID()).setInvestUnitID(from.getInvestUnitID())
				.setIPAddress(from.getIPAddress()).setMacAddress(from.getMacAddress());

	}
}
