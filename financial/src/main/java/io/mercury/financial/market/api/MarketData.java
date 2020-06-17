package io.mercury.financial.market.api;

public interface MarketData {

	MarketDataType getMarketDataType();

	String getInstrumentId();

	long getEpochMillis();

	long getLastPrice();

	int getVolume();

	long getTurnover();

	long getBidPrice1();

	int getBidVolume1();

	long getAskPrice1();

	int getAskVolume1();

	public static enum MarketDataType {
		Record, Basic, Depth, Static, Group
	}

}
