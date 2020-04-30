package io.redstone.core.keeper;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableLists;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.financial.instrument.Instrument;
import io.redstone.core.strategy.Strategy;

@NotThreadSafe
public final class StrategyKeeper {

	private static final Logger log = CommonLoggerFactory.getLogger(StrategyKeeper.class);

	private static final MutableIntObjectMap<Strategy> Strategys = MutableMaps.newIntObjectHashMap();

	// Map<instrumentId, List<Strategy>>
	private static final MutableIntObjectMap<MutableList<Strategy>> SubscribedInstrumentList = MutableMaps
			.newIntObjectHashMap();

	private StrategyKeeper() {
	}

	/**
	 * 添加策略并置为激活, 同时添加策略订阅的Instrument
	 * 
	 * @param strategy
	 */
	public static void putStrategy(Strategy strategy) {
		Strategys.put(strategy.strategyId(), strategy);
		log.info("StrategyKeeper :: Put strategy, strategyId==[{}]", strategy.strategyId());
		strategy.instruments().forEach(instrument -> {
			SubscribedInstrumentList.getIfAbsentPut(instrument.id(), MutableLists::newFastList).add(strategy);
			log.info("StrategyKeeper :: Add subscribed instrument, strategyId==[{}], instrumentId==[{}]",
					strategy.strategyId(), instrument.id());
		});
		strategy.enable();
		log.info("StrategyKeeper :: Strategy is enable, strategyId==[{}]", strategy.strategyId());
	}

	public static Strategy getStrategy(int strategyId) {
		return Strategys.get(strategyId);
	}

	public static MutableList<Strategy> getSubscribedStrategys(int instrumentId) {
		return SubscribedInstrumentList.get(instrumentId);
	}

	public static MutableList<Strategy> getSubscribedStrategys(Instrument instrument) {
		return SubscribedInstrumentList.get(instrument.id());
	}

}
