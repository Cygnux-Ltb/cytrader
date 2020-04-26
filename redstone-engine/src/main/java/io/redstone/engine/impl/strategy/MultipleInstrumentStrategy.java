package io.redstone.engine.impl.strategy;

import java.util.Collection;

import org.eclipse.collections.api.list.ImmutableList;

import io.mercury.common.collections.ImmutableLists;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;

public abstract class MultipleInstrumentStrategy<M extends MarketData> extends StrategyBaseImpl<M> {

	// 策略订阅的合约
	protected ImmutableList<Instrument> instruments;

	protected MultipleInstrumentStrategy(int strategyId, String strategyName, int subAccountId,
			Collection<Instrument> instruments) {
		super(strategyId, strategyName, subAccountId);
		this.instruments = ImmutableLists.newList(instruments);
	}

}
