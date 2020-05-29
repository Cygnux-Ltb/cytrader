package io.mercury.indicator.api;

import io.mercury.common.sequence.Serial;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;

public interface Point<S extends Serial<S>, M extends MarketData> {

	int index();

	Instrument instrument();

	S serial();

	void onMarketData(M marketData);

}
