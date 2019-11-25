package io.redstone.engine.specific.strategy;

import io.polaris.financial.instrument.Instrument;
import io.polaris.financial.market.api.MarketData;

public abstract class IndicatorStrategy<M extends MarketData> extends BaseStrategy<M> {

	protected IndicatorStrategy(int strategyId, int subAccountId, Instrument instrument) {
		super(strategyId, subAccountId, instrument);
	}

}
