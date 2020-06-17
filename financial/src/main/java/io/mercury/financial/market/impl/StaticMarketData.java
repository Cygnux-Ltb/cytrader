package io.mercury.financial.market.impl;

import io.mercury.financial.market.api.MarketData;

public final class StaticMarketData implements MarketData {

	@Override
	public MarketDataType getMarketDataType() {
		// TODO Auto-generated method stub
		return MarketDataType.Static;
	}

	@Override
	public String getInstrumentId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getEpochMillis() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getLastPrice() {
		// TODO Auto-generated method stub
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
