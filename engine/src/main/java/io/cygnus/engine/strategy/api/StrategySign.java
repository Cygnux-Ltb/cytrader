package io.cygnus.engine.strategy.api;

import javax.annotation.Nonnull;

public interface StrategySign {

	int getStrategyId();

	@Nonnull
	String getStrategyName();

}
