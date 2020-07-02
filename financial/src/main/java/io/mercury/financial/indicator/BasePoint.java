package io.mercury.financial.indicator;

import io.mercury.common.annotation.lang.ProtectedAbstractMethod;
import io.mercury.common.sequence.Serial;
import io.mercury.common.util.Assertor;
import io.mercury.financial.indicator.api.Point;
import io.mercury.financial.market.api.MarketData;

public abstract class BasePoint<ST extends Serial, MT extends MarketData> implements Point<ST, MT> {

	protected int index;

	protected ST serial;
	protected MT preMarketData;

	protected BasePoint(int index, ST serial) {
		this.index = Assertor.greaterThan(index, -1, "index");
		this.serial = Assertor.nonNull(serial, "serial");
	}

	@Override
	public int index() {
		return index;
	}

	@Override
	public ST serial() {
		return serial;
	}

	@Override
	public void onMarketData(MT marketData) {
		handleMarketData(marketData);
		updatePreMarketData(marketData);
	}

	public void updatePreMarketData(MT marketData) {
		this.preMarketData = marketData;
	}

	@ProtectedAbstractMethod
	protected abstract void handleMarketData(MT marketData);

}
