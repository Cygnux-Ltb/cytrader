package io.ffreedom.redstone.adaptor.jctp;

import io.ffreedom.common.param.ParamKey;
import io.ffreedom.common.param.ParamType;

public enum JctpAdaptorParams implements ParamKey {

	/**
	 * CTP Params
	 */
	CTP_Trader_Address(201, "CTP_Trader_Address", ParamType.STRING),

	CTP_Md_Address(202, "CTP_Md_Address", ParamType.STRING),

	CTP_BrokerId(203, "CTP_BrokerId", ParamType.STRING),

	CTP_InvestorId(204, "CTP_InvestorId", ParamType.STRING),

	CTP_UserId(205, "CTP_UserId", ParamType.STRING),

	CTP_AccountId(206, "CTP_AccountId", ParamType.STRING),

	CTP_Password(207, "CTP_Password", ParamType.STRING),

	;

	private int keyId;

	private String fullName;

	private ParamType paramType;

	private JctpAdaptorParams(int keyId, String fullName, ParamType paramType) {
		this.keyId = keyId;
		this.fullName = fullName;
		this.paramType = paramType;
	}

	@Override
	public String fullName() {
		return fullName;
	}

	@Override
	public ParamType getParamType() {
		return paramType;
	}

	@Override
	public int getKeyId() {
		return keyId;
	}

}
