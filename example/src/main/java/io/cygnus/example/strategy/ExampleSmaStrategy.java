package io.cygnus.example.strategy;

import io.cygnus.engine.strategy.StrategySingleInstrument;
import io.horizon.definition.market.data.impl.BasicMarketData;
import io.horizon.definition.market.instrument.Instrument;
import io.horizon.definition.order.Order;
import io.horizon.indicator.impl.SmaPoint;
import io.horizon.indicator.impl.SmaIndicator.SmaEvent;
import io.mercury.common.param.ImmutableParams;
import io.mercury.common.param.ParamKey;

/**
 * 
 * @author yellow013
 *
 */
public final class ExampleSmaStrategy extends StrategySingleInstrument<BasicMarketData, ParamKey>
		implements SmaEvent {

	public ExampleSmaStrategy(int strategyId, int subAccountId, Instrument instrument,
			ImmutableParams<ParamKey> params) {
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
