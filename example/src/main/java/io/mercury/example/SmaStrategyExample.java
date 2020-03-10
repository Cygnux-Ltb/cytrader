package io.redstone.example;

import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.mercury.polaris.indicator.events.SmaEvent;
import io.mercury.polaris.indicator.impl.ma.SmaPoint;
import io.redstone.core.adaptor.impl.OutboundAdaptor;
import io.redstone.engine.specific.strategy.IndicatorStrategy;

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
	protected OutboundAdaptor getOutboundAdaptor(Instrument instrument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String strategyName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String eventName() {
		// TODO Auto-generated method stub
		return null;
	}

}
