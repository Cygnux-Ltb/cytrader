package io.cygnus.indicator.impl;

import java.time.Duration;

import io.cygnus.indicator.IndicatorEvent;
import io.cygnus.indicator.impl.MacdIndicator.MacdEvent;
import io.cygnus.indicator.impl.base.FixedPeriodIndicator;
import io.horizon.definition.market.data.impl.BasicMarketData;
import io.horizon.definition.market.instrument.Instrument;

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
		default String eventName() {
			return "MacdEvent";
		}

	}

}
