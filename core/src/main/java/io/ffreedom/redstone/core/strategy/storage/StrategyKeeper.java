package io.ffreedom.redstone.core.strategy.storage;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.ffreedom.common.collect.ECollections;
import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.redstone.core.strategy.Strategy;

@NotThreadSafe
public final class StrategyKeeper {

	private static final Logger logger = LoggerFactory.getLogger(StrategyKeeper.class);

	private MutableIntObjectMap<Strategy> strategyMap = ECollections.newIntObjectHashMap();

	// Map<instrumentId, List<Strategy>>
	private MutableIntObjectMap<MutableList<Strategy>> instrumentStrategyMap = ECollections.newIntObjectHashMap();

	public static final StrategyKeeper INSTANCE = new StrategyKeeper();

	private StrategyKeeper() {
	}

	public static void put(Strategy strategy, Instrument... instruments) {
		INSTANCE.strategyMap.put(strategy.getStrategyId(), strategy);
		logger.info("Put to strategyMap. strategyId==[{}]", strategy.getStrategyId());
		if (instruments != null) {
			for (int i = 0; i < instruments.length; i++) {
				int instrumentId = instruments[i].getInstrumentId();
				MutableList<Strategy> strategyList = INSTANCE.instrumentStrategyMap.get(instrumentId);
				if (strategyList == null) {
					strategyList = ECollections.newFastList();
					INSTANCE.instrumentStrategyMap.put(instrumentId, strategyList);
				}
				strategyList.add(strategy);
				logger.info("Put to instrumentStrategyMap. strategyId==[{}], instrumentId==[{}]",
						strategy.getStrategyId(), instrumentId);
			}
		}
	}

	public static Strategy getStrategy(int strategyId) {
		return INSTANCE.strategyMap.get(strategyId);
	}

	public static MutableList<Strategy> getStrategys(int instrumentId) {
		return INSTANCE.instrumentStrategyMap.get(instrumentId);
	}

}
