package io.cygnus.service.dto.pack;

import static io.mercury.common.collections.MutableMaps.newUnifiedMap;

import java.util.Map;

public enum InboxTitle {

	LastPrice,

	Heartbeat,

	Order,

	TimeBinner,

	StrategyInstrumentPNLDaily,

	Init,

	InitFinish,

	LogInfo,

	StrategySwitch,

	UpdateStrategyParams,

	UpdateStrategySignals,

	MetricData,

	;

	private static final Map<String, InboxTitle> Map = newUnifiedMap();

	static {
		for (InboxTitle value : InboxTitle.values())
			Map.put(value.name(), value);
	}

	public static InboxTitle checkout(String name) {
		InboxTitle value;
		if ((value = Map.get(name)) != null)
			return value;
		throw new IllegalArgumentException("Checkout with [" + name + "] is null");
	}

}
