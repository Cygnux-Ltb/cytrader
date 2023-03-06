package io.cygnuxltb.engine.scheduler;

import io.horizon.market.data.MarketData;
import io.horizon.trader.handler.InboundHandler;
import io.horizon.trader.strategy.Strategy;

/**
 * 
 * @author yellow013
 *
 * @param <M>
 */
public interface MultiStrategyScheduler<M extends MarketData> extends InboundHandler<M> {

	void addStrategy(Strategy<M> strategy);

}
