package io.cygnus.indicator.impl.base;

import java.time.Duration;

import io.cygnus.indicator.IndicatorEvent;
import io.horizon.structure.market.data.MarketData;
import io.horizon.structure.market.instrument.Instrument;
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

}
