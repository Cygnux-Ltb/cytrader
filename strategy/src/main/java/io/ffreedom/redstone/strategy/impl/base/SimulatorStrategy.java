package io.ffreedom.redstone.strategy.impl.base;

import io.ffreedom.market.BasicMarketData;
import io.ffreedom.redstone.core.strategy.StrategyControlEvent;

public class SimulatorStrategy extends BaseStrategy<BasicMarketData> {

	public SimulatorStrategy() {
		super(777);
	}


	@Override
	public void onControlEvent(StrategyControlEvent event) {
		
	}

	@Override
	public void onMarketData(BasicMarketData marketData) {
		
		
	}

	@Override
	public void onError(Throwable throwable) {
		// TODO Auto-generated method stub
		
	}

	

	

}
