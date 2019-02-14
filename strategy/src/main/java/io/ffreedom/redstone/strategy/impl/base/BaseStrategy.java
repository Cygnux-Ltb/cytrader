package io.ffreedom.redstone.strategy.impl.base;

import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;
import org.slf4j.Logger;

import io.ffreedom.common.collect.ECollections;
import io.ffreedom.common.functional.Initializer;
import io.ffreedom.common.log.LoggerFactory;
import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.actor.InstrumentKeeper;
import io.ffreedom.redstone.actor.OrderActor;
import io.ffreedom.redstone.core.order.Order;
import io.ffreedom.redstone.core.order.VirtualOrder;
import io.ffreedom.redstone.core.strategy.CircuitBreaker;
import io.ffreedom.redstone.core.strategy.Strategy;

public abstract class BaseStrategy<M extends BasicMarketData> implements Strategy, CircuitBreaker {

	private int strategyId;

	private boolean isInitSuccess = false;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	//
	protected MutableLongObjectMap<VirtualOrder> strategyOrders = ECollections.newLongObjectHashMap();

	public BaseStrategy(int strategyId) {
		super();
		this.strategyId = strategyId;
	}

	@Override
	public void init(Initializer<Boolean> initializer) {
		if (initializer != null)
			isInitSuccess = initializer.initialize();
		else
			logger.error("Initializer is null.");
		logger.info("Initialize result isInitSuccess==[{}]", isInitSuccess);
	}

	@Override
	public int getStrategyId() {
		return strategyId;
	}

	@Override
	public void onOrder(Order order) {
		OrderActor.onOrder(order);
	}

	private boolean isEnable = false;

	@Override
	public boolean enabled() {
		return isEnable;
	}

	@Override
	public void setEnable(boolean enable) {
		if (isInitSuccess && enable)
			this.isEnable = true;
		logger.info("Enable strategy , strategyId==[{}], isInitSuccess==[{}], isEnable==[]", strategyId, isInitSuccess,
				isEnable);
	}

	@Override
	public void enableAccount(int accountId) {

	}

	@Override
	public void disableAccount(int accountId) {

	}

	@Override
	public void enableInstrument(int instrumentId) {
		InstrumentKeeper.setTradable(instrumentId);
	}

	@Override
	public void disableInstrument(int instrumentId) {
		InstrumentKeeper.setNotTradable(instrumentId);
	}

	@Override
	public void onError(Throwable throwable) {
		logger.error("StrategyId -> [{}] throw exception -> [{}]", strategyId, throwable);
	}

	@Override
	public void positionTarget(Instrument instrument, double targetQty, double minPrice, double maxPrice) {

	}

}
