package io.cygnus.simulator.adaptor;

import io.horizon.structure.adaptor.AdaptorParamKey;
import io.mercury.common.param.ParamType;

public enum SimAdaptorParamKey implements AdaptorParamKey {

	/**
	 * Simulator Params
	 */
	MdHost("md.host", ParamType.STRING),

	MdPort("md.port", ParamType.INT),

	TdHost("td.host", ParamType.STRING),

	TdPort("td.port", ParamType.INT),

	TradingDayStart("trading.day.start", ParamType.DATE),

	TradingDayEnd("trading.day.end", ParamType.DATE),

	;

	private String paramName;

	private ParamType type;

	private SimAdaptorParamKey(String paramName, ParamType type) {
		this.paramName = paramName;
		this.type = type;
	}

	@Override
	public int id() {
		return ordinal();
	}

	@Override
	public String paramName() {
		return paramName;
	}

	@Override
	public ParamType type() {
		return type;
	}

	@Override
	public String getAdaptorName() {
		return "SimAdaptor";
	}

}
