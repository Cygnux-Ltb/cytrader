package io.mercury.indicator.base;

import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;

import io.mercury.common.annotation.lang.ProtectedAbstractMethod;
import io.mercury.common.collections.Capacity;
import io.mercury.common.collections.MutableLists;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.sequence.Serial;
import io.mercury.indicator.api.Indicator;
import io.mercury.indicator.api.IndicatorEvent;
import io.mercury.indicator.api.Point;
import io.mercury.indicator.api.PointSet;
import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.impl.BasicMarketData;

public abstract class BaseIndicator<P extends Point<? extends Serial<?>>, E extends IndicatorEvent>
		implements Indicator<P, E> {

	protected Logger logger = CommonLoggerFactory.getLogger(getClass());

	// 指标对应的标的
	protected Instrument instrument;

	// 存储所有Point的集合
	protected PointSet<P> pointSet;

	// 当前Point
	protected P currentPoint;

	// 前一笔行情
	protected BasicMarketData preMarketData;

	// 存储事件的集合
	protected MutableList<E> events = MutableLists.newFastList(8);

	protected BaseIndicator(Instrument instrument) {
		this(instrument, Capacity.L08_SIZE_256);
	}

	protected BaseIndicator(Instrument instrument, Capacity capacity) {
		this.instrument = instrument;
		this.pointSet = PointSet.newEmpty(capacity);
	}

	@Override
	public void addIndicatorEvent(E event) {
		if (event != null) {
			logger.info("Add IndicatorEvent -> name==[{}]", event.eventName());
			events.add(event);
		}
	}

	@Override
	public void onMarketData(BasicMarketData marketData) {
		handleMarketData(marketData);
		this.preMarketData = marketData;
	}

	@ProtectedAbstractMethod
	protected abstract void handleMarketData(BasicMarketData marketData);

	@Override
	public Instrument instrument() {
		return instrument;
	}

	@Override
	public P fastPoint() {
		if (pointSet.size() == 0)
			return currentPoint;
		return pointSet.getFirst();
	}

	@Override
	public P currentPoint() {
		return currentPoint;
	}

	@Override
	public P getPoint(int index) {
		if (index < 0 || pointSet.size() == 0)
			return currentPoint;
		if (index >= pointSet.size())
			index = pointSet.size() - 1;
		return pointSet.get(index).orElse(currentPoint);
	}

	@Override
	public PointSet<P> pointSet() {
		return pointSet;
	}

}
