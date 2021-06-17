package io.cygnus.service.dto.pack;

import static io.mercury.common.collections.MutableMaps.newUnifiedMap;

import java.util.Map;

import io.mercury.common.codec.Envelope;

public enum OutboxTitle implements Envelope {

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

	@Override
	public int getCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
