package io.mercury.indicator.impl;

import java.time.Duration;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

import io.gemini.definition.market.data.impl.BasicMarketData;
import io.gemini.definition.market.instrument.Instrument;
import io.gemini.definition.market.vector.TimePeriodPool;
import io.gemini.definition.market.vector.TimePeriodSerial;
import io.mercury.common.collections.list.FixedLengthRecorder;
import io.mercury.indicator.api.IndicatorEvent;
import io.mercury.indicator.impl.SmaIndicator.SmaEvent;
import io.mercury.indicator.impl.base.FixedPeriodIndicator;

public final class SmaIndicator extends FixedPeriodIndicator<SmaPoint, SmaEvent, BasicMarketData> {

	private FixedLengthRecorder historyPriceRecorder;

	public SmaIndicator(Instrument instrument, Duration duration, int cycle) {
		super(instrument, duration, cycle);

		this.historyPriceRecorder = FixedLengthRecorder.newRecorder(cycle);
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
