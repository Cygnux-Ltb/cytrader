package io.ffreedom.redstone.actor;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.slf4j.Logger;

import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.market.BasicMarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.strategy.Strategy;

public final class StrategyState {

	private Logger logger = LoggerFactory.getLogger(StrategyState.class);

	private MutableIntObjectMap<Strategy> strategyMap = new IntObjectHashMap<>();

	private MutableMultimap<Integer, Strategy> instrumentStrategyMultimap = FastListMultimap
			.newMultimap();

	public static final StrategyState INSTANCE = new StrategyState();

	private StrategyState() {
	}

	public void onMarketData(BasicMarketData marketData) {
		instrumentStrategyMultimap.get(marketData.getInstrumentId())
				.each(strategy -> strategy.onMarketData(marketData));
	}

	public void onOrder(Order order) {
		Strategy strategy = strategyMap.get(order.getStrategyId());
		strategy.onOrder(order);
	}

	public void registerStrategy(Strategy strategy) {
		strategyMap.put(strategy.getStrategyId(), strategy);
	}

}
