package io.mercury.financial.indicator.api;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;

public interface Indicator<P extends Point<?, M>, M extends MarketData, E extends IndicatorEvent> {

	Instrument instrument();

	void onMarketData(M marketData);

	void addEvent(E event);

	P getPoint(int index);

	P fastPoint();

	P currentPoint();

	PointSet<P> pointSet();

}
