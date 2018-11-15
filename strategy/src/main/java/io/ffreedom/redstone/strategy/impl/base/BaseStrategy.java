package io.ffreedom.redstone.strategy.impl.base;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.slf4j.Logger;

import io.ffreedom.common.collect.EclipseCollections;
import io.ffreedom.common.functional.Initializer;
import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.financial.Instrument;
import io.ffreedom.market.BasicMarketData;
import io.ffreedom.redstone.actor.InstrumentActor;
import io.ffreedom.redstone.actor.OrderActor;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.strategy.CircuitBreaker;
import io.ffreedom.redstone.core.strategy.Strategy;

public abstract class BaseStrategy<M extends BasicMarketData> implements Strategy, CircuitBreaker {

	private int strategyId;

	private boolean isEnable;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected MutableLongObjectMap<Order> strategyOrders = EclipseCollections.newLongObjectHashMap();

	public BaseStrategy(int strategyId) {
		super();
		this.strategyId = strategyId;
	}

	@Override
	public void init(Initializer<Boolean> initializer) {
		if (initializer != null)
			isEnable = initializer.initialize();
		else
			logger.info("Initializer is null.");
	}

	@Override
	public int getStrategyId() {
		return strategyId;
	}

	@Override
	public boolean isEnable() {
		return isEnable;
	}

	@Override
	public void onOrder(Order order) {

		OrderActor.onOrder(order);
	}

	@Override
	public void enableStrategy() {
		this.isEnable = true;
	}

	@Override
	public void disableStrategy() {
		this.isEnable = false;
	}

	@Override
	public void enableAccount(int accountId) {
		
	}

	@Override
	public void disableAccount(int accountId) {

	}

	@Override
	public void enableInstrument(int instrumentId) {
		InstrumentActor.setTradable(instrumentId);
	}

	@Override
	public void disableInstrument(int instrumentId) {
		InstrumentActor.setNotTradable(instrumentId);
	}

	@Override
	public void onError(Throwable throwable) {
		logger.error("StrategyId -> [{}] throw exception -> [{}]", strategyId, throwable);
	}

	@Override
	public void positionTarget(Instrument instrument, double targetQty, double minPrice, double maxPrice) {

	}

}
