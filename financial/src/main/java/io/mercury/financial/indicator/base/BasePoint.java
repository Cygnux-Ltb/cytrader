package io.mercury.financial.indicator.base;

import io.mercury.common.annotation.lang.ProtectedAbstractMethod;
import io.mercury.common.sequence.Serial;
import io.mercury.common.util.Assertor;
import io.mercury.financial.indicator.api.Point;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.api.MarketData;

public abstract class BasePoint<S extends Serial<S>, M extends MarketData> implements Point<S, M>, Comparable<BasePoint<S, M>> {

	protected int index;
	protected Instrument instrument;

	protected M preMarketData;

	protected BasePoint(int index, Instrument instrument) {
		this.index = Assertor.greaterThan(index, -1, "index");
		this.instrument = instrument;
	}

	@Override
	public int index() {
		return index;
	}

	@Override
	public Instrument instrument() {
		return instrument;
	}

	@Override
	public int compareTo(BasePoint<S, M> o) {
		return serial().compareTo(o.serial());
	}

	@Override
	public void onMarketData(M marketData) {
		handleMarketData(marketData);
		updatePreMarketData(marketData);
	}

	public void updatePreMarketData(M marketData) {
		this.preMarketData = marketData;
	}

	@ProtectedAbstractMethod
	protected abstract void handleMarketData(M marketData);

}
