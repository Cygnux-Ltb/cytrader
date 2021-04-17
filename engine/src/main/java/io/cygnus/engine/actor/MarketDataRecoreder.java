package io.cygnus.engine.actor;

import com.lmax.disruptor.EventHandler;

import io.horizon.market.data.impl.BasicMarketData;

public final class MarketDataRecoreder implements EventHandler<BasicMarketData> {

	@Override
	public void onEvent(BasicMarketData event, long sequence, boolean endOfBatch) throws Exception {
		// TODO
	}

}
