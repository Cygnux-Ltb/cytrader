package io.cygnus.indicator.impl;

import java.time.Duration;

import io.cygnus.indicator.IndicatorEvent;
import io.cygnus.indicator.impl.BollingerBands.BollingerBandsEvent;
import io.cygnus.indicator.impl.BollingerBands.BollingerBandsPoint;
import io.cygnus.indicator.impl.base.FixedPeriodIndicator;
import io.cygnus.indicator.impl.base.FixedPeriodIndicator.FixedPeriodPoint;
import io.horizon.market.data.impl.BasicMarketData;
import io.horizon.market.instrument.Instrument;
import io.mercury.common.sequence.TimeWindow;

public final class BollingerBands
		extends FixedPeriodIndicator<BollingerBandsPoint, BollingerBandsEvent, BasicMarketData> {

	public BollingerBands(Instrument instrument, Duration duration, int cycle) {
		super(instrument, duration, cycle);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {

	}

	/**
	 * 
	 * @author yellow013
	 *
	 */
	public static interface BollingerBandsEvent extends IndicatorEvent {

		@Override
		default String getEventName() {
			return "BollingerBandsEvent";
		}

	}

	public final class BollingerBandsPoint extends FixedPeriodPoint<BasicMarketData> {

		private BollingerBandsPoint(int index, TimeWindow timePeriod) {
			super(index, timePeriod);
		}

		@Override
		protected void handleMarketData0(BasicMarketData marketData) {

		}

	}

}
