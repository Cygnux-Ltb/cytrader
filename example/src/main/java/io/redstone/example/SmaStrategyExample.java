package io.redstone.example;

import io.polaris.financial.Instrument;
import io.polaris.indicators.events.SmaEvent;
import io.polaris.indicators.impl.ma.SmaPoint;
import io.polaris.market.impl.BasicMarketData;
import io.redstone.core.adaptor.impl.OutboundAdaptor;
import io.redstone.specific.strategy.IndicatorStrategy;

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
	public String getStrategyName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return null;
	}

}
