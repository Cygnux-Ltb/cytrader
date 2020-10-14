package io.mercury.redstone.runtime.config.entity.pack;

import java.util.HashMap;
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

	private static Map<String, InboxTitle> map = new HashMap<>();
	static {
		for (InboxTitle title : InboxTitle.values()) {
			map.put(title.name(), title);
		}
	}

	public static InboxTitle checkOf(String name) {
		if (map.containsKey(name)) {
			return map.get(name);
		}
		return null;
	}

}
