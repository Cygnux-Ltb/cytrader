package io.ffreedom.redstone.actor;

import com.lmax.disruptor.EventHandler;

import io.ffreedom.polaris.market.impl.BasicMarketData;

public final class MarketDataDumpActor implements EventHandler<BasicMarketData> {

	
	
	@Override
	public void onEvent(BasicMarketData event, long sequence, boolean endOfBatch) throws Exception {
		// TODO Auto-generated method stub

	}

}
