package io.mercury.indicator.api;

/**
 * 标识指标的一个点由N个周期的点计算
 * 
 * @author yellow013
 */
public final class CalculationCycle {

	public final static CalculationCycle OnlyOne = CalculationCycle.with(1);

	private int cycleValue;

	private CalculationCycle(int cycleValue) {
		this.cycleValue = cycleValue;
	}

	public static CalculationCycle with(int cycleValue) {
		if (cycleValue > 10000)
			throw new IllegalArgumentException("IndicatorCycle value is too big.");
		return new CalculationCycle(cycleValue);
	}

	public int getCycleValue() {
		return cycleValue;
	}

}
