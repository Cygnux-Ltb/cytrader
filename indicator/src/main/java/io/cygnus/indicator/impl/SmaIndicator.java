package io.cygnus.indicator.impl;

import java.time.Duration;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

import io.cygnus.indicator.IndicatorEvent;
import io.cygnus.indicator.impl.SmaIndicator.SmaEvent;
import io.cygnus.indicator.impl.base.FixedPeriodIndicator;
import io.horizon.structure.market.data.impl.BasicMarketData;
import io.horizon.structure.market.instrument.Instrument;
import io.horizon.structure.pool.TimePeriodPool;
import io.horizon.structure.serial.TimePeriodSerial;
import io.mercury.common.collections.list.LongFixedLengthList;

public final class SmaIndicator extends FixedPeriodIndicator<SmaPoint, SmaEvent, BasicMarketData> {

	private LongFixedLengthList historyPriceRecorder;

	public SmaIndicator(Instrument instrument, Duration duration, int cycle) {
		super(instrument, duration, cycle);
		this.historyPriceRecorder = LongFixedLengthList.newList(cycle);
		ImmutableSortedSet<TimePeriodSerial> timePeriodSet = TimePeriodPool.Singleton.getTimePeriodSet(instrument,
				duration);
		int i = -1;
		for (TimePeriodSerial timePeriod : timePeriodSet)
			pointSet.add(SmaPoint.with(++i, instrument, duration, timePeriod, cycle, historyPriceRecorder));
		currentPoint = pointSet.getFirst();

	}

	public static SmaIndicator with(Instrument instrument, Duration duration, int cycle) {
		return new SmaIndicator(instrument, duration, cycle);
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
