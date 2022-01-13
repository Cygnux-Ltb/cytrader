package io.cygnus.engine.strategy;

import static io.mercury.common.collections.ImmutableMaps.getIntObjectMapFactory;
import static java.util.stream.Collectors.toSet;

import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;

import io.horizon.market.data.MarketData;
import io.horizon.market.instrument.Instrument;
import io.horizon.trader.account.SubAccount;
import io.horizon.trader.adaptor.Adaptor;
import io.horizon.trader.strategy.Strategy;
import io.mercury.common.lang.Assertor;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.Params;

public abstract class MultiInstrumentStrategy<M extends MarketData, K extends ParamKey> extends AbstractStrategy<M, K> {

	// 策略订阅的合约列表
	protected ImmutableIntObjectMap<Instrument> instruments;

	protected Adaptor adaptor;

	protected MultiInstrumentStrategy(int strategyId, @Nonnull String strategyName, SubAccount subAccount,
			@Nonnull Instrument... instruments) {
		this(strategyId, strategyName, subAccount, null, instruments);
	}

	protected MultiInstrumentStrategy(int strategyId, @Nonnull String strategyName, SubAccount subAccount,
			@Nullable Params<K> params, @Nonnull Instrument... instruments) {
		super(strategyId, strategyName, subAccount, params);
		this.instruments = getIntObjectMapFactory().from(Stream.of(instruments).collect(toSet()),
				Instrument::getInstrumentId, instrument -> instrument);
	}

	@Override
	public ImmutableIntObjectMap<Instrument> getInstruments() {
		return instruments;
	}

	@Override
	public Strategy<M> addAdaptor(Adaptor adaptor) {
		Assertor.nonNull(adaptor, "adaptor");
		this.adaptor = adaptor;
		return this;
	}

}
