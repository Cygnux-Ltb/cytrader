package io.ffreedom.redstone.strategy.impl;

import io.ffreedom.indicators.impl.sar.SAR;
import io.ffreedom.market.BasicMarketData;
import io.ffreedom.redstone.core.strategy.StrategyControlEvent;
import io.ffreedom.redstone.strategy.impl.base.BaseStrategy;

/**
 * 
 * @author yellow013
 * @version 0.1
 * 
 *          Index SAR Strategy
 *
 */
public class SarStrategy extends BaseStrategy<BasicMarketData> {

	private SAR sar = new SAR();

	public SarStrategy(int strategyId) {
		super(strategyId);
	}

	@Override
	public void onControlEvent(StrategyControlEvent event) {
			
	}

	@Override
	public void onMarketData(BasicMarketData marketData) {

	}

}
