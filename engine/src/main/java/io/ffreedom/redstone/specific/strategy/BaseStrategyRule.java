package io.ffreedom.redstone.specific.strategy;

import io.ffreedom.redstone.core.strategy.StrategyRule;

public abstract class BaseStrategyRule implements StrategyRule {

	private boolean truth;

	protected BaseStrategyRule() {
	}

	@Override
	public boolean getTruth() {
		return truth;
	}

	protected void inputTruthValue(boolean value) {
		this.truth = value;
	}

}
