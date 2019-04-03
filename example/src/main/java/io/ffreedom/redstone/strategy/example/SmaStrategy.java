package io.ffreedom.redstone.strategy.example;

import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.polaris.indicators.impl.ma.point.SmaPoint;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.specific.strategy.IndicatorStrategy;

public class SmaStrategy extends IndicatorStrategy<BasicMarketData, SmaPoint> {

	public SmaStrategy(int strategyId, Instrument instrument) {
		super(strategyId, instrument);
	}

	@Override
	public void onCurrentPointChanged(SmaPoint p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartPoint(SmaPoint p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEndPoint(SmaPoint p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub
	}

}
