package io.cygnus.service.dto.pack;

import static io.mercury.common.collections.MutableMaps.newUnifiedMap;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.map.MutableMap;

import io.mercury.common.codec.Envelope;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum InboxTitle implements Envelope {

	Unknown(-1),
	
	Heartbeat(0),

	LastPrice(1),

	Bar(2),

	Order(3),

	StrategyInstrumentPNLDaily(),

	Init(),

	InitFinish(),

	LogInfo(),

	StrategySwitch(),

	UpdateStrategyParams(),

	UpdateStrategySignals(),

	MetricData(7),

	;
	
	@Getter
	private final int code;
	
	private static final MutableMap<String, InboxTitle> Map = newUnifiedMap();

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



}
