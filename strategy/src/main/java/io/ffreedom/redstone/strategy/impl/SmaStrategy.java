package io.ffreedom.redstone.strategy.impl;

import io.ffreedom.indicators.impl.ma.SMA;
import io.ffreedom.market.BasicMarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.strategy.StrategyControlEvent;
import io.ffreedom.redstone.strategy.impl.base.BaseStrategy;

public class SmaStrategy extends BaseStrategy<BasicMarketData> {

	private SMA sma;

	public SmaStrategy(int period, int strategyId) {
		super(strategyId);
		sma = new SMA(period);
	}

	@Override
	public void onError(Throwable throwable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onControlEvent(StrategyControlEvent event) {
		// TODO Auto-generated method stub

	}

}
