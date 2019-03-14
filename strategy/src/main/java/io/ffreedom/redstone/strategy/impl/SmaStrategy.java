package io.ffreedom.redstone.strategy.impl;

import io.ffreedom.polaris.indicators.impl.ma.base.MAPoint;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.core.strategy.StrategyControlEvent;
import io.ffreedom.redstone.core.strategy.callback.IndicatorCallback;
import io.ffreedom.redstone.strategy.impl.base.BaseStrategy;

public class SmaStrategy extends BaseStrategy<BasicMarketData> implements IndicatorCallback<MAPoint> {

	public SmaStrategy(int period, int strategyId) {
		super(strategyId);
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

	@Override
	public void onStartPoint(MAPoint p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEndPoint(MAPoint p) {
		// TODO Auto-generated method stub

	}

}
