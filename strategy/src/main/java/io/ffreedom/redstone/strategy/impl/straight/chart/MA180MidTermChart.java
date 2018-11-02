package io.ffreedom.redstone.strategy.impl.straight.chart;

import io.ffreedom.indicators.api.IndicatorPeriod;
import io.ffreedom.indicators.impl.ma.SMA;

public class MA180MidTermChart {

	private SMA sma5 = new SMA(5);
	private SMA sma10 = new SMA(10);
	private SMA sma20 = new SMA(20);
	private SMA sma180 = new SMA(180);

	public MA180MidTermChart(IndicatorPeriod period) {
		
	}

	// @Override
	// public void onBar(Bar bar) {
	//
	// }

}
