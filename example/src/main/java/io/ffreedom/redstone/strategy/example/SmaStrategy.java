package io.ffreedom.redstone.strategy.example;

import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.polaris.indicators.events.SmaEvent;
import io.ffreedom.polaris.indicators.impl.ma.point.SmaPoint;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.specific.strategy.IndicatorStrategy;

public class SmaStrategy extends IndicatorStrategy<BasicMarketData> implements SmaEvent {

	public SmaStrategy(int strategyId, Instrument instrument) {
		super(strategyId, instrument);
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
	protected void specificOnMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

}
