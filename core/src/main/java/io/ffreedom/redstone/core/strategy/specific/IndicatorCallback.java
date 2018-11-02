package io.ffreedom.redstone.core.strategy.specific;

import io.ffreedom.indicators.api.TimeSeriesPoint;

public interface IndicatorCallback<P extends TimeSeriesPoint<?>> {

	void onStartPoint(P p);

	void onEndPoint(P p);

}
