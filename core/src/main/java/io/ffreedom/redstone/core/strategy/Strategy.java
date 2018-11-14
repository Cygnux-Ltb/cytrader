package io.ffreedom.redstone.core.strategy;

import io.ffreedom.common.functional.Initializer;
import io.ffreedom.financial.Instrument;
import io.ffreedom.market.BasicMarketData;
import io.ffreedom.redstone.core.order.Order;

public interface Strategy<I, M extends BasicMarketData> {

	int getStrategyId();

	boolean isEnable();

	void init(Initializer<I> initializer);

	void onControlEvent(StrategyControlEvent event);

	void onMarketData(M marketData);

	void onOrder(Order order);

	void onError(Throwable throwable);

	void positionTarget(Instrument instrument, double targetQty, double minPrice, double maxPrice);

}
