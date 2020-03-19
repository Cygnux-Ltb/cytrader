package io.redstone.core.strategy;

import java.util.function.Supplier;

import org.eclipse.collections.api.list.ImmutableList;

import io.mercury.common.fsm.Enable;
import io.mercury.polaris.financial.instrument.Instrument;
import io.mercury.polaris.financial.market.impl.BasicMarketData;
import io.redstone.core.adaptor.AdaptorEvent;
import io.redstone.core.order.Order;

public interface Strategy extends Enable {

	int strategyId();

	String strategyName();

	int subAccountId();

	ImmutableList<Instrument> instruments();

	void initialize(Supplier<Boolean> initializer);

	void onAdaptorEvent(AdaptorEvent event);

	void onStrategyEvent(StrategyEvent event);

	void onMarketData(BasicMarketData marketData);

	void onOrder(Order order);

	void onError(Throwable throwable);

}
