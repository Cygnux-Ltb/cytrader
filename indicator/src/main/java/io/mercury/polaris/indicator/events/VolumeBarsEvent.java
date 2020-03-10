package io.mercury.polaris.indicator.events;

import io.mercury.polaris.indicator.api.IndicatorEvent;
import io.mercury.polaris.indicator.impl.bar.VolumeBar;

public interface VolumeBarsEvent extends IndicatorEvent {

	
	
	void onCurrentVolumeBarChanged(VolumeBar bar);

	void onStartVolumeBar(VolumeBar bar);

	void onEndVolumeBar(VolumeBar bar);

}
