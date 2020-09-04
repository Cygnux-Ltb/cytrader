package io.mercury.indicator.impl;

import io.mercury.common.annotation.lang.AbstractFunction;
import io.mercury.common.sequence.Serial;
import io.mercury.common.util.Assertor;
import io.mercury.financial.market.api.MarketData;
import io.mercury.indicator.api.Point;

public abstract class BasePoint<S extends Serial, M extends MarketData> implements Point<S, M> {

	protected int index;

	protected S serial;
	protected M preMarketData;

	protected BasePoint(int index, S serial) {
		this.index = Assertor.greaterThan(index, -1, "index");
		this.serial = Assertor.nonNull(serial, "serial");
	}

	@Override
	public int index() {
		return index;
	}

	@Override
	public S serial() {
		return serial;
	}

	@Override
	public void onMarketData(M marketData) {
		handleMarketData(marketData);
		updatePreMarketData(marketData);
	}

	public void updatePreMarketData(M marketData) {
		this.preMarketData = marketData;
	}

	@AbstractFunction
	protected abstract void handleMarketData(M marketData);

}
