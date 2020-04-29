package io.mercury.financial.market.api;

public interface MarketData {

	MarketDataType marketDataType();

	public static enum MarketDataType {
		Basic, Depth, Group
	}

}
