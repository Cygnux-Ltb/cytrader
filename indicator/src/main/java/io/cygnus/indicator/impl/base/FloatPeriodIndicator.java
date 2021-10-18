package io.cygnus.indicator.impl.base;

import java.time.ZonedDateTime;

import io.cygnus.indicator.IndicatorEvent;
import io.cygnus.indicator.impl.base.FloatPeriodIndicator.FloatPeriodPoint;
import io.horizon.market.data.MarketData;
import io.horizon.market.instrument.Instrument;
import io.mercury.common.sequence.TimePoint;

public abstract class FloatPeriodIndicator<P extends FloatPeriodPoint<M>, E extends IndicatorEvent, M extends MarketData>
		extends BaseIndicator<P, E, M> {

	protected FloatPeriodIndicator(Instrument instrument) {
		super(instrument);
	}

	public static abstract class FloatPeriodPoint<M extends MarketData> extends BasePoint<TimePoint, M> {

		protected FloatPeriodPoint(int index, TimePoint serial) {
			super(index, serial);
		}

		public ZonedDateTime getDatetime() {
			return serial.getDatetime();
		}

	}

}
