package io.cygnus.engine.strategy.api;

import io.horizon.market.data.MarketData;
import io.horizon.trader.event.InboundScheduler;

/**
 * 
 * @author yellow013
 *
 * @param <M>
 */
public interface MultiStrategyScheduler<M extends MarketData> extends InboundScheduler<M> {

	void addStrategy(Strategy<M> strategy);

}
