package io.mercury.redstone.engine.strategy;

import org.eclipse.collections.api.list.ImmutableList;
import org.slf4j.Logger;

import io.mercury.common.collections.ImmutableLists;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.param.ImmutableParams;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;
import io.mercury.redstone.core.adaptor.Adaptor;
import io.mercury.redstone.core.adaptor.AdaptorEvent;
import io.mercury.redstone.core.strategy.StrategyParamKey;

public abstract class StrategySingleInstrument<M extends MarketData, PK extends StrategyParamKey>
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

	protected StrategySingleInstrument(int strategyId, int subAccountId, Instrument instrument,
			ImmutableParams<PK> params) {
		super(strategyId, subAccountId, params);
		this.instrument = instrument;
		this.instruments = ImmutableLists.newImmutableList(instrument);
	}

	@Override
	public ImmutableList<Instrument> instruments() {
		return instruments;
	}

	private Adaptor<M> adaptor;

	@Override
	public void addAdaptor(Adaptor<M> adaptor) {
		if (this.adaptor != null)
			throw new IllegalStateException("adaptor is not null");
		this.adaptor = adaptor;
	}

	@Override
	protected Adaptor<M> getAdaptor(Instrument instrument) {
		return adaptor;
	}

	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		log.info("{} :: On adaptor status callback, adaptorId==[{}], status==[{}]", strategyName(), event.adaptorId(),
				event.status());
		switch (event.status()) {
		case MdEnable:
			log.info("{} :: Handle adaptor MdEnable, adaptorId==[{}]", strategyName(), event.adaptorId());
			adaptor.subscribeMarketData(instrument);
			log.info("{} :: Call subscribeMarketData, instrument -> {}", strategyName(), instrument);
			break;
		case TraderEnable:
			log.info("{} :: Handle adaptor TdEnable, adaptorId==[{}]", strategyName(), event.adaptorId());
			adaptor.queryOrder(instrument);
			log.info("{} :: Call queryOrder, adaptodId==[{}], account is default", strategyName(), event.adaptorId());
			adaptor.queryPositions(instrument);
			log.info("{} :: Call queryPositions, adaptodId==[{}], account is default", strategyName(),
					event.adaptorId());
			adaptor.queryBalance();
			log.info("{} :: Call queryBalance, adaptodId==[{}], account is default", strategyName(), event.adaptorId());
			break;
		default:
			break;
		}
	}

}
