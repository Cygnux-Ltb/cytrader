package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.slf4j.Logger;

import io.ffreedom.common.collect.EclipseCollections;
import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.market.BasicMarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.strategy.Strategy;

public final class StrategyState {

	private Logger logger = LoggerFactory.getLogger(StrategyState.class);

	private MutableIntObjectMap<Strategy> strategyMap = new IntObjectHashMap<>();

	// Map<instrumentId, List<Strategy>>
	private MutableMultimap<Integer, Strategy> instrumentIdStrategyMultimap = EclipseCollections.newFastListMultimap();

	public static final StrategyState INSTANCE = new StrategyState();

	private StrategyState() {
	}

	public void onMarketData(BasicMarketData marketData) {
		instrumentIdStrategyMultimap.get(marketData.getInstrumentId()).each(strategy -> {
			if (strategy.isEnable())
				strategy.onMarketData(marketData);
		});
	}

	public void onOrder(Order order) {
		strategyMap.get(order.getStrategyId()).onOrder(order);
	}

	public void registerStrategy(Strategy strategy) {
		strategyMap.put(strategy.getStrategyId(), strategy);
	}

}
