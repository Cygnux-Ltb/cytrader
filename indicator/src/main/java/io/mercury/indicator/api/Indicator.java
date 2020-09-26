package io.mercury.indicator.api;

import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;

/**
 * 
 * @author yellow013
 *
 * @param <P> Point 类型
 * @param <E> IndicatorEvent 类型
 * @param <M> MarketData 类型
 */
public interface Indicator<P extends Point<?>, E extends IndicatorEvent, M extends MarketData> {

	Instrument instrument();

	void onMarketData(M marketData);

	void addEvent(E event);

	P getPoint(int index);

	P getFastPoint();

	P getCurrentPoint();

	PointSet<P> getPointSet();

}
