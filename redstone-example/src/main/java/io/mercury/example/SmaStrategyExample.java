package io.mercury.example;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.indicator.event.SmaEvent;
import io.mercury.indicator.impl.ma.SmaPoint;
import io.redstone.core.order.Order;
import io.redstone.engine.impl.strategy.StrategyBaseImpl;

public class SmaStrategyExample extends StrategyBaseImpl<BasicMarketData> implements SmaEvent {

	public SmaStrategyExample(int strategyId, int subAccountId, Instrument instrument) {
		super(strategyId, subAccountId, instrument);
	}

	@Override
	protected void marketDataExtendHandle(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void orderExtendHandle(Order order) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onCurrentPointAvgPriceChanged(SmaPoint point) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartSmaPoint(SmaPoint point) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEndSmaPoint(SmaPoint point) {
		// TODO Auto-generated method stub

	}

	@Override
	public String eventName() {
		// TODO Auto-generated method stub
		return null;
	}

}
