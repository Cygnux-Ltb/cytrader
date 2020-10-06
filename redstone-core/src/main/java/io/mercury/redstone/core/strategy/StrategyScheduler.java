package io.mercury.redstone.core.strategy;

import io.mercury.financial.market.api.MarketData;
import io.mercury.redstone.core.EventScheduler;

/**
 * 
 * @author yellow013
 *
 * @param <M>
 */
public interface StrategyScheduler<M extends MarketData> extends EventScheduler<M> {

	void addStrategy(Strategy<M> strategy);

}
