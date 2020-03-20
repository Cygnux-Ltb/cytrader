package io.mercury.indicator.impl.ma;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

import io.mercury.indicator.api.CalculationCycle;
import io.mercury.indicator.base.BaseTimePeriodIndicator;
import io.mercury.indicator.event.SmaEvent;
import io.mercury.indicator.structure.FixedHistoryPriceRecorder;
import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.mercury.polaris.financial.time.TimePeriodPool;
import io.mercury.polaris.financial.vector.TimePeriod;
import io.mercury.polaris.financial.vector.TimePeriodSerial;

public final class SmaIndicator extends BaseTimePeriodIndicator<SmaPoint, SmaEvent> {

	private FixedHistoryPriceRecorder historyPriceRecorder;

	public SmaIndicator(Instrument instrument, TimePeriod period, CalculationCycle cycle) {
		super(instrument, period, cycle);

		this.historyPriceRecorder = FixedHistoryPriceRecorder.newRecorder(cycle);
		ImmutableSortedSet<TimePeriodSerial> timePeriodSet = TimePeriodPool.Singleton.getTimePeriodSet(instrument, period);
		int i = -1;
		for (TimePeriodSerial timePeriod : timePeriodSet)
			pointSet.add(SmaPoint.with(++i, instrument, period, timePeriod, cycle, historyPriceRecorder));
		currentPoint = pointSet.getFirst();

	}

	public static SmaIndicator with(Instrument instrument, TimePeriod period, CalculationCycle cycle) {
		return new SmaIndicator(instrument, period, cycle);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

}
