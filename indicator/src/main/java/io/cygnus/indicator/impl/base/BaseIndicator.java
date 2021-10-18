package io.cygnus.indicator.impl.base;

import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;

import io.cygnus.indicator.Indicator;
import io.cygnus.indicator.IndicatorEvent;
import io.cygnus.indicator.Point;
import io.cygnus.indicator.PointSet;
import io.cygnus.indicator.impl.base.BaseIndicator.BasePoint;
import io.horizon.market.data.MarketData;
import io.horizon.market.instrument.Instrument;
import io.mercury.common.annotation.lang.AbstractFunction;
import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableLists;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.sequence.Serial;
import io.mercury.common.util.Assertor;

public abstract class BaseIndicator<P extends BasePoint<?, M>, E extends IndicatorEvent, M extends MarketData>
		implements Indicator<P, E, M> {

	private static final Logger log = CommonLoggerFactory.getLogger(BaseIndicator.class);

	// 指标对应的标的
	protected final Instrument instrument;

	// 存储所有Point的集合
	protected final PointSet<P> pointSet;

	// 当前Point
	protected P currentPoint;

	// 前一笔行情
	protected M preMarketData;

	// 存储事件的集合
	protected MutableList<E> events = MutableLists.newFastList(8);

	protected BaseIndicator(Instrument instrument) {
		this(instrument, Capacity.L08_SIZE);
	}

	protected BaseIndicator(Instrument instrument, Capacity capacity) {
		this.instrument = instrument;
		this.pointSet = PointSet.newEmpty(capacity);
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public PointSet<P> getPointSet() {
		return pointSet;
	}

	public P getCurrentPoint() {
		return currentPoint;
	}

	public M getPreMarketData() {
		return preMarketData;
	}

	@Override
	public void addEvent(E event) {
		if (event != null) {
			log.info("Add IndicatorEvent -> name==[{}]", event.getEventName());
			events.add(event);
		}
	}

	@Override
	public void onMarketData(M marketData) {
		handleMarketData(marketData);
		this.preMarketData = marketData;
	}

	@AbstractFunction
	protected abstract void handleMarketData(M marketData);

	@Override
	public P getFastPoint() {
		if (pointSet.size() == 0)
			return currentPoint;
		return pointSet.getFirst();
	}

	@Override
	public P getPoint(int index) {
		if (index < 0 || pointSet.size() == 0)
			return currentPoint;
		if (index >= pointSet.size())
			index = pointSet.size() - 1;
		return pointSet.get(index).orElse(currentPoint);
	}

	/**
	 * 
	 * @author yellow013
	 *
	 * @param <S>
	 * @param <M>
	 */
	public static abstract class BasePoint<S extends Serial<S>, M extends MarketData> implements Point<S> {

		protected final int index;

		protected final S serial;

		protected M preMarketData;

		protected BasePoint(int index, S serial) {
			Assertor.greaterThan(index, -1, "index");
			Assertor.nonNull(serial, "serial");
			this.index = index;
			this.serial = serial;
		}

		public int getIndex() {
			return index;
		}

		public S getSerial() {
			return serial;
		}

		public M getPreMarketData() {
			return preMarketData;
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

}
