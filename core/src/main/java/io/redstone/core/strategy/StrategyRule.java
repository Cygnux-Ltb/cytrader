package io.ffreedom.redstone.core.strategy;

public interface StrategyRule {

	boolean getTruth();

	default boolean and(StrategyRule rule) {
		return getTruth() && rule.getTruth();
	}

	default boolean or(StrategyRule rule) {
		return getTruth() || rule.getTruth();
	}

	default boolean not() {
		return !getTruth();
	}

}
