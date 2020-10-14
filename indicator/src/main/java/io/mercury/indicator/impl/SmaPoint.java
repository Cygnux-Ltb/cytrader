package io.mercury.indicator.impl;

import java.time.Duration;

import io.mercury.common.collections.list.FixedLengthRecorder;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriodSerial;

public final class SmaPoint extends MaPoint  {

	private long historyPriceSum;

	private int cycle;

	public SmaPoint(int index, Instrument instrument, Duration duration, TimePeriodSerial timePeriod,
			int cycle, FixedLengthRecorder historyPriceRecorder) {
		super(index, instrument, duration, timePeriod, historyPriceRecorder);
		this.historyPriceSum = historyPriceRecorder.sum();
		this.cycle = cycle;
	}

	public static SmaPoint with(int indxe, Instrument instrument, Duration duration, TimePeriodSerial timePeriod,
			int cycle, FixedLengthRecorder historyPriceRecorder) {
		return new SmaPoint(indxe, instrument, duration, timePeriod, cycle, historyPriceRecorder);
	}

	public int cycle() {
		return cycle;
	}

	@Override
	protected void handleMarketData0(BasicMarketData marketData) {
		this.lastPrice = marketData.getLastPrice();
		int count = historyPriceRecorder.count();
		this.avgPrice = (historyPriceSum + lastPrice) / count;
	}

	public static void main(String[] args) {

		double d = 1 + 1 + 6 + 10;
		double b = d / 4;
		System.out.println(b);
		double b1 = b + 20;
		System.out.println(b1 / 2);
		System.out.println((1 + 1 + 6 + 10 + 20) / 2);

	}

}
