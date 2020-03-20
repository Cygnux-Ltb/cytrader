package io.mercury.indicator.api;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;

public interface Indicator<P extends Point<?>, E extends IndicatorEvent> {

	Instrument instrument();

	void onMarketData(BasicMarketData marketData);

	void addIndicatorEvent(E event);

	P getPoint(int index);

	P fastPoint();

	P currentPoint();

	PointSet<P> pointSet();

}
