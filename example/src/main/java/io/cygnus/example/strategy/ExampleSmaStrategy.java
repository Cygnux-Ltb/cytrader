package io.cygnus.example.strategy;

import java.io.IOException;

import io.cygnus.engine.strategy.SingleInstrumentStrategy;
import io.cygnus.engine.strategy.api.StrategySign;
import io.cygnus.indicator.impl.SmaIndicator.SmaEvent;
import io.cygnus.indicator.impl.SmaPoint;
import io.horizon.market.data.impl.BasicMarketData;
import io.horizon.market.instrument.Instrument;
import io.horizon.trader.account.SubAccount;
import io.horizon.trader.order.Order;
import io.mercury.common.param.Params;
import io.mercury.common.param.Params.ParamKey;

/**
 * 
 * @author yellow013
 *
 */
public final class ExampleSmaStrategy extends SingleInstrumentStrategy<BasicMarketData, ParamKey> implements SmaEvent {

	public ExampleSmaStrategy(SubAccount subAccount, Params<ParamKey> params, Instrument instrument) {
		super(new StrategySign() {

			@Override
			public int getStrategyId() {
				return 100;
			}

			@Override
			public String getStrategyName() {
				return "ExampleSmaStrategy";
			}

		}, subAccount, params, instrument);
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

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

}
