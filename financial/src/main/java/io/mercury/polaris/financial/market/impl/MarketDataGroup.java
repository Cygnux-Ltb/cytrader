package io.mercury.polaris.financial.market.impl;

import java.util.Set;

public final class GroupMarketData<M extends BasicMarketData> {

	private Set<M> marketDataSet;

	public GroupMarketData(Set<M> marketDataSet) {
		super();
		this.marketDataSet = marketDataSet;
	}

	public Set<M> getMarketDataSet() {
		return marketDataSet;
	}

}
