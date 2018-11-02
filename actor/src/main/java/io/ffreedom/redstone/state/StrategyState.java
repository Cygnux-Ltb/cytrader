package io.ffreedom.redstone.state;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.impl.map.mutable.primitive.IntObjectHashMap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;

import org.slf4j.Logger;

import io.ffreedom.common.fsm.AbsMooreFSM;
import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.financial.Instrument;
import io.ffreedom.market.MarketData;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.strategy.Strategy;

public final class StrategyState extends AbsMooreFSM<Strategy> {

	private Logger logger = LoggerFactory.getLogger(StrategyState.class);

	private MutableIntObjectMap<Strategy> strategyMap = new IntObjectHashMap<>();

	private MutableMultimap<Instrument, Strategy> instrumentStrategyMultimap = FastListMultimap.newMultimap();

	public static final StrategyState INSTANCE = new StrategyState();

	private StrategyState() {
	}

	public void onMarketData(MarketData marketData) {
		instrumentStrategyMultimap.get(marketData.getInstrument()).each(strategy -> strategy.onMarketData(marketData));
	}

	public void onOrder(Order order) {
		Strategy strategy = strategyMap.get(order.getStrategyId());
		switch (order.getOrdStatus()) {
		case New:
			strategy.onNewOrder(order);
			break;
		case Canceled:
			strategy.onCancelOrder(order);
			break;
		case PartiallyFilled:
			strategy.onOrderPartiallyFilled(order);
			break;
		case Filled:
			strategy.onOrderFilled(order);
			break;
		case NewRejected:
			strategy.onNewOrderReject(order);
			break;
		case CancelRejected:
			strategy.onCancelOrderReject(order);
			break;
		default:
			logger.warn("Not processed : OrdSysId -> {}, OrdStatus -> {}", order.getOrdSysId(), order.getOrdStatus());
			break;
		}

	}

	@Override
	public void registerElement(Strategy strategy) {
		strategyMap.put(strategy.getStrategyId(), strategy);
	}

	@Override
	protected void clear() {
		strategyMap.clear();
	}

}
