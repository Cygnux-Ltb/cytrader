package io.cygnus.indicator.impl;

import java.time.Duration;

import io.cygnus.indicator.IndicatorEvent;
import io.cygnus.indicator.impl.BollingerBandsIndicator.BollingerBandsEvent;
import io.cygnus.indicator.impl.BollingerBandsIndicator.BollingerBandsPoint;
import io.cygnus.indicator.impl.base.FixedPeriodIndicator;
import io.cygnus.indicator.impl.base.FixedPeriodPoint;
import io.horizon.market.data.impl.BasicMarketData;
import io.horizon.market.instrument.Instrument;
import io.horizon.market.serial.TimePeriodSerial;

public final class BollingerBandsIndicator
		extends FixedPeriodIndicator<BollingerBandsPoint, BollingerBandsEvent, BasicMarketData> {

	public BollingerBandsIndicator(Instrument instrument, Duration duration, int cycle) {
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

		private BollingerBandsPoint(int index, TimePeriodSerial timePeriod) {
			super(index, timePeriod);
		}

		@Override
		protected void handleMarketData0(BasicMarketData marketData) {

		}

	}

}
