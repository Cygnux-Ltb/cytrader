package io.cygnuxltb.engine.strategy;

import io.horizon.market.data.MarketData;
import io.horizon.market.instrument.Instrument;
import io.horizon.trader.account.SubAccount;
import io.horizon.trader.adaptor.Adaptor;
import io.horizon.trader.strategy.Strategy;
import io.mercury.common.lang.Asserter;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.Params;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.stream.Stream;

import static io.mercury.common.collections.ImmutableMaps.getIntObjectMapFactory;
import static java.util.stream.Collectors.toSet;

public abstract class MultiInstrumentStrategy<M extends MarketData, K extends ParamKey>
        extends AbstractStrategy<M, K> {

    private final static Logger log = Log4j2LoggerFactory.getLogger(MultiInstrumentStrategy.class);

    // 策略订阅的合约列表
    protected ImmutableIntObjectMap<Instrument> instruments;

    protected Adaptor adaptor;

    protected MultiInstrumentStrategy(int strategyId, @Nonnull String strategyName, SubAccount subAccount,
                                      @Nonnull Instrument... instruments) {
        this(strategyId, strategyName, subAccount, null, instruments);
    }

    /**
     * @param strategyId   int
     * @param strategyName String
     * @param subAccount   SubAccount
     * @param params       Params<K>
     * @param instruments  Instrument...
     */
    protected MultiInstrumentStrategy(int strategyId, @Nonnull String strategyName, SubAccount subAccount,
                                      @Nullable Params<K> params, @Nonnull Instrument... instruments) {
        super(strategyId, strategyName, subAccount, params);
        this.instruments = getIntObjectMapFactory().from(Stream.of(instruments).collect(toSet()),
                Instrument::getInstrumentId, instrument -> instrument);
    }

    @Override
    @Nonnull
    public ImmutableIntObjectMap<Instrument> getInstruments() {
        return instruments;
    }

    @Override
    public Strategy<M> addAdaptor(@Nonnull Adaptor adaptor) {
        Asserter.nonNull(adaptor, "adaptor");
        this.adaptor = adaptor;
        log.info("added adaptor, strategyId -> {}, strategyName -> {}, adaptorId -> {}", strategyId, strategyName,
                adaptor.getAdaptorId());
        return this;
    }

}
