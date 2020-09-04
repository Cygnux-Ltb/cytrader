package io.mercury.indicator.pools;

import javax.annotation.concurrent.NotThreadSafe;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.indicator.impl.ma.SmaIndicator;
import io.mercury.indicator.pools.base.MultipleIndicatorPool;

@NotThreadSafe
public final class SmaIndicatorPool extends MultipleIndicatorPool<SmaIndicator, BasicMarketData> {

	public static final SmaIndicatorPool Singleton = new SmaIndicatorPool();

	private SmaIndicatorPool() {
	}

	@Override
	protected SmaIndicator newIndicator(TimePeriod period, int cycle, Instrument instrument) {
		return SmaIndicator.with(instrument, period, cycle);
	}

}
