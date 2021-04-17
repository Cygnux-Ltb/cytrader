package io.cygnus.engine.scheduler;

import java.io.IOException;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.set.MutableSet;
import org.slf4j.Logger;

import io.cygnus.engine.strategy.api.MultiStrategyScheduler;
import io.cygnus.engine.strategy.api.Strategy;
import io.horizon.market.data.MarketData;
import io.horizon.market.instrument.Instrument;
import io.mercury.common.annotation.lang.AbstractFunction;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.collections.MutableSets;
import io.mercury.common.log.CommonLoggerFactory;

public abstract class AbstractMultiStrategyScheduler<M extends MarketData> implements MultiStrategyScheduler<M> {

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(AbstractMultiStrategyScheduler.class);

	/**
	 * 策略列表
	 */
	protected final MutableIntObjectMap<Strategy<M>> strategyMap = MutableMaps.newIntObjectHashMap();

	/**
	 * 订阅合约的策略列表 <br>
	 * instrumentId -> Set::[Strategy]
	 */
	protected final MutableIntObjectMap<MutableSet<Strategy<M>>> subscribedMap = MutableMaps.newIntObjectHashMap();

	@Override
	public void addStrategy(Strategy<M> strategy) {
		log.info("Add strategy -> strategyId==[{}], strategyName==[{}], subAccount==[{}]", strategy.getStrategyId(),
				strategy.getStrategyName(), strategy.getSubAccount());
		strategyMap.put(strategy.getStrategyId(), strategy);
		strategy.getInstruments().each(instrument -> subscribeInstrument(instrument, strategy));
		strategy.enable();
	}

	private void subscribeInstrument(Instrument instrument, Strategy<M> strategy) {
		subscribedMap.getIfAbsentPut(instrument.getInstrumentId(), MutableSets::newUnifiedSet).add(strategy);
		log.info("Add subscribe instrument, strategyId==[{}], instrumentId==[{}]", strategy.getStrategyId(),
				instrument.getInstrumentId());
	}

	@AbstractFunction
	protected abstract void close0() throws IOException;

	@Override
	public void close() throws IOException {
		strategyMap.each(strategy -> {
			try {
				strategy.close();
			}catch (IOException e) {
				
			}
			
		});

	}

}
