package io.mercury.financial.market.impl;

import org.eclipse.collections.api.set.MutableSet;

public final class GroupMarketData<M extends BasicMarketData> {

	private MutableSet<M> marketDataSet;

	public GroupMarketData(MutableSet<M> marketDataSet) {
		this.marketDataSet = marketDataSet;
	}

	public MutableSet<M> getMarketDataSet() {
		return marketDataSet;
	}

}
