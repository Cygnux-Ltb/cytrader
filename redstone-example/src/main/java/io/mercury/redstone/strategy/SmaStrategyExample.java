package io.mercury.redstone.strategy;

import io.mercury.financial.indicator.impl.ma.SmaEvent;
import io.mercury.financial.indicator.impl.ma.SmaPoint;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.redstone.core.order.Order;
import io.mercury.redstone.core.strategy.StrategyParamKey;
import io.mercury.redstone.engine.strategy.StrategySingleInstrumentImpl;

public final class SmaStrategyExample extends StrategySingleInstrumentImpl<BasicMarketData, StrategyParamKey>
		implements SmaEvent {

	public SmaStrategyExample(int strategyId, int subAccountId, Instrument instrument) {
		super(strategyId, "SmaStrategyExample", subAccountId, instrument, null);
	}

	@Override
	public String eventName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void handleOrder(Order order) {
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

}
