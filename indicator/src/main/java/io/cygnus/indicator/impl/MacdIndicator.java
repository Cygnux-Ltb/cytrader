package io.cygnus.indicator.impl;

import java.time.Duration;

import io.cygnus.indicator.IndicatorEvent;
import io.cygnus.indicator.impl.MacdIndicator.MacdEvent;
import io.cygnus.indicator.impl.MacdIndicator.MacdPoint;
import io.cygnus.indicator.impl.base.FixedPeriodIndicator;
import io.cygnus.indicator.impl.base.FixedPeriodPoint;
import io.horizon.structure.market.data.impl.BasicMarketData;
import io.horizon.structure.market.instrument.Instrument;
import io.horizon.structure.serial.TimePeriodSerial;

public final class MacdIndicator extends FixedPeriodIndicator<MacdPoint, MacdEvent, BasicMarketData> {

	public MacdIndicator(Instrument instrument, Duration duration) {
		super(instrument, duration);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub
	}

	public static interface MacdEvent extends IndicatorEvent {

		@Override
		default String getEventName() {
			return "MacdEvent";
		}

	}

	public final class MacdPoint extends FixedPeriodPoint<BasicMarketData> {

		private MacdPoint(int index, TimePeriodSerial timePeriod) {
			super(index, timePeriod);
		}

		@Override
		protected void handleMarketData0(BasicMarketData marketData) {
			// TODO Auto-generated method stub
		}

	}

}
