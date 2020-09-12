package io.mercury.redstone.strategy;

import io.mercury.common.param.ImmutableParams;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.indicator.impl.SmaIndicator.SmaEvent;
import io.mercury.indicator.impl.SmaPoint;
import io.mercury.redstone.core.order.Order;
import io.mercury.redstone.core.strategy.StrategyParamKey;
import io.mercury.redstone.engine.strategy.StrategySingleInstrument;

public final class SmaStrategyExample extends StrategySingleInstrument<BasicMarketData, StrategyParamKey>
		implements SmaEvent {

	public SmaStrategyExample(int strategyId, int subAccountId, Instrument instrument,
			ImmutableParams<StrategyParamKey> params) {
		super(strategyId, subAccountId, instrument, params);
	}

	@Override
	public String strategyName() {
		return "SmaStrategyExample";
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
