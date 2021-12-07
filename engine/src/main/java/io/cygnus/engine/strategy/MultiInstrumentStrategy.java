package io.cygnus.engine.strategy;

import static io.mercury.common.collections.ImmutableMaps.getIntObjectMapFactory;
import static java.util.stream.Collectors.toSet;

import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;

import io.cygnus.engine.strategy.api.Strategy;
import io.cygnus.engine.strategy.api.StrategySign;
import io.horizon.market.data.MarketData;
import io.horizon.market.instrument.Instrument;
import io.horizon.trader.account.SubAccount;
import io.horizon.trader.adaptor.Adaptor;
import io.mercury.common.lang.Assertor;
import io.mercury.common.param.Params;
import io.mercury.common.param.Params.ParamKey;

public abstract class MultiInstrumentStrategy<M extends MarketData, PK extends ParamKey>
		extends AbstractStrategy<M, PK> {

	// 策略订阅的合约列表

	protected ImmutableIntObjectMap<Instrument> instruments;

	protected Adaptor adaptor;

	protected MultiInstrumentStrategy(StrategySign sign, SubAccount subAccount, @Nonnull Instrument... instruments) {
		this(sign, subAccount, null, instruments);
	}

	protected MultiInstrumentStrategy(StrategySign sign, SubAccount subAccount, @Nullable Params<PK> params,
			@Nonnull Instrument... instruments) {
		super(sign, subAccount, params);
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
