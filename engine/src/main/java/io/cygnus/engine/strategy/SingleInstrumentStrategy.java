package io.cygnus.engine.strategy;

import static io.mercury.common.collections.ImmutableMaps.immutableIntObjectMapFactory;
import static io.mercury.common.log.CommonLoggerFactory.getLogger;

import javax.annotation.Nullable;

import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.slf4j.Logger;

import io.cygnus.engine.strategy.api.Strategy;
import io.cygnus.engine.strategy.api.StrategySign;
import io.horizon.market.data.MarketData;
import io.horizon.market.instrument.Instrument;
import io.horizon.transaction.account.SubAccount;
import io.horizon.transaction.adaptor.Adaptor;
import io.horizon.transaction.adaptor.AdaptorEvent;
import io.mercury.common.param.Params;
import io.mercury.common.param.Params.ParamKey;
import io.mercury.common.util.Assertor;
import lombok.Getter;

public abstract class SingleInstrumentStrategy<M extends MarketData, PK extends ParamKey>
		extends AbstractStrategy<M, PK> {

	// Logger
	private static final Logger log = getLogger(SingleInstrumentStrategy.class);

	// 策略订阅的合约
	protected Instrument instrument;

	// 策略订阅的合约列表
	@Getter
	protected ImmutableIntObjectMap<Instrument> instruments;

	private Adaptor adaptor;

	/**
	 * 
	 * @param strategyId
	 * @param subAccount
	 * @param instrument
	 */
	protected SingleInstrumentStrategy(StrategySign sign, SubAccount subAccount, Instrument instrument) {
		this(sign, subAccount, null, instrument);
	}

	/**
	 * 
	 * @param strategyId
	 * @param subAccount
	 * @param params
	 * @param instrument
	 */
	protected SingleInstrumentStrategy(StrategySign sign, SubAccount subAccount, @Nullable Params<PK> params,
			Instrument instrument) {
		super(sign, subAccount, params);
		this.instrument = instrument;
		this.instruments = immutableIntObjectMapFactory().of(instrument.getInstrumentId(), instrument);
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
	public void onAdaptorEvent(AdaptorEvent event) {
		log.info("{} :: On adaptor status callback, adaptorId==[{}], status==[{}]", getStrategyName(),
				event.getAdaptorId(), event.getStatus());
		switch (event.getStatus()) {
		case MdEnable:
			log.info("{} :: Handle adaptor MdEnable, adaptorId==[{}]", getStrategyName(), event.getAdaptorId());
			adaptor.subscribeMarketData(instrument);
			log.info("{} :: Call subscribeMarketData, instrument -> {}", getStrategyName(), instrument);
			break;
		case TraderEnable:
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
