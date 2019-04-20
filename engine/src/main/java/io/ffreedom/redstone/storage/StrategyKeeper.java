package io.ffreedom.redstone.storage;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.ffreedom.common.collect.MutableLists;
import io.ffreedom.common.collect.MutableMaps;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.redstone.core.strategy.Strategy;

@NotThreadSafe
public final class StrategyKeeper {

	private static final Logger logger = CommonLoggerFactory.getLogger(StrategyKeeper.class);

	private MutableIntObjectMap<Strategy> strategyMap = MutableMaps.newIntObjectHashMap();

	// Map<instrumentId, List<Strategy>>
	private MutableIntObjectMap<MutableList<Strategy>> instrumentStrategyMap = MutableMaps.newIntObjectHashMap();

	public static final StrategyKeeper InnerInstance = new StrategyKeeper();

	private StrategyKeeper() {
	}

	public static void putStrategy(Strategy strategy) {
		InnerInstance.strategyMap.put(strategy.getStrategyId(), strategy);
		logger.info("Put to strategyMap. strategyId==[{}]", strategy.getStrategyId());
		int instrumentId = strategy.getInstrument().getInstrumentId();
		MutableList<Strategy> strategyList = InnerInstance.instrumentStrategyMap.get(instrumentId);
		if (strategyList == null) {
			strategyList = MutableLists.newFastList();
			InnerInstance.instrumentStrategyMap.put(instrumentId, strategyList);
		}
		strategy.enable();
		strategyList.add(strategy);
		logger.info("Put to instrumentStrategyMap. strategyId==[{}], instrumentId==[{}]", strategy.getStrategyId(),
				instrumentId);
	}

	public static Strategy getStrategy(int strategyId) {
		return InnerInstance.strategyMap.get(strategyId);
	}

	public static MutableList<Strategy> getStrategys(int instrumentId) {
		return InnerInstance.instrumentStrategyMap.get(instrumentId);
	}

}
