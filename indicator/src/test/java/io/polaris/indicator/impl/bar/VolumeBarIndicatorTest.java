package io.polaris.indicator.impl.bar;

import org.junit.Test;

import io.mercury.polaris.financial.instrument.Instrument.PriorityCloseType;
import io.mercury.polaris.financial.instrument.futures.ChinaFutures;
import io.mercury.polaris.financial.instrument.futures.ChinaFuturesSymbol;
import io.mercury.polaris.indicator.impl.bar.VolumeBarIndicator;

public class VolumeBarIndicatorTest {

	@Test
	public void test() {

		// TODO 读取数据文件

		ChinaFutures rb1910 = ChinaFutures.build(ChinaFuturesSymbol.RB, 1910, PriorityCloseType.BEFORE_TODAY);

		VolumeBarIndicator indicator = new VolumeBarIndicator(rb1910, 2000);

		indicator.onMarketData(null);

	}

}
