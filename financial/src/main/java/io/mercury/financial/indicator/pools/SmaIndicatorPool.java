package io.mercury.financial.indicator.pools;

import javax.annotation.concurrent.NotThreadSafe;

import io.mercury.financial.indicator.pools.base.MultipleIndicatorPool;
import io.mercury.financial.indicator.specific.ma.Sma;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.vector.TimePeriod;

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
