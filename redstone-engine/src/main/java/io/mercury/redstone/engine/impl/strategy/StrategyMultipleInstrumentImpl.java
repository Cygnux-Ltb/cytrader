package io.mercury.redstone.engine.impl.strategy;

import java.util.Collection;

import org.eclipse.collections.api.list.ImmutableList;

import io.mercury.common.collections.ImmutableLists;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;
import io.mercury.redstone.core.adaptor.AdaptorEvent;

public abstract class StrategyMultipleInstrumentImpl<M extends MarketData> extends StrategyBaseImpl<M> {

	// 策略订阅的合约
	protected ImmutableList<Instrument> instruments;

	protected StrategyMultipleInstrumentImpl(int strategyId, String strategyName, int subAccountId,
			Collection<Instrument> instruments) {
		super(strategyId, strategyName, subAccountId);
		this.instruments = ImmutableLists.newList(instruments);
	}
	
	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		// TODO Auto-generated method stub
		
	}

}
