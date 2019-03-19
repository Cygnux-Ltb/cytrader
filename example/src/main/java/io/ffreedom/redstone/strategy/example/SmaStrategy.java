package io.ffreedom.redstone.strategy.example;

import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.polaris.indicators.impl.ma.base.MAPoint;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.specific.strategy.IndicatorStrategy;

public class SmaStrategy extends IndicatorStrategy<BasicMarketData, MAPoint> {

	public SmaStrategy(int strategyId, Instrument instrument) {
		super(strategyId, instrument);
	}

	@Override
	public void onCurrentPointChanged(MAPoint p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartPoint(MAPoint p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEndPoint(MAPoint p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub
	}

}
