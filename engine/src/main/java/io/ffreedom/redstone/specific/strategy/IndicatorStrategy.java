package io.ffreedom.redstone.specific.strategy;

import io.ffreedom.polaris.indicators.api.IndicatorEvent;
import io.ffreedom.polaris.indicators.api.Point;
import io.ffreedom.polaris.market.BasicMarketData;

public abstract class IndicatorStrategy<M extends BasicMarketData, P extends Point<?, ?>> extends BaseStrategy<M>
		implements IndicatorEvent<P> {

	protected IndicatorStrategy(int strategyId) {
		super(strategyId);
	}

	@Override
	public String getEventName() {
		return getClass().getSimpleName();
	}

}
