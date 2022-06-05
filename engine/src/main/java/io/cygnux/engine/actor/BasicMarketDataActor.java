package io.cygnux.engine.actor;

import io.horizon.market.data.impl.BasicMarketData;

public class BasicMarketDataActor extends MarketDataActor<BasicMarketData> {

	@Override
	public void onMarketData(BasicMarketData marketData) {

	}

	@Override
	protected Class<BasicMarketData> eventType() {
		return BasicMarketData.class;
	}

}
