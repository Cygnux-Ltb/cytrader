package io.mercury.financial.indicator.pools;

import javax.annotation.concurrent.NotThreadSafe;

import io.mercury.financial.indicator.pools.base.SingleIndicatorPool;
import io.mercury.financial.indicator.specific.bar.TimeBarIndicator;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriod;

@NotThreadSafe
public final class TimeBarIndicatorPool extends SingleIndicatorPool<TimeBarIndicator, BasicMarketData> {

	public static final TimeBarIndicatorPool Singleton = new TimeBarIndicatorPool();

	private TimeBarIndicatorPool() {
	}

	@Override
	protected TimeBarIndicator generateIndicator(TimePeriod period, Instrument instrument) {
		return new TimeBarIndicator(instrument, period);
	}

}
