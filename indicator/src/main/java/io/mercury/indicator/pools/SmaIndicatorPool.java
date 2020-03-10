package io.mercury.indicator.pools;

import javax.annotation.concurrent.NotThreadSafe;

import io.mercury.indicator.api.CalculationCycle;
import io.mercury.indicator.impl.ma.SmaIndicator;
import io.mercury.indicator.pools.base.MultiLayerIndicatorPool;
import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.vector.TimePeriod;

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
