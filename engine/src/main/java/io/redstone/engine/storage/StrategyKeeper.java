package io.redstone.engine.storage;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.ffreedom.common.collections.MutableLists;
import io.ffreedom.common.collections.MutableMaps;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.polaris.financial.instrument.Instrument;
import io.redstone.core.strategy.Strategy;

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
		ImmutableList<Instrument> instruments = strategy.getInstruments();
		for (Instrument instrument : instruments) {
			int instrumentId = instrument.getInstrumentId();
			MutableList<Strategy> strategyList = InnerInstance.instrumentStrategyMap.get(instrumentId);
			if (strategyList == null) {
				strategyList = MutableLists.newFastList();
				InnerInstance.instrumentStrategyMap.put(instrumentId, strategyList);
			}
			strategyList.add(strategy);
			logger.info("Put to instrumentStrategyMap. strategyId==[{}], instrumentId==[{}]", strategy.getStrategyId(),
					instrumentId);
		}
		strategy.enable();

	}

	public static Strategy getStrategy(int strategyId) {
		return InnerInstance.strategyMap.get(strategyId);
	}

	public static MutableList<Strategy> getStrategys(int instrumentId) {
		return InnerInstance.instrumentStrategyMap.get(instrumentId);
	}

}
