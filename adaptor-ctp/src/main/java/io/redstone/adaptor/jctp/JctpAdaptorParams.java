package io.redstone.adaptor.jctp;

import io.mercury.common.param.ParamKey;
import io.mercury.common.param.ParamType;

public enum JctpAdaptorParams implements ParamKey {

	/**
	 * CTP Params
	 */
	CTP_Trader_Address(201, ParamType.STRING),

	CTP_Md_Address(202, ParamType.STRING),

	CTP_BrokerId(203, ParamType.STRING),

	CTP_InvestorId(204, ParamType.STRING),

	CTP_UserId(205, ParamType.STRING),

	CTP_AccountId(206, ParamType.STRING),

	CTP_Password(207, ParamType.STRING),

	;

	private int keyId;

	private ParamType paramType;

	private JctpAdaptorParams(int keyId, ParamType paramType) {
		this.keyId = keyId;
		this.paramType = paramType;
	}

	@Override
	public ParamType type() {
		return paramType;
	}

	@Override
	public int id() {
		return keyId;
	}

}
