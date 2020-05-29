package io.mercury.indicator.pools;

import javax.annotation.concurrent.NotThreadSafe;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.indicator.impl.ma.Sma;
import io.mercury.indicator.pools.base.MultipleIndicatorPool;

@NotThreadSafe
public final class SmaIndicatorPool extends MultipleIndicatorPool<Sma> {

	public static final SmaIndicatorPool Singleton = new SmaIndicatorPool();

	private SmaIndicatorPool() {
	}

	@Override
	protected Sma generateIndicator(TimePeriod period, int cycle, Instrument instrument) {
		return Sma.with(instrument, period, cycle);
	}

}
