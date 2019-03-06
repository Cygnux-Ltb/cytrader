package io.ffreedom.redstone.adaptor.simulator;

import io.ffreedom.common.param.ParamKey;
import io.ffreedom.common.param.ParamType;

public enum SimAdaptorParams implements ParamKey {

	/**
	 * Simulator Params
	 */
	SIM_MD_HOST(101, "Simulator MarketData Host", ParamType.STRING),

	SIM_MD_PORT(102, "Simulator MarketData Port", ParamType.INTEGER),

	SIM_TD_HOST(103, "Simulator Trading Host", ParamType.STRING),

	SIM_TD_PORT(104, "Simulator Trading Port", ParamType.INTEGER),

	;

	private int keyId;

	private String fullName;

	private ParamType paramType;

	private SimAdaptorParams(int keyId, String fullName, ParamType paramType) {
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
