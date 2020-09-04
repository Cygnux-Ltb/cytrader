package io.mercury.indicator.pools;

import javax.annotation.concurrent.NotThreadSafe;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.indicator.impl.bar.TimeBarIndicator;
import io.mercury.indicator.pools.base.SingleIndicatorPool;

@NotThreadSafe
public final class TimeBarIndicatorPool extends SingleIndicatorPool<TimeBarIndicator, BasicMarketData> {

	public static final TimeBarIndicatorPool Singleton = new TimeBarIndicatorPool();

	private TimeBarIndicatorPool() {
	}

	@Override
	protected TimeBarIndicator newIndicator(TimePeriod period, Instrument instrument) {
		return new TimeBarIndicator(instrument, period);
	}

}
