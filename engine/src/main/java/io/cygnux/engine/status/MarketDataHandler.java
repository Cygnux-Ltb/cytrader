package io.cygnux.engine.status;

import com.lmax.disruptor.EventHandler;

import io.horizon.market.data.impl.BasicMarketData;

public final class MarketDataHandler implements EventHandler<BasicMarketData> {

	@Override
	public void onEvent(BasicMarketData event, long sequence, boolean endOfBatch) throws Exception {
		// TODO
	}

}
