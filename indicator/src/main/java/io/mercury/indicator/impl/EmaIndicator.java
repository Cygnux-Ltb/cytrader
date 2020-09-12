package io.mercury.indicator.impl;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.indicator.api.IndicatorEvent;
import io.mercury.indicator.impl.EmaIndicator.EmaEvent;
import io.mercury.indicator.impl.base.FixedPeriodIndicator;

public final class EmaIndicator extends FixedPeriodIndicator<EmaPoint, EmaEvent, BasicMarketData> {

	public EmaIndicator(Instrument instrument, TimePeriod period, int cycle) {
		super(instrument, period);
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
