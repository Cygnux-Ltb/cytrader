package io.cygnuxltb.engine.strategy;

import io.horizon.market.data.MarketData;
import io.horizon.market.instrument.Instrument;
import io.horizon.trader.account.SubAccount;
import io.horizon.trader.adaptor.Adaptor;
import io.horizon.trader.strategy.Strategy;
import io.horizon.trader.transport.outbound.TdxAdaptorReport;
import io.mercury.common.datetime.EpochTime;
import io.mercury.common.lang.Asserter;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.Params;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static io.mercury.common.collections.ImmutableMaps.getIntObjectMapFactory;
import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

public abstract class SingleInstrumentStrategy<M extends MarketData, K extends ParamKey>
        extends AbstractStrategy<M, K> {

    private static final Logger log = getLogger(SingleInstrumentStrategy.class);

    // 策略订阅的合约
    protected Instrument instrument;

    // 策略订阅的合约列表
    protected ImmutableIntObjectMap<Instrument> instruments;

    private Adaptor adaptor;

    /**
     * @param strategyId   int
     * @param strategyName String
     * @param subAccount   String
     * @param instrument   SubAccount
     */
    protected SingleInstrumentStrategy(int strategyId, @Nonnull String strategyName, SubAccount subAccount,
                                       Instrument instrument) {
        this(strategyId, strategyName, subAccount, null, instrument);
    }

    /**
     * @param strategyId   int
     * @param strategyName String
     * @param subAccount   SubAccount
     * @param params       Params<K>
     * @param instrument   Instrument
     */
    protected SingleInstrumentStrategy(int strategyId, @Nonnull String strategyName, SubAccount subAccount,
                                       @Nullable Params<K> params, Instrument instrument) {
        super(strategyId, strategyName, subAccount, params);
        this.instrument = instrument;
        this.instruments = getIntObjectMapFactory().of(instrument.getInstrumentId(), instrument);
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
        return this;
    }

    @Override
    protected Adaptor getAdaptor(@Nonnull Instrument instrument) {
        return adaptor;
    }

    @Override
    public void onAdaptorReport(@Nonnull TdxAdaptorReport event) {
        log.info("{} :: On adaptor status callback, adaptorId==[{}], status==[{}]", getStrategyName(),
                event.getAdaptorId(), event.getStatus());
        switch (event.getStatus()) {
            case MD_ENABLE -> {
                log.info("{} :: Handle adaptor MdEnable, adaptorId==[{}]", getStrategyName(), event.getAdaptorId());
                adaptor.subscribeMarketData(new Instrument[]{instrument});
                log.info("{} :: Call subscribeMarketData, instrument -> {}", getStrategyName(), instrument);
            }
            case TRADER_ENABLE -> {
                log.info("{} :: Handle adaptor TdEnable, adaptorId==[{}]", getStrategyName(), event.getAdaptorId());
                // TODO
//			adaptor.queryOrder(null);
//			log.info("{} :: Call queryOrder, adaptorId==[{}], account is default", getStrategyName(),
//					event.getAdaptorId());
                adaptor.queryPositions(queryPositionsReq.setExchangeCode(instrument.getExchangeCode())
                        .setInstrumentCode(instrument.getInstrumentCode()).setGenerateTime(EpochTime.getEpochMillis()));
                log.info("{} :: Call queryPositions, adaptorId==[{}], account is default", getStrategyName(),
                        event.getAdaptorId());
                adaptor.queryBalance(queryBalanceReq.setGenerateTime(EpochTime.getEpochMillis()));
                log.info("{} :: Call queryBalance, adaptorId==[{}], account is default", getStrategyName(),
                        event.getAdaptorId());
            }
            default -> log.warn("{} unhandled event received {}", getStrategyName(), event);
        }
    }

}
