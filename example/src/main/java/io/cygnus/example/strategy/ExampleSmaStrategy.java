package io.cygnus.example.strategy;

import io.cygnus.engine.strategy.impl.StrategySingleInstrument;
import io.cygnus.indicator.impl.SmaIndicator.SmaEvent;
import io.cygnus.indicator.impl.SmaPoint;
import io.horizon.structure.market.data.impl.BasicMarketData;
import io.horizon.structure.market.instrument.Instrument;
import io.horizon.structure.order.Order;
import io.mercury.common.param.Params;
import io.mercury.common.param.Params.ParamKey;

/**
 * 
 * @author yellow013
 *
 */
public final class ExampleSmaStrategy extends StrategySingleInstrument<BasicMarketData, ParamKey> implements SmaEvent {

	public ExampleSmaStrategy(int strategyId, int subAccountId, Params<ParamKey> params, Instrument instrument) {
		super(strategyId, subAccountId, instrument, params);
	}

	@Override
	public String getStrategyName() {
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

	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return null;
	}

}
