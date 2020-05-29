package io.mercury.financial.indicator.specific.ma;

import io.mercury.common.collections.list.FixedLengthRecorder;
import io.mercury.financial.indicator.base.FixedPeriodPoint;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.financial.vector.TimePeriodSerial;

public abstract class MaPoint extends FixedPeriodPoint {

	protected FixedLengthRecorder historyPriceRecorder;
	protected long avgPrice;
	protected long lastPrice;

	protected MaPoint(int index, Instrument instrument, TimePeriod period, TimePeriodSerial timePeriod,
			FixedLengthRecorder historyPriceRecorder) {
		super(index, instrument, period, timePeriod);
		this.historyPriceRecorder = historyPriceRecorder;
	}

	public double avgPrice() {
		return avgPrice;
	}

	public double lastPrice() {
		return lastPrice;
	}

	public FixedLengthRecorder historyPriceRecorder() {
		return historyPriceRecorder;
	}

}
