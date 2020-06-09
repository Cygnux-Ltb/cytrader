package io.mercury.redstone.engine.scheduler;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.set.MutableSet;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.financial.market.api.MarketData;
import io.mercury.redstone.core.strategy.Strategy;
import io.mercury.redstone.core.strategy.StrategyScheduler;

public abstract class MultipleStrategyScheduler<M extends MarketData> implements StrategyScheduler<M> {

	/**
	 * 策略列表
	 */
	protected final MutableIntObjectMap<Strategy<M>> strategyMap = MutableMaps.newIntObjectHashMap();

	/**
	 * 订阅合约的策略列表
	 */
	protected final MutableIntObjectMap<MutableSet<Strategy<M>>> subscribedMap = MutableMaps.newIntObjectHashMap();

	/**
	 * Logger
	 */
	protected final Logger log = CommonLoggerFactory.getLogger(this.getClass());

}
