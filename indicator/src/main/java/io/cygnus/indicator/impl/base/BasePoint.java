package io.cygnus.indicator.impl.base;

import io.cygnus.indicator.Point;
import io.horizon.market.data.MarketData;
import io.mercury.common.annotation.lang.AbstractFunction;
import io.mercury.common.sequence.Serial;
import io.mercury.common.util.Assertor;
import lombok.Getter;

public abstract class BasePoint<S extends Serial<S>, M extends MarketData> implements Point<S> {

	@Getter
	protected final int index;

	@Getter
	protected final S serial;
	
	@Getter
	protected M preMarketData;

	protected BasePoint(int index, S serial) {
		Assertor.greaterThan(index, -1, "index");
		Assertor.nonNull(serial, "serial");
		this.index = index;
		this.serial = serial;
	}

	public void handleMarketData(M marketData) {
		handleMarketData0(marketData);
		updatePreMarketData(marketData);
	}

	public void updatePreMarketData(M marketData) {
		this.preMarketData = marketData;
	}

	@AbstractFunction
	protected abstract void handleMarketData0(M marketData);

}
