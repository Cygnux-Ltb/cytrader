package io.cygnus.engine.strategy.impl;

import static io.mercury.common.collections.ImmutableMaps.immutableIntObjectMapFactory;
import static java.util.stream.Collectors.toSet;

import java.util.stream.Stream;

import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;

import io.horizon.structure.market.data.MarketData;
import io.horizon.structure.market.instrument.Instrument;
import io.mercury.common.param.Params;
import io.mercury.common.param.Params.ParamKey;

public abstract class StrategyMultiInstrument<M extends MarketData, PK extends ParamKey>
		extends AbstractStrategy<M, PK> {

	// 策略订阅的合约列表
	protected ImmutableIntObjectMap<Instrument> instruments;

	protected StrategyMultiInstrument(int strategyId, int subAccountId, Params<PK> params, Instrument... instruments) {
		super(strategyId, subAccountId, params);
		this.instruments = immutableIntObjectMapFactory().from(Stream.of(instruments).collect(toSet()),
				Instrument::getInstrumentId, instrument -> instrument);
	}

}
