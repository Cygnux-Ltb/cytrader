package io.redstone.runtime.config.entity.pack;

import java.util.HashMap;
import java.util.Map;

public enum OutboxTitle {

	Heartbeat,

	TimeBinner,

	CygInfo,

	CygStrategy,

	Strategy,

	StrategyParam,

	StrategySymbol,

	StrategyInstrumentPNLDaily,

	SymbolInfo,

	SymbolTradingFee,

	SymbolTradingPeriod,

	TradeableInstrument,

	InitFinish,

	StrategySwitch,

	UpdateStrategyParams,

	EndTimeBinner,

	UpdateStrategySignals,
	
	StrategySignal,

	Signal,
	
	SignalParam,
	
	SignalSymbol,
	
	CygInitConfig,
	;

	private static Map<String, OutboxTitle> map = new HashMap<>();
	static {
		for (OutboxTitle title : OutboxTitle.values()) {
			map.put(title.name(), title);
		}
	}

	public static OutboxTitle checkOf(String name) {
		if (map.containsKey(name)) {
			return map.get(name);
		}
		return null;
	}

}
