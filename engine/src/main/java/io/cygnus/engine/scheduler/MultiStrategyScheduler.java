package io.cygnus.engine.scheduler;

import io.horizon.market.data.MarketData;
import io.horizon.trader.handler.InboundScheduler;
import io.horizon.trader.strategy.Strategy;

/**
 * 
 * @author yellow013
 *
 * @param <M>
 */
public interface MultiStrategyScheduler<M extends MarketData> extends InboundScheduler<M> {

	void addStrategy(Strategy<M> strategy);

}
