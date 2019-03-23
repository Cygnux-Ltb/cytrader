package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.ffreedom.common.collect.ECollections;
import io.ffreedom.common.log.CommonLoggerFactory;
import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.strategy.Strategy;

public final class StrategyActor {

	private Logger logger = CommonLoggerFactory.getLogger(StrategyActor.class);

	private MutableIntObjectMap<Strategy> strategyMap = ECollections.newIntObjectHashMap();

	// Map<instrumentId, List<Strategy>>
	private MutableIntObjectMap<MutableList<Strategy>> instrumentStrategyMap = ECollections.newIntObjectHashMap();

	public static final StrategyActor Singleton = new StrategyActor();

	private StrategyActor() {

	}

	public void onMarketData(BasicMarketData marketData) {
		instrumentStrategyMap.get(marketData.getInstrument().getInstrumentId()).each(strategy -> {
			if (strategy.isEnabled())
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
			MutableList<Strategy> strategyList = instrumentStrategyMap.get(instrumentId);
			if (strategyList == null) {
				strategyList = ECollections.newFastList();
				instrumentStrategyMap.put(instrumentId, strategyList);
			}
			strategyList.add(strategy);
		}
	}

}
