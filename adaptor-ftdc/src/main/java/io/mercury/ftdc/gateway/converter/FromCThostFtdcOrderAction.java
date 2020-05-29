package io.mercury.ftdc.gateway.converter;

import java.util.function.Function;

import ctp.thostapi.CThostFtdcOrderActionField;
import io.mercury.ftdc.gateway.bean.FtdcOrderAction;

public class FromCThostFtdcOrderAction implements Function<CThostFtdcOrderActionField, FtdcOrderAction> {

	@Override
	public FtdcOrderAction apply(CThostFtdcOrderActionField from) {
		return new FtdcOrderAction()

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

				.setActionDate(from.getActionDate())

				.setActionTime(from.getActionTime())

				.setTraderID(from.getTraderID())

				.setInstallID(from.getInstallID())

				.setOrderLocalID(from.getOrderLocalID())

				.setActionLocalID(from.getActionLocalID())

				.setParticipantID(from.getParticipantID())

				.setClientID(from.getClientID())

				.setBusinessUnit(from.getBusinessUnit())

				.setOrderActionStatus(from.getOrderActionStatus())

				.setUserID(from.getUserID())

				.setStatusMsg(from.getStatusMsg())

				.setInstrumentID(from.getInstrumentID())

				.setBranchID(from.getBranchID())

				.setInvestUnitID(from.getInvestUnitID())

				.setIPAddress(from.getIPAddress())

				.setMacAddress(from.getMacAddress());

	}

}
