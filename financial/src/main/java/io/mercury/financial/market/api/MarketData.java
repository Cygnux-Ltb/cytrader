package io.mercury.financial.market.api;

import io.mercury.financial.instrument.Instrument;

public interface MarketData {

	MarketDataType getMarketDataType();

	Instrument getInstrument();

	long getEpochMillis();

	long getLastPrice();

	int getVolume();

	long getTurnover();

	long getBidPrice1();

	int getBidVolume1();

	long getAskPrice1();

	int getAskVolume1();

	public static enum MarketDataType {
		Basic, Depth, Static, Group
	}

}
