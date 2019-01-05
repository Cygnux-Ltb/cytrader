package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.slf4j.Logger;

import io.ffreedom.common.collect.ECollections;
import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.financial.Instrument;
import io.ffreedom.market.BasicMarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.strategy.Strategy;

public final class StrategyActor {

	private Logger logger = LoggerFactory.getLogger(StrategyActor.class);

	private MutableIntObjectMap<Strategy> strategyMap = new IntObjectHashMap<>();

	// Map<instrumentId, List<Strategy>>
	private MutableIntObjectMap<MutableList<Strategy>> instrumentStrategyMultimap = ECollections.newIntObjectHashMap();

	public static final StrategyActor INSTANCE = new StrategyActor();

	private StrategyActor() {
	}

	public void onMarketData(BasicMarketData marketData) {
		instrumentStrategyMultimap.get(marketData.getInstrumentId()).each(strategy -> {
			if (strategy.isEnable())
				strategy.onMarketData(marketData);
		});
	}

	public void onOrder(Order order) {
		logger.debug("Call StrategyActor.onOrder , StrategyId==[{}], ordSysId==[{}]", order.getStrategyId(),
				order.getOrdSysId());
		strategyMap.get(order.getStrategyId()).onOrder(order);
	}

	public void addStrategy(Strategy strategy) {
		strategyMap.put(strategy.getStrategyId(), strategy);
	}

	public void bindInstrument(Strategy strategy, Instrument... instruments) {
		for (int i = 0; i < instruments.length; i++) {
			int instrumentId = instruments[i].getInstrumentId();
			MutableList<Strategy> strategyList = instrumentStrategyMultimap.get(instrumentId);
			if (strategyList == null) {
				strategyList = ECollections.newFastList();
				instrumentStrategyMultimap.put(instrumentId, strategyList);
			}
			strategyList.add(strategy);
		}
	}

}
