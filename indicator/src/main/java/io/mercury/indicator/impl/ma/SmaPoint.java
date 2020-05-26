package io.mercury.indicator.impl.ma;

import io.mercury.common.collections.list.FixedLengthRecorder;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.mercury.financial.vector.TimePeriod;
import io.mercury.financial.vector.TimePeriodSerial;

public final class SmaPoint extends MaPoint {

	private long historyPriceSum;

	private int cycle;

	public SmaPoint(int index, Instrument instrument, TimePeriod period, TimePeriodSerial timePeriod,
			int cycle, FixedLengthRecorder historyPriceRecorder) {
		super(index, instrument, period, timePeriod, historyPriceRecorder);
		this.historyPriceSum = historyPriceRecorder.sum();
		this.cycle = cycle;
	}

	public static SmaPoint with(int indxe, Instrument instrument, TimePeriod period, TimePeriodSerial timePeriod,
			int cycle, FixedLengthRecorder historyPriceRecorder) {
		return new SmaPoint(indxe, instrument, period, timePeriod, cycle, historyPriceRecorder);
	}

	public int cycle() {
		return cycle;
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
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
