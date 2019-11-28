package io.redstone.core.strategy;

import java.util.function.Supplier;

import org.eclipse.collections.api.list.ImmutableList;

import io.mercury.common.fsm.Enable;
import io.polaris.financial.instrument.Instrument;
import io.polaris.financial.market.impl.BasicMarketData;
import io.redstone.core.order.api.Order;

public interface Strategy extends Enable {

	int getStrategyId();

	String getStrategyName();

	int getSubAccountId();

	ImmutableList<Instrument> getInstruments();

	void initialize(Supplier<Boolean> initializer);

	void onControlEvent(StrategyControlEvent event);

	void onMarketData(BasicMarketData marketData);

	void onOrder(Order order);

	void onError(Throwable throwable);

}
