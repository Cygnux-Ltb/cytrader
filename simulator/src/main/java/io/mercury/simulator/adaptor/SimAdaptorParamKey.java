package io.mercury.simulator.adaptor;

import io.mercury.common.param.AdaptorParamKey;
import io.mercury.common.param.ParamType;

public enum SimAdaptorParamKey implements AdaptorParamKey {

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

	private int id;

	private String key;

	private ParamType type;

	private SimAdaptorParamKey(int id, String key, ParamType type) {
		this.id = id;
		this.key = key;
		this.type = type;
	}

	@Override
	public int id() {
		return id;
	}

	@Override
	public String key() {
		return key;
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
