package io.redstone.adaptor.simulator;

import io.mercury.common.param.api.ParamKey;
import io.mercury.common.param.api.ParamType;

public enum SimAdaptorParam implements ParamKey {

	/**
	 * Simulator Params
	 */
	MdHost(101, "md.host", ParamType.STRING),

	MdPort(102, "md.port", ParamType.INT),

	TdHost(103, "td.host", ParamType.STRING),

	TdPort(104, "td.port", ParamType.INT),

	TradingDayStart(105, "trading.day.start", ParamType.DATE),

	TradingDayEnd(106, "trading.day.end", ParamType.DATE),

	;

	private int paramId;

	private String paramName;

	private ParamType paramType;

	private SimAdaptorParam(int paramId, String paramName, ParamType paramType) {
		this.paramId = paramId;
		this.paramName = paramName;
		this.paramType = paramType;
	}

	@Override
	public int paramId() {
		return paramId;
	}

	@Override
	public String paramName() {
		return paramName;
	}

	@Override
	public ParamType paramType() {
		return paramType;
	}

}
