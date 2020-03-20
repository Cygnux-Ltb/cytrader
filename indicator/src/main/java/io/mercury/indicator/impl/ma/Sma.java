package io.mercury.indicator.impl.ma;

import org.eclipse.collections.api.set.sorted.ImmutableSortedSet;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.time.TimePeriodPool;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.financial.vector.TimePeriodSerial;
import io.mercury.indicator.base.TimePeriodIndicator;
import io.mercury.indicator.event.SmaEvent;
import io.mercury.indicator.structure.FixedHistoryPriceRecorder;

public final class Sma extends TimePeriodIndicator<SmaPoint, SmaEvent> {

	private FixedHistoryPriceRecorder historyPriceRecorder;

	public Sma(Instrument instrument, TimePeriod period, int cycle) {
		super(instrument, period, cycle);

		this.historyPriceRecorder = FixedHistoryPriceRecorder.newRecorder(cycle);
		ImmutableSortedSet<TimePeriodSerial> timePeriodSet = TimePeriodPool.Singleton.getTimePeriodSet(instrument, period);
		int i = -1;
		for (TimePeriodSerial timePeriod : timePeriodSet)
			pointSet.add(SmaPoint.with(++i, instrument, period, timePeriod, cycle, historyPriceRecorder));
		currentPoint = pointSet.getFirst();

	}

	public static Sma with(Instrument instrument, TimePeriod period, int cycle) {
		return new Sma(instrument, period, cycle);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}

}
