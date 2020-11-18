package io.cygnus.engine.strategy;

import io.horizon.definition.event.InboundScheduler;
import io.horizon.definition.market.data.MarketData;

/**
 * 
 * @author yellow013
 *
 * @param <M>
 */
public interface StrategyScheduler<M extends MarketData> extends InboundScheduler<M> {

	void addStrategy(Strategy<M> strategy);

}
