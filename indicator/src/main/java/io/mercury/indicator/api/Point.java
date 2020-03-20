package io.mercury.indicator.api;

import io.mercury.common.sequence.Serial;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;

public interface Point<S extends Serial<S>> {

	int index();

	Instrument instrument();

	S serial();

	void onMarketData(BasicMarketData marketData);

}
