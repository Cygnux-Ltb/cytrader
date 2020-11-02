package io.apollo.example.strategy;

import io.apollo.engine.strategy.StrategySingleInstrument;
import io.apollo.indicator.impl.SmaPoint;
import io.apollo.indicator.impl.SmaIndicator.SmaEvent;
import io.gemini.definition.market.data.impl.BasicMarketData;
import io.gemini.definition.market.instrument.Instrument;
import io.gemini.definition.order.Order;
import io.gemini.definition.strategy.StrategyParamKey;
import io.mercury.common.param.ImmutableParams;

/**
 * 
 * @author yellow013
 *
 */
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
