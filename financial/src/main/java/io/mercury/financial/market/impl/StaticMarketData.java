package io.mercury.financial.market.impl;

import io.mercury.financial.market.api.MarketData;

public final class StaticMarketData implements MarketData {

	@Override
	public String getInstrumentId() {
		return null;
	}

	@Override
	public long getEpochMillis() {
		return 0;
	}

	@Override
	public long getLastPrice() {
		return 0;
	}

	@Override
	public int getVolume() {
		return 0;
	}

	@Override
	public long getTurnover() {
		return 0;
	}

	@Override
	public long getBidPrice1() {
		return 0;
	}

	@Override
	public int getBidVolume1() {
		return 0;
	}

	@Override
	public long getAskPrice1() {
		return 0;
	}

	@Override
	public int getAskVolume1() {
		return 0;
	}

}
