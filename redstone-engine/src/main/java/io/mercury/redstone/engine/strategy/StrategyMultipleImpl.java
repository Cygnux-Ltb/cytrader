package io.mercury.redstone.engine.strategy;

import java.util.Set;

import org.eclipse.collections.api.list.ImmutableList;

import io.mercury.common.collections.ImmutableLists;
import io.mercury.common.param.map.ImmutableParamMap;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;
import io.mercury.redstone.core.adaptor.AdaptorEvent;
import io.mercury.redstone.core.strategy.StrategyParamKey;

public abstract class StrategyMultipleImpl<M extends MarketData, P extends StrategyParamKey>
		extends StrategyBaseImpl<M, P> {

	// 策略订阅的合约
	protected ImmutableList<Instrument> instruments;

	protected StrategyMultipleImpl(int strategyId, String strategyName, int subAccountId, Set<Instrument> instruments,
			ImmutableParamMap<P> paramMap) {
		super(strategyId, strategyName, subAccountId, paramMap);
		this.instruments = ImmutableLists.newList(instruments);
	}

	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		// TODO Auto-generated method stub
	}

}
