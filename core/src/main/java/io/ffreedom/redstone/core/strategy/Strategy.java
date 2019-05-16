package io.ffreedom.redstone.core.strategy;

import org.eclipse.collections.api.list.ImmutableList;

import io.ffreedom.common.fsm.Enable;
import io.ffreedom.common.functional.Initializer;
import io.ffreedom.polaris.financial.Instrument;
import io.ffreedom.polaris.market.BasicMarketData;
import io.ffreedom.redstone.core.order.api.Order;

public interface Strategy extends Enable {

	int getStrategyId();

	String getStrategyName();

	int getSubAccountId();

	ImmutableList<Instrument> getInstruments();

	void init(Initializer<Boolean> initializer);

	void onControlEvent(StrategyControlEvent event);

	void onMarketData(BasicMarketData marketData);

	void onOrder(Order order);

	void onError(Throwable throwable);

}
