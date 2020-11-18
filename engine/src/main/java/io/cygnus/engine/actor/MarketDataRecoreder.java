package io.apollo.engine.actor;

import com.lmax.disruptor.EventHandler;

import io.horizon.definition.market.data.impl.BasicMarketData;

public final class MarketDataRecoreder implements EventHandler<BasicMarketData> {

	@Override
	public void onEvent(BasicMarketData event, long sequence, boolean endOfBatch) throws Exception {
		// TODO
	}

}
