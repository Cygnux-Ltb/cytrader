package io.apollo.indicator.impl;

import java.time.Duration;

import io.apollo.indicator.api.IndicatorEvent;
import io.apollo.indicator.impl.MacdIndicator.MacdEvent;
import io.apollo.indicator.impl.base.FixedPeriodIndicator;
import io.gemini.definition.market.data.impl.BasicMarketData;
import io.gemini.definition.market.instrument.Instrument;

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
