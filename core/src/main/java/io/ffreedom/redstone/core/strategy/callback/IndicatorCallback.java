package io.ffreedom.redstone.core.strategy.callback;

import io.ffreedom.polaris.indicators.api.TimeSeriesPoint;

public interface IndicatorCallback<P extends TimeSeriesPoint<?>> {

	void onStartPoint(P p);

	void onEndPoint(P p);

}
