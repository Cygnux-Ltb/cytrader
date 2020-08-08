package io.mercury.redstone.core.strategy;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableLists;
import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.serialization.Dumpable;
import io.mercury.financial.instrument.Instrument;

/**
 * 可以使用在 #StrategyScheduler 中进行行情分发的管理
 * 
 * @author yellow013
 */
@NotThreadSafe
@Deprecated
public final class StrategyKeeper implements Dumpable<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4657849933310280319L;

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(StrategyKeeper.class);

	/**
	 * 策略列表
	 */
	private static final MutableIntObjectMap<Strategy<?>> StrategyMap = MutableMaps.newIntObjectHashMap();

	/**
	 * 子账户与策略的对应关系
	 */
//	@Deprecated
//	private static final MutableIntObjectMap<MutableList<Strategy>> SubAccountStrategysMap = MutableMaps
//			.newIntObjectHashMap();

	/**
	 * 订阅合约的策略列表
	 */
	private static final MutableIntObjectMap<MutableList<Strategy<?>>> SubscribedInstrumentMap = MutableMaps
			.newIntObjectHashMap();

	private StrategyKeeper() {
	}

	/**
	 * 添加策略并置为激活, 同时添加策略订阅的Instrument
	 * 
	 * @param strategy
	 */
	public static void putStrategy(Strategy<?> strategy) {
		if (StrategyMap.containsKey(strategy.strategyId())) {
			log.error("Strategy id is existed, Have stored or have duplicate strategy id");
		} else {
			StrategyMap.put(strategy.strategyId(), strategy);
			log.info("Put strategy, strategyId==[{}]", strategy.strategyId());
			strategy.instruments().each(instrument -> {
				SubscribedInstrumentMap.getIfAbsentPut(instrument.id(), MutableLists::newFastList).add(strategy);
				log.info("Add subscribe instrument, strategyId==[{}], instrumentId==[{}]", strategy.strategyId(),
						instrument.id());
			});
			strategy.enable();
			log.info("Strategy is enable, strategyId==[{}]", strategy.strategyId());
		}
	}

	public static Strategy<?> getStrategy(int strategyId) {
		return StrategyMap.get(strategyId);
	}

	public static MutableList<Strategy<?>> getSubscribedStrategys(Instrument instrument) {
		return getSubscribedStrategys(instrument.id());
	}

	public static MutableList<Strategy<?>> getSubscribedStrategys(int instrumentId) {
		return SubscribedInstrumentMap.get(instrumentId);
	}

	@Override
	public String dump() {
		return "";
	}

}
