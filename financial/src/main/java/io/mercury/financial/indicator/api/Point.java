package io.mercury.financial.indicator.api;

import io.mercury.common.sequence.Serial;
import io.mercury.financial.market.api.MarketData;

public interface Point<ST extends Serial, MT extends MarketData> extends Comparable<Point<ST, MT>> {

	int index();

	ST serial();

	void onMarketData(MT marketData);

	@Override
	default int compareTo(Point<ST, MT> o) {
		return serial().compareTo(o.serial());
	}

}
