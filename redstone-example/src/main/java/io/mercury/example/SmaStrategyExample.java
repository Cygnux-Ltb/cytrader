package io.mercury.example;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.indicator.event.SmaEvent;
import io.mercury.indicator.impl.ma.SmaPoint;
import io.redstone.core.adaptor.Adaptor;
import io.redstone.core.adaptor.AdaptorEvent;
import io.redstone.engine.impl.strategy.IndicatorStrategy;

public class SmaStrategyExample extends IndicatorStrategy<BasicMarketData> implements SmaEvent {

	public SmaStrategyExample(int strategyId, int subAccountId, Instrument instrument) {
		super(strategyId, subAccountId, instrument);
	}

	@Override
	public void onMarketData(BasicMarketData marketData) {
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
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Adaptor getAdaptor(Instrument instrument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String eventName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onAdaptorEvent(AdaptorEvent event) {
		// TODO Auto-generated method stub

	}

}
