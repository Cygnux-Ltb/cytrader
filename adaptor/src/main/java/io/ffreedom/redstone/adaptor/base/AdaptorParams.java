package io.ffreedom.redstone.adaptor.base;

import io.ffreedom.common.param.ParamKey;
import io.ffreedom.common.param.ParamType;

public enum AdaptorParams implements ParamKey {

	/**
	 * Simulator Params
	 */
	SIM_MD_HOST(101, "Simulator MarketData Host", ParamType.STRING),

	SIM_MD_PORT(102, "Simulator MarketData Port", ParamType.INTEGER),

	SIM_TD_HOST(103, "Simulator Trading Host", ParamType.STRING),

	SIM_TD_PORT(104, "Simulator Trading Port", ParamType.INTEGER),

	/**
	 * CTP Params
	 */
	CTP_MQ_HOST(201, "CTP MessageQueue Host", ParamType.STRING),

	CTP_MQ_PORT(202, "CTP MessageQueue Port", ParamType.INTEGER),

	CTP_MQ_USERNAME(203, "CTP MessageQueue Username", ParamType.STRING),

	CTP_MQ_PASSWORD(204, "CTP MessageQueue Password", ParamType.STRING),

	CTP_QNAME_INBOUND(205, "CTP MessageQueue Inbound Queue Name", ParamType.STRING),

	CTP_QNAME_OUTBOUND(206, "CTP MessageQueue Outbound Queue Name", ParamType.STRING),

	;

	private int keyId;

	private String fullName;

	private ParamType paramType;

	private AdaptorParams(int keyId, String fullName, ParamType paramType) {
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
