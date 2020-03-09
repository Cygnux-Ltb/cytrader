package io.redstone.engine.actor;

import com.lmax.disruptor.EventHandler;

import io.mercury.polaris.financial.market.impl.BasicMarketData;

public final class MarketDataDumpActor implements EventHandler<BasicMarketData> {

	
	
	@Override
	public void onEvent(BasicMarketData event, long sequence, boolean endOfBatch) throws Exception {
		// TODO Auto-generated method stub

	}

}
