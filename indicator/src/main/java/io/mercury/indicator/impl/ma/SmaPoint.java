package io.mercury.indicator.impl.ma;

import io.mercury.common.number.DoubleArithmetic;
import io.mercury.indicator.api.CalculationCycle;
import io.mercury.indicator.impl.ma.base.MaPoint;
import io.mercury.indicator.structure.FixedHistoryPriceRecorder;
import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.mercury.polaris.financial.vector.TimePeriod;
import io.mercury.polaris.financial.vector.TimePeriodSerial;

public final class SmaPoint extends MaPoint<SmaPoint> {

	private double historyPriceSum;

	private CalculationCycle cycle;

	public SmaPoint(int index, Instrument instrument, TimePeriod period, TimePeriodSerial timePeriod,
			CalculationCycle cycle, FixedHistoryPriceRecorder historyPriceRecorder) {
		super(index, instrument, period, timePeriod, historyPriceRecorder);
		this.historyPriceSum = historyPriceRecorder.sum();
		this.cycle = cycle;
	}

	public static SmaPoint with(int indxe, Instrument instrument, TimePeriod period, TimePeriodSerial timePeriod,
			CalculationCycle cycle, FixedHistoryPriceRecorder historyPriceRecorder) {
		return new SmaPoint(indxe, instrument, period, timePeriod, cycle, historyPriceRecorder);
	}

	public CalculationCycle cycle() {
		return cycle;
	}

	@Override
	protected void handleMarketData(BasicMarketData marketData) {
		this.lastPrice = marketData.getLastPrice();
		int count = historyPriceRecorder.count();
		this.avgPrice = DoubleArithmetic.correction4(historyPriceSum + lastPrice / count);
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
