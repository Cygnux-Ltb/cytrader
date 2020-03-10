package io.mercury.polaris.indicator.events;

import io.mercury.polaris.indicator.api.IndicatorEvent;
import io.mercury.polaris.indicator.impl.sar.SarPoint;

public interface SarEvent extends IndicatorEvent {

	void onCurrentSarChanged(SarPoint point);

	void onStartSar(SarPoint point);

	void onEndSar(SarPoint point);

}
