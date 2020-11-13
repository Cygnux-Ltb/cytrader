package io.apollo.engine.strategy;

import io.gemini.definition.event.InboundScheduler;
import io.gemini.definition.market.data.MarketData;

/**
 * 
 * @author yellow013
 *
 * @param <M>
 */
public interface StrategyScheduler<M extends MarketData> extends InboundScheduler<M> {

	void addStrategy(Strategy<M> strategy);

}
