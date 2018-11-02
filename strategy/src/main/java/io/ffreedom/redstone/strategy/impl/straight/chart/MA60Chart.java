package io.ffreedom.redstone.strategy.impl.straight.chart;

import io.ffreedom.indicators.api.IndicatorPeriod;
import io.ffreedom.indicators.impl.ma.SMA;

public class MA60Chart	 {
	
	/**
	 * MA5上穿MA60做多,反之亦然
	 * MA5下穿MA10平多,反之亦然
	 */

	private SMA sma5 = new SMA(5);
	private SMA sma10 = new SMA(10);
	private SMA sma60 = new SMA(60);

	public MA60Chart(IndicatorPeriod period) {
		//super(period);

	}

	

}
