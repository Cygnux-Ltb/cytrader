package io.mercury.polaris.indicator.pools;

import javax.annotation.concurrent.NotThreadSafe;

import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.vector.TimePeriod;
import io.mercury.polaris.indicator.api.CalculationCycle;
import io.mercury.polaris.indicator.impl.ma.SmaIndicator;
import io.mercury.polaris.indicator.pools.base.MultiLayerIndicatorPool;

@NotThreadSafe
public final class SmaIndicatorPool extends MultiLayerIndicatorPool<SmaIndicator> {

	public static final SmaIndicatorPool Singleton = new SmaIndicatorPool();

	private SmaIndicatorPool() {
	}

	@Override
	protected SmaIndicator generateIndicator(TimePeriod period, CalculationCycle cycle, Instrument instrument) {
		return SmaIndicator.with(instrument, period, cycle);
	}

}
