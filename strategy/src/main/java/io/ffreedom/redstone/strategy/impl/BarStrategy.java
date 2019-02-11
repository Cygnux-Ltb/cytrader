package io.ffreedom.redstone.strategy.impl;

import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.core.strategy.StrategyControlEvent;
import io.ffreedom.redstone.strategy.impl.base.BaseStrategy;

public class BarStrategy extends BaseStrategy<BasicMarketData> {

	public BarStrategy(int strategyId) {
		super(strategyId);
	}

	@Override
	public void onControlEvent(StrategyControlEvent event) {

	}

	@Override
	public void onMarketData(BasicMarketData marketData) {

	}

	@Override
	public void onError(Throwable throwable) {

	}

}
