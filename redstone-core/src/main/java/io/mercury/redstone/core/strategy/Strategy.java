package io.redstone.core.strategy;

import java.util.function.Supplier;

import org.eclipse.collections.api.list.ImmutableList;

import io.mercury.common.fsm.Enable;
import io.mercury.financial.instrument.Instrument;
import io.mercury.financial.market.impl.BasicMarketData;
import io.redstone.core.adaptor.Adaptor;
import io.redstone.core.adaptor.Adaptor.AdaptorStatus;
import io.redstone.core.order.Order;

public interface Strategy extends Enable<Strategy> {

	int strategyId();

	String strategyName();

	int subAccountId();

	ImmutableList<Instrument> instruments();

	void addAdaptor(Adaptor adaptor);

	void initialize(Supplier<Boolean> initializer);

	void onAdaptorStatus(int adaptorId, AdaptorStatus status);

	void onStrategyEvent(StrategyEvent event);

	void onMarketData(BasicMarketData marketData);

	void onOrder(Order order);

	void onError(Throwable throwable);

}
