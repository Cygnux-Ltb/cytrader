package io.mercury.indicator.api;

import io.mercury.common.sequence.Serial;
import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.impl.BasicMarketData;

public interface Indicator<P extends Point<? extends Serial<?>>, E extends IndicatorEvent> {

	Instrument instrument();

	void onMarketData(BasicMarketData marketData);

	void addIndicatorEvent(E event);

	P getPoint(int index);

	P fastPoint();

	P currentPoint();

	PointSet<P> pointSet();

}
