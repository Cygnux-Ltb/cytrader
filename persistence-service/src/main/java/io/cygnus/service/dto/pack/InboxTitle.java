package io.cygnus.service.dto.pack;

import static io.mercury.common.collections.MutableMaps.newUnifiedMap;

import java.util.Map;

import javax.annotation.Nonnull;

import io.mercury.common.codec.Envelope;

public enum InboxTitle implements Envelope {

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

	public static InboxTitle checkout(@Nonnull String name) {
		InboxTitle value;
		if ((value = Map.get(name)) != null)
			return value;
		throw new IllegalArgumentException("checkout with [" + name + "] is null");
	}

	@Override
	public int getCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
