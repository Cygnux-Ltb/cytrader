package io.mercury.financial.market.impl;

import io.mercury.financial.market.api.MarketData;

public final class StaticMarketData implements MarketData {

	@Override
	public MarketDataType getMarketDataType() {
		return MarketDataType.Static;
	}

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getTurnover() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getBidPrice1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBidVolume1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getAskPrice1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAskVolume1() {
		// TODO Auto-generated method stub
		return 0;
	}

}
