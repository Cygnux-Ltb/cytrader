package io.cygnus.service.dto.pack;

import static io.mercury.common.collections.MutableMaps.newUnifiedMap;

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

	private static final Map<String, OutboxTitle> Map = newUnifiedMap();
	static {
		for (OutboxTitle value : OutboxTitle.values())
			Map.put(value.name(), value);
	}

	public static OutboxTitle checkout(String name) {
		OutboxTitle value;
		if ((value = Map.get(name)) != null)
			return value;
		throw new IllegalArgumentException("Checkout with [" + name + "] is null");
	}

}
