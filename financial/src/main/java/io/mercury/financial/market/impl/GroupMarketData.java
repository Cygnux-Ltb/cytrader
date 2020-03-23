package io.mercury.financial.market.impl;

import java.util.Set;

import org.eclipse.collections.api.set.MutableSet;

public final class GroupMarketData<M extends BasicMarketData> {

	private MutableSet<M> marketDataSet;

	public GroupMarketData(MutableSet<M> marketDataSet) {
		this.marketDataSet = marketDataSet;
	}

	public Set<M> getMarketDataSet() {
		return marketDataSet;
	}

}
