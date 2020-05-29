package io.mercury.ftdc.gateway.converter;

import java.util.function.Function;

import ctp.thostapi.CThostFtdcInputOrderActionField;
import io.mercury.ftdc.gateway.bean.FtdcInputOrderAction;

public final class FromCThostFtdcInputOrderAction
		implements Function<CThostFtdcInputOrderActionField, FtdcInputOrderAction> {

	@Override
	public FtdcInputOrderAction apply(CThostFtdcInputOrderActionField from) {
		return new FtdcInputOrderAction()

				.setBrokerID(from.getBrokerID())

				.setInvestorID(from.getInvestorID())

				.setOrderActionRef(from.getOrderActionRef())

				.setOrderRef(from.getOrderRef())

				.setRequestID(from.getRequestID())

				.setFrontID(from.getFrontID())

				.setSessionID(from.getSessionID())

				.setExchangeID(from.getExchangeID())

				.setOrderSysID(from.getOrderSysID())

				.setActionFlag(from.getActionFlag())

				.setLimitPrice(from.getLimitPrice())

				.setVolumeChange(from.getVolumeChange())

				.setUserID(from.getUserID())

				.setInstrumentID(from.getInstrumentID())

				.setInvestUnitID(from.getInvestUnitID())

				.setIPAddress(from.getIPAddress())

				.setMacAddress(from.getMacAddress());

	}
	
}
