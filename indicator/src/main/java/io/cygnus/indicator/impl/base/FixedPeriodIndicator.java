package io.cygnus.indicator.impl.base;

import java.time.Duration;
import java.time.LocalDateTime;

import io.cygnus.indicator.IndicatorEvent;
import io.cygnus.indicator.impl.base.FixedPeriodIndicator.FixedPeriodPoint;
import io.horizon.market.data.MarketData;
import io.horizon.market.instrument.Instrument;
import io.mercury.common.sequence.TimeWindow;

public abstract class FixedPeriodIndicator<P extends FixedPeriodPoint<M>, E extends IndicatorEvent, M extends MarketData>
		extends BaseIndicator<P, E, M> {

	protected final Duration duration;

	protected final int cycle;

	/**
	 * 
	 * @param instrument
	 * @param period
	 */
	public FixedPeriodIndicator(Instrument instrument, Duration duration) {
		this(instrument, duration, 1);
	}

	/**
	 * 
	 * @param instrument
	 * @param period
	 * @param cycle
	 */
	public FixedPeriodIndicator(Instrument instrument, Duration duration, int cycle) {
		super(instrument);
		this.duration = duration;
		this.cycle = cycle;
	}

	public Duration getDuration() {
		return duration;
	}

	public int getCycle() {
		return cycle;
	}

	/**
	 * 
	 * @author yellow013
	 *
	 * @param <M>
	 */
	public static abstract class FixedPeriodPoint<M extends MarketData> extends BasePoint<TimeWindow, M> {

		protected FixedPeriodPoint(int index, TimeWindow serial) {
			super(index, serial);
		}

		public LocalDateTime getStartTime() {
			return serial.getStart();
		}

		public LocalDateTime getEndTime() {
			return serial.getEnd();
		}

	}

}
