package io.apollo.indicator.impl;

import java.time.Duration;

import io.apollo.indicator.api.IndicatorEvent;
import io.apollo.indicator.impl.EmaIndicator.EmaEvent;
import io.apollo.indicator.impl.base.FixedPeriodIndicator;
import io.gemini.definition.market.data.impl.BasicMarketData;
import io.gemini.definition.market.instrument.Instrument;

public final class EmaIndicator extends FixedPeriodIndicator<EmaPoint, EmaEvent, BasicMarketData> {

	public EmaIndicator(Instrument instrument, Duration duration, int cycle) {
		super(instrument, duration);
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		// TODO Auto-generated method stub

	}
	
	public interface EmaEvent extends IndicatorEvent {

		@Override
		default String eventName() {
			return "EmaEvent";
		}

		void onCurrentEmaPointAvgPriceChanged(EmaPoint point);

		void onStartEmaPoint(EmaPoint point);

		void onEndEmaPoint(EmaPoint point);

	}


}
