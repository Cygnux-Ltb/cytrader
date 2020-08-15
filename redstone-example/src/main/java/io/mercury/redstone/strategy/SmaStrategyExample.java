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
		return null;
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		
	}

	@Override
	protected void handleOrder(Order order) {
		
	}

	@Override
	public void onCurrentPointAvgPriceChanged(SmaPoint point) {
		
	}

	@Override
	public void onStartSmaPoint(SmaPoint point) {

	}

	@Override
	public void onEndSmaPoint(SmaPoint point) {

	}

}
