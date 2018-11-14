package io.ffreedom.redstone.strategy.impl;

import io.ffreedom.common.functional.Initializer;
import io.ffreedom.market.BasicMarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.strategy.StrategyControlEvent;
import io.ffreedom.redstone.strategy.impl.base.BaseStrategy;

public class BarStrategy extends BaseStrategy<BasicMarketData> {

	public BarStrategy(int strategyId) {
		super(strategyId);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public void onControlEvent(StrategyControlEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(Throwable throwable) {
		// TODO Auto-generated method stub

	}

}
