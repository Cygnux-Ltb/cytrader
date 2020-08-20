package io.mercury.simulator.adaptor;

import io.mercury.common.param.ParamType;
import io.mercury.redstone.core.adaptor.AdaptorParamKey;

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

	private String kname;

	private ParamType type;

	private SimAdaptorParamKey(String kname, ParamType type) {
		this.kname = kname;
		this.type = type;
	}

	@Override
	public int id() {
		return ordinal();
	}

	@Override
	public String kname() {
		return kname;
	}

	@Override
	public ParamType type() {
		return type;
	}

	@Override
	public String adaptorName() {
		return "SimAdaptor";
	}

}
