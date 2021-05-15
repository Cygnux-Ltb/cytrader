package io.cygnus.indicator.impl.base;

import java.time.Duration;
import java.time.ZonedDateTime;

import io.cygnus.indicator.IndicatorEvent;
import io.cygnus.indicator.impl.base.FixedPeriodIndicator.FixedPeriodPoint;
import io.horizon.market.data.MarketData;
import io.horizon.market.instrument.Instrument;
import io.horizon.market.serial.TimePeriodSerial;
import lombok.Getter;

public abstract class FixedPeriodIndicator<P extends FixedPeriodPoint<M>, E extends IndicatorEvent, M extends MarketData>
		extends BaseIndicator<P, E, M> {

	@Getter
	protected final Duration duration;

	@Getter
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

	/**
	 * 
	 * @author yellow013
	 *
	 * @param <M>
	 */
	public static abstract class FixedPeriodPoint<M extends MarketData> extends BasePoint<TimePeriodSerial, M> {

		protected FixedPeriodPoint(int index, TimePeriodSerial serial) {
			super(index, serial);
		}

		public ZonedDateTime getStartTime() {
			return serial.getStartTime();
		}

		public ZonedDateTime getEndTime() {
			return serial.getEndTime();
		}

	}

}
