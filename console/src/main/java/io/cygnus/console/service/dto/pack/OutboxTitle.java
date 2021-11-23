package io.cygnus.console.service.dto.pack;

import static io.mercury.common.collections.MutableMaps.newUnifiedMap;

import java.util.Map;

import javax.annotation.Nonnull;

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

	public static OutboxTitle checkout(@Nonnull String name) {
		OutboxTitle value;
		if ((value = Map.get(name)) != null)
			return value;
		throw new IllegalArgumentException("checkout with [" + name + "] is null");
	}

	@Override
	public int getCode() {
		return 0;
	}

	@Override
	public int getVersion() {
		return 1;
	}

}
