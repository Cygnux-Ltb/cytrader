package io.redstone.core.strategy;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableLists;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.financial.instrument.Instrument;

@NotThreadSafe
public final class StrategyKeeper {

	private static final Logger log = CommonLoggerFactory.getLogger(StrategyKeeper.class);

	private MutableIntObjectMap<Strategy> strategyMap = MutableMaps.newIntObjectHashMap();

	// Map<instrumentId, List<Strategy>>
	private MutableIntObjectMap<MutableList<Strategy>> subscribedStrategysMap = MutableMaps.newIntObjectHashMap();

	public static final StrategyKeeper InnerInstance = new StrategyKeeper();

	private StrategyKeeper() {
	}

	public static void putStrategy(Strategy strategy) {
		InnerInstance.strategyMap.put(strategy.strategyId(), strategy);
		log.info("Put to strategyMap. strategyId==[{}]", strategy.strategyId());
		strategy.instruments().forEach(instrument -> {
			InnerInstance.subscribedStrategysMap.getIfAbsentPut(instrument.id(), MutableLists::newFastList)
					.add(strategy);
			log.info("Put to subscribedStrategysMap. strategyId==[{}], instrumentId==[{}]", strategy.strategyId(),
					instrument.id());
		});
		strategy.enable();
		log.info("Strategy is enable. strategyId==[{}]", strategy.strategyId());
	}

	public static Strategy getStrategy(int strategyId) {
		return InnerInstance.strategyMap.get(strategyId);
	}

	public static MutableList<Strategy> getSubscribedStrategys(int instrumentId) {
		return InnerInstance.subscribedStrategysMap.get(instrumentId);
	}

	public static MutableList<Strategy> getSubscribedStrategys(Instrument instrument) {
		return InnerInstance.subscribedStrategysMap.get(instrument.id());
	}

}
