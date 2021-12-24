package io.cygnus.engine.strategy;

import static io.mercury.common.collections.ImmutableMaps.getIntObjectMapFactory;
import static io.mercury.common.log.Log4j2LoggerFactory.getLogger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.slf4j.Logger;

import io.horizon.market.data.MarketData;
import io.horizon.market.instrument.Instrument;
import io.horizon.trader.account.SubAccount;
import io.horizon.trader.adaptor.Adaptor;
import io.horizon.trader.adaptor.AdaptorStatus;
import io.horizon.trader.report.AdaptorReport;
import io.horizon.trader.strategy.Strategy;
import io.mercury.common.lang.Assertor;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.Params;

public abstract class SingleInstrumentStrategy<M extends MarketData, K extends ParamKey>
		extends AbstractStrategy<M, K> {

	// Logger
	private static final Logger log = getLogger(SingleInstrumentStrategy.class);

	// 策略订阅的合约
	protected Instrument instrument;

	// 策略订阅的合约列表
	protected ImmutableIntObjectMap<Instrument> instruments;

	private Adaptor adaptor;

	/**
	 * 
	 * @param strategyId
	 * @param subAccount
	 * @param instrument
	 */
	protected SingleInstrumentStrategy(int strategyId, @Nonnull String strategyName, SubAccount subAccount,
			Instrument instrument) {
		this(strategyId, strategyName, subAccount, null, instrument);
	}

	/**
	 * 
	 * @param strategyId
	 * @param subAccount
	 * @param params
	 * @param instrument
	 */
	protected SingleInstrumentStrategy(int strategyId, @Nonnull String strategyName, SubAccount subAccount,
			@Nullable Params<K> params, Instrument instrument) {
		super(strategyId, strategyName, subAccount, params);
		this.instrument = instrument;
		this.instruments = getIntObjectMapFactory().of(instrument.getInstrumentId(), instrument);
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

	@Override
	protected Adaptor getAdaptor(Instrument instrument) {
		return adaptor;
	}

	@Override
	public void onAdaptorReport(AdaptorReport event) {
		log.info("{} :: On adaptor status callback, adaptorId==[{}], status==[{}]", getStrategyName(),
				event.getAdaptorId(), event.getStatus());
		switch (event.getStatus()) {
		case AdaptorStatus.Code.MD_ENABLE:
			log.info("{} :: Handle adaptor MdEnable, adaptorId==[{}]", getStrategyName(), event.getAdaptorId());
			adaptor.subscribeMarketData(new Instrument[] { instrument });
			log.info("{} :: Call subscribeMarketData, instrument -> {}", getStrategyName(), instrument);
			break;
		case AdaptorStatus.Code.TRADER_ENABLE:
			log.info("{} :: Handle adaptor TdEnable, adaptorId==[{}]", getStrategyName(), event.getAdaptorId());
			adaptor.queryOrder(instrument);
			log.info("{} :: Call queryOrder, adaptodId==[{}], account is default", getStrategyName(),
					event.getAdaptorId());
			adaptor.queryPositions(instrument);
			log.info("{} :: Call queryPositions, adaptodId==[{}], account is default", getStrategyName(),
					event.getAdaptorId());
			adaptor.queryBalance();
			log.info("{} :: Call queryBalance, adaptodId==[{}], account is default", getStrategyName(),
					event.getAdaptorId());
			break;
		default:
			log.warn("{} unhandled event received {}", getStrategyName(), event);
			break;
		}
	}

}
