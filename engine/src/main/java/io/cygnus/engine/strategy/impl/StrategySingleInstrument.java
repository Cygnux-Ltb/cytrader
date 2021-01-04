package io.cygnus.engine.strategy.impl;

import org.eclipse.collections.api.list.ImmutableList;
import org.slf4j.Logger;

import io.horizon.structure.adaptor.Adaptor;
import io.horizon.structure.adaptor.AdaptorEvent;
import io.horizon.structure.market.data.MarketData;
import io.horizon.structure.market.instrument.Instrument;
import io.mercury.common.collections.ImmutableLists;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.Params;

public abstract class StrategySingleInstrument<M extends MarketData, PK extends ParamKey>
		extends StrategyBaseImpl<M, PK> {

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(StrategySingleInstrument.class);

	/**
	 * 策略订阅的合约
	 */
	protected Instrument instrument;

	/**
	 * 策略订阅的合约列表
	 */
	protected ImmutableList<Instrument> instruments;

	protected StrategySingleInstrument(int strategyId, int subAccountId, Instrument instrument, Params<PK> params) {
		super(strategyId, subAccountId, params);
		this.instrument = instrument;
		this.instruments = ImmutableLists.newImmutableList(instrument);
	}

	@Override
	public ImmutableList<Instrument> instruments() {
		return instruments;
	}

	private Adaptor adaptor;

	@Override
	public void addAdaptor(Adaptor adaptor) {
		if (this.adaptor != null)
			throw new IllegalStateException("adaptor is not null");
		this.adaptor = adaptor;
	}

	@Override
	protected Adaptor getAdaptor(Instrument instrument) {
		return adaptor;
	}

	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		log.info("{} :: On adaptor status callback, adaptorId==[{}], status==[{}]", strategyName(), event.getAdaptorId(),
				event.getStatus());
		switch (event.getStatus()) {
		case MdEnable:
			log.info("{} :: Handle adaptor MdEnable, adaptorId==[{}]", strategyName(), event.getAdaptorId());
			adaptor.subscribeMarketData(instrument);
			log.info("{} :: Call subscribeMarketData, instrument -> {}", strategyName(), instrument);
			break;
		case TraderEnable:
			log.info("{} :: Handle adaptor TdEnable, adaptorId==[{}]", strategyName(), event.getAdaptorId());
			adaptor.queryOrder(instrument);
			log.info("{} :: Call queryOrder, adaptodId==[{}], account is default", strategyName(), event.getAdaptorId());
			adaptor.queryPositions(instrument);
			log.info("{} :: Call queryPositions, adaptodId==[{}], account is default", strategyName(),
					event.getAdaptorId());
			adaptor.queryBalance();
			log.info("{} :: Call queryBalance, adaptodId==[{}], account is default", strategyName(), event.getAdaptorId());
			break;
		default:
			log.warn("{} unhandled event received {}", strategyName(), event);
			break;
		}
	}

}
