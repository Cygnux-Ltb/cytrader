package io.cygnux.engine.actor;

import io.horizon.market.data.impl.BasicMarketData;

import javax.annotation.Nonnull;

public class BasicMarketDataActor extends MarketDataActor<BasicMarketData> {

	@Override
	public void onMarketData(@Nonnull BasicMarketData marketData) {

	}

	@Override
	protected Class<BasicMarketData> eventType() {
		return BasicMarketData.class;
	}

}
