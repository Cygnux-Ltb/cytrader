package io.mercury.redstone.engine.strategy;

import java.util.Set;

import org.eclipse.collections.api.list.ImmutableList;

import io.mercury.common.collections.ImmutableLists;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;
import io.mercury.redstone.core.adaptor.AdaptorEvent;

public abstract class StrategyMultipleImpl<M extends MarketData> extends StrategyBaseImpl<M> {

	// 策略订阅的合约
	protected ImmutableList<Instrument> instruments;

	protected StrategyMultipleImpl(int strategyId, String strategyName, int subAccountId, Set<Instrument> instruments) {
		super(strategyId, strategyName, subAccountId);
		this.instruments = ImmutableLists.newList(instruments);
	}

	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		// TODO Auto-generated method stub
	}

}
