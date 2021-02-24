package io.cygnus.engine.strategy.api;

import io.horizon.structure.event.InboundScheduler;
import io.horizon.structure.market.data.MarketData;

/**
 * 
 * @author yellow013
 *
 * @param <M>
 */
public interface MultiStrategyScheduler<M extends MarketData> extends InboundScheduler<M> {

	void addStrategy(Strategy<M> strategy);

}
