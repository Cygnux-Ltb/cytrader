package io.cygnus.engine.strategy.impl;

import static io.mercury.common.collections.ImmutableMaps.immutableIntObjectMapFactory;
import static io.mercury.common.log.CommonLoggerFactory.getLogger;

import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;
import org.slf4j.Logger;

import io.horizon.structure.adaptor.Adaptor;
import io.horizon.structure.adaptor.AdaptorEvent;
import io.horizon.structure.market.data.MarketData;
import io.horizon.structure.market.instrument.Instrument;
import io.mercury.common.param.Params;
import io.mercury.common.param.Params.ParamKey;
import io.mercury.common.util.Assertor;

public abstract class StrategySingleInstrument<M extends MarketData, PK extends ParamKey>
		extends AbstractStrategy<M, PK> {

	// Logger
	private static final Logger log = getLogger(StrategySingleInstrument.class);

	// 策略订阅的合约
	protected Instrument instrument;

	// 策略订阅的合约列表
	protected ImmutableIntObjectMap<Instrument> instruments;

	protected StrategySingleInstrument(int strategyId, int subAccountId, Instrument instrument, Params<PK> params) {
		super(strategyId, subAccountId, params);
		this.instrument = instrument;
		this.instruments = immutableIntObjectMapFactory().of(instrument.getInstrumentId(), instrument);
	}

	@Override
	public ImmutableIntObjectMap<Instrument> getInstruments() {
		return instruments;
	}

	private Adaptor adaptor;

	@Override
	public void addAdaptor(Adaptor adaptor) {
		Assertor.nonNull(adaptor, "adaptor");
		this.adaptor = adaptor;
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
