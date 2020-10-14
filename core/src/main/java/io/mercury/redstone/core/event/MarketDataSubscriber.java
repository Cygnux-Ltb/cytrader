package io.mercury.redstone.core.event;

import io.mercury.financial.instrument.Instrument;

@FunctionalInterface
public interface MarketDataSubscriber {

	void subscribeMarketData(Instrument... instruments);

}
