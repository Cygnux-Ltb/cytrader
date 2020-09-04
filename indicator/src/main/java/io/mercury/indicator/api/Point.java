package io.mercury.indicator.api;

import io.mercury.common.sequence.Serial;
import io.mercury.financial.market.api.MarketData;

public interface Point<S extends Serial, M extends MarketData> extends Comparable<Point<S, M>> {

	int index();

	S serial();

	void onMarketData(M marketData);

	@Override
	default int compareTo(Point<S, M> o) {
		return serial().compareTo(o.serial());
	}

}
