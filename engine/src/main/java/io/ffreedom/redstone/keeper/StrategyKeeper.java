package io.ffreedom.redstone.keeper;

import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.ffreedom.common.collect.ECollections;
import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.strategy.Strategy;

@NotThreadSafe
public final class StrategyKeeper {

	private Logger logger = LoggerFactory.getLogger(StrategyKeeper.class);

	private MutableIntObjectMap<Strategy> strategyMap = ECollections.newIntObjectHashMap();

	// Map<instrumentId, List<Strategy>>
	private MutableIntObjectMap<MutableList<Strategy>> instrumentStrategyMap = ECollections.newIntObjectHashMap();

	public static final StrategyKeeper INSTANCE = new StrategyKeeper();

	private StrategyKeeper() {

	}

	public void onMarketData(BasicMarketData marketData) {
		instrumentStrategyMap.get(marketData.getInstrumentId()).each(strategy -> {
			if (strategy.isEnable())
				strategy.onMarketData(marketData);
		});
	}

	public void onOrder(Order order) {
		logger.debug("Call StrategyActor.onOrder , StrategyId==[{}], ordSysId==[{}]", order.getStrategyId(),
				order.getOrdSysId());
		strategyMap.get(order.getStrategyId()).onOrder(order);
	}

	public void put(Strategy strategy, Instrument... instruments) {
		strategyMap.put(strategy.getStrategyId(), strategy);
		if (instruments != null) {
			for (int i = 0; i < instruments.length; i++) {
				int instrumentId = instruments[i].getInstrumentId();
				MutableList<Strategy> strategyList = instrumentStrategyMap.get(instrumentId);
				if (strategyList == null) {
					strategyList = ECollections.newFastList();
					instrumentStrategyMap.put(instrumentId, strategyList);
				}
				strategyList.add(strategy);
			}
		}
	}

}
