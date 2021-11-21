package io.cygnus.indicator.impl;

import java.time.Duration;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

import io.cygnus.indicator.IndicatorEvent;
import io.cygnus.indicator.impl.SMA.SmaEvent;
import io.cygnus.indicator.impl.base.FixedPeriodIndicator;
import io.horizon.market.data.impl.BasicMarketData;
import io.horizon.market.instrument.Instrument;
import io.horizon.market.pool.TimePeriodPool;
import io.mercury.common.collections.list.LongSlidingWindow;
import io.mercury.common.sequence.TimeWindow;

public final class SMA extends FixedPeriodIndicator<SmaPoint, SmaEvent, BasicMarketData> {

	private LongSlidingWindow historyPriceWindow;

	public SMA(Instrument instrument, Duration duration, int cycle) {
		super(instrument, duration, cycle);
		this.historyPriceWindow = new LongSlidingWindow(cycle);
		ImmutableSortedSet<TimeWindow> timePeriodSet = TimePeriodPool.Singleton.getTimePeriodSet(instrument,
				duration);
		int i = -1;
		for (TimeWindow timePeriod : timePeriodSet)
			pointSet.add(SmaPoint.with(++i, instrument, duration, timePeriod, cycle, historyPriceWindow));
		currentPoint = pointSet.getFirst();

	}

	public static SMA with(Instrument instrument, Duration duration, int cycle) {
		return new SMA(instrument, duration, cycle);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {

	}

	public static interface SmaEvent extends IndicatorEvent {

		default String eventName() {
			return "SmaEvent";
		}

		void onCurrentPointAvgPriceChanged(SmaPoint point);

		void onStartSmaPoint(SmaPoint point);

		void onEndSmaPoint(SmaPoint point);

	}

}
